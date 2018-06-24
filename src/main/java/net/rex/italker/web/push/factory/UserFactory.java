package net.rex.italker.web.push.factory;

import net.rex.italker.web.push.bean.db.User;
import net.rex.italker.web.push.utils.Hib;
import net.rex.italker.web.push.utils.TextUtil;
import org.hibernate.Session;

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
}
