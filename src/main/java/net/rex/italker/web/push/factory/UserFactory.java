package net.rex.italker.web.push.factory;

import com.google.common.base.Strings;
import net.rex.italker.web.push.bean.db.User;
import net.rex.italker.web.push.utils.Hib;
import net.rex.italker.web.push.utils.TextUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.UUID;

/**
 * Created by Rexbean on 6/21/2018.
 */
public class UserFactory {

    /**
     * find user by token, only use by himself, not by others
     * @param token
     * @return
     */
    public static User findByToken(String token){
        return Hib.query(session -> (User)session.createQuery("from User where token=:token")
                .setParameter("token",token)
                .uniqueResult());
    }
    public static User findByPhone(String phone){
        return Hib.query(session -> (User)session.createQuery("from User where phone=:phone").setParameter("phone",phone).uniqueResult());
    }

    public static User findByName(String name){
        return Hib.query(session -> (User)session.createQuery("from User where name =:name").setParameter("name",name).uniqueResult());
    }


    /**
     * using username and password login
     * @param account account
     * @param password password
     * @return user
     */
    public static User login(String account, String password){
        String accountStr = account.trim();
        String passwordStr = encodePwd(password.trim());

        User user = Hib.query(session -> (User) session.createQuery("from User where phone=:phone and password=:password")
                    .setParameter("phone", accountStr)
                    .setParameter("password",passwordStr)
                    .uniqueResult());

        if(user != null){
            user = login(user);
        }
        return user;

    }

    /**
     * register a user,insert to db and return the user info
     * @param account account,
     * @param password password
     * @param name name
     * @return User
     */
    public static User register(String account, String password, String name) {
        // remove space
        account = account.trim();
        name = name.trim();
        // encrypt password
        password = encodePwd(password);

        return createUser(account, password, name);
    }

    /**
     * login operation,token operation
     * @param user user
     * @return login user
     */
    public static User login(User user){
        // using a random uuid to be a new token
        String newToken = UUID.randomUUID().toString();
        newToken = TextUtil.encodeBase64(newToken);

        user.setToken(newToken);

        Hib.query(session -> {
            session.saveOrUpdate(user);
        });
        return user;

    }

    /**
     * register create user and insert to
     * @param account account/phone
     * @param password password
     * @param name username
     * @return created user
     */
    private static User createUser(String account, String password, String name){
        User user = new User();

        user.setName(name);
        user.setPassword(password);
        user.setPhone(account);

        return Hib.query(session -> (User)session.save(user));
    }

    private static String encodePwd(String pwd){
        pwd = pwd.trim();
        pwd = TextUtil.getMD5(pwd);
        return TextUtil.encodeBase64(pwd);
    }

    /**
     * bind pushId to current user
     * @param user user
     * @param pushId pushId
     * @return user
     */
    public static User bindPushId(User user, String pushId){
        // find whether there is other device bind to this pushId
        // cancel bind
        // the result list won't contain itself
        Hib.query(session->{
            @SuppressWarnings("unchecked")
            List<User> userList = (List<User>)session.createQuery(
                    "from User where lower(pushId)=:pushId and id!=:userId")
                    .setParameter("pushId",pushId.toLowerCase())
                    .setParameter("userId",user.getId())
                    .list();
            for(User u: userList){
                u.setPushId(null);
                session.saveOrUpdate(u);
            }
        });

        // if bind before then return
        if(pushId.equalsIgnoreCase(user.getPushId())){
            return user;
        } else {
            // if pushId different
            // send a message to that device to quit
            if(Strings.isNullOrEmpty(user.getPushId())){

            }
            user.setPushId(pushId);
            return Hib.query(session->{
                session.saveOrUpdate(user);
                return user;
            });
        }
    }
}
