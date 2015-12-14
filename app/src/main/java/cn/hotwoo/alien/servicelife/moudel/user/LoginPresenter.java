package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/13.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {


    @Override
    protected void onCreateView(LoginActivity view) {
        super.onCreateView(view);
    }

    public void login(String name,String password){
        getView().showProgress();
        String realPass = Utils.md5(password);
        UserModel.getInstance().userLogin(name, realPass, new StatusCallback() {
            @Override
            public void success(String response) {
                Utils.Toast("登录成功");
                getView().finish();
            }

            @Override
            public void result(int status) {
                super.result(status);
                switch (status) {
                    case 201:
                        Utils.Toast("用户名或密码错误");
                        getView().dismissProgress();
                        break;
                }
            }
        });
    }

    public void register(){
        getView().startActivity(new Intent(getView(), RegisterActivity.class));
        getView().finish();
    }

}
