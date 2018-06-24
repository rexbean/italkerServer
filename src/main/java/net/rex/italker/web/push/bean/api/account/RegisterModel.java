package net.rex.italker.web.push.bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

/**
 * Created by Rexbean on 6/21/2018.
 */
public class RegisterModel {
    @Expose
    private String account;
    @Expose
    private String password;
    @Expose
    private String name;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean check(RegisterModel registerModel){
        return registerModel != null &&
                Strings.isNullOrEmpty(registerModel.account) &&
                Strings.isNullOrEmpty(registerModel.password) &&
                Strings.isNullOrEmpty(registerModel.name);
    }
}
