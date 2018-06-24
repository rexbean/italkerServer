package net.rex.italker.web.push.bean.api.account;

import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;

public class LoginModel {
    @Expose
    private String account;
    @Expose
    private String password;

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

    public static boolean check(LoginModel loginModel){
        return loginModel != null &&
                Strings.isNullOrEmpty(loginModel.account) &&
                Strings.isNullOrEmpty(loginModel.password);
    }

}

