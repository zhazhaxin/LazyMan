package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/14.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    @Override
    protected void onCreateView(RegisterActivity view) {
        super.onCreateView(view);
    }

    public void register(String name,String password){
        getView().showProgress();
        String realPass= Utils.md5(password);
        UserModel.getInstance().userRegister(name, realPass, new StatusCallback() {
            @Override
            public void success(String response) {
                Utils.Toast("注册成功");
                getView().startActivity(new Intent(getView(),ModifyInfoActivity.class));
                getView().finish();
            }

            @Override
            public void result(int status) {
                super.result(status);
                if (status==201){
                    getView().dismissProgress();
                    Utils.Toast("用户名重复");
                }

            }
        });
    }

}
