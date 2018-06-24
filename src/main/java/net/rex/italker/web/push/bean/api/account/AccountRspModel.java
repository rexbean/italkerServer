package net.rex.italker.web.push.bean.api.account;

import com.google.gson.annotations.Expose;
import net.rex.italker.web.push.bean.card.UserCard;
import net.rex.italker.web.push.bean.db.User;

/**
 * Created by Rexbean on 6/21/2018.
 */
public class AccountRspModel {

    @Expose
    private UserCard user;
    @Expose
    private String account;
    // token got after login, using token to get all user info
    @Expose
    private String token;
    // whether bind to a device PushId
    @Expose
    private boolean isBind;

    public AccountRspModel(User user, boolean isBind) {
        this.user = new UserCard(user);
        this.account = user.getPhone();
        this.token = user.getToken();
        this.isBind = isBind;
    }

    public AccountRspModel(User user) {
        this(user, false);
    }

    public UserCard getUser() {
        return user;
    }

    public void setUser(UserCard user) {
        this.user = user;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }
}
