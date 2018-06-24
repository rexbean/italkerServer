package net.rex.italker.web.push.service;

import com.google.common.base.Strings;
import net.rex.italker.web.push.bean.api.account.AccountRspModel;
import net.rex.italker.web.push.bean.api.account.LoginModel;
import net.rex.italker.web.push.bean.api.account.RegisterModel;
import net.rex.italker.web.push.bean.api.base.ResponseModel;
import net.rex.italker.web.push.bean.card.UserCard;
import net.rex.italker.web.push.bean.db.User;
import net.rex.italker.web.push.factory.UserFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Rexbean on 6/13/2018.
 */
@Path("/account")
public class AccountService {
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> login(LoginModel loginModel) {

        if(LoginModel.check(loginModel)){
            return ResponseModel.buildParameterError();
        }

        User user = UserFactory.login(loginModel.getAccount(), loginModel.getPassword());

        if(user != null){
            AccountRspModel rspModel = new AccountRspModel(user);
            return ResponseModel.buildOk(rspModel);
        } else {
            return ResponseModel.buildLoginError();
        }


    }

    @POST
    @Path("/bind/{pushId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // get the token from the request header
    // get pushId from url
    public ResponseModel<AccountRspModel> bind(@HeaderParam("token") String token,
                                               @PathParam("pushId") String pushId) {

        if(Strings.isNullOrEmpty(token)||
                Strings.isNullOrEmpty(pushId)){
            return ResponseModel.buildParameterError();
        }

        // get user using token
        User user = UserFactory.findByToken(token);

        if(user != null){

        }
        return null;

    }


    @POST
    @Path("/register")
    // 指定 请求与返回响应为JSON
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseModel<AccountRspModel> register(RegisterModel model) {

        if(RegisterModel.check(model)){
            return ResponseModel.buildParameterError();
        }
        // check duplicate
        User user = UserFactory.findByPhone(model.getAccount().trim());
        if (user != null) {
            // have account error;
            return ResponseModel.buildHaveAccountError();
        }

        user = UserFactory.findByName(model.getName().trim());
        if (user != null) {
            return ResponseModel.buildHaveNameError();
        }

        // register logic
        user = UserFactory.register(model.getAccount(),
                model.getPassword(),
                model.getName());

        if (user != null) {
            AccountRspModel rspModel = new AccountRspModel(user);
            return ResponseModel.buildOk(rspModel);
        } else {
            return ResponseModel.buildRegisterError();
        }
    }
}
