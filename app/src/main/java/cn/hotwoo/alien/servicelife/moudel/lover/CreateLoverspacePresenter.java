package cn.hotwoo.alien.servicelife.moudel.lover;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.LoveModel;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/9/3.
 */
public class CreateLoverspacePresenter extends BasePresenter<CreateLovespaceActivity> {

    public void createLoverSpace(String spaceName, String password) {
        getView().showProgress();
        String realPassword = Utils.md5(password);
        LoveModel.getInstance().createLovespace(spaceName, realPassword, new StatusCallback() {
            @Override
            public void success(String response) {
                Utils.Toast("创建成功");
                UserModel.getInstance().updateLocalData();
                getView().dismissProgress();
                getView().startActivity(new Intent(getView(), LoverSpaceListActivity.class));
                getView().finish();
            }

            @Override
            public void result(int status) {
                super.result(status);
                if (status == 201) {
                    Utils.Toast("空间名重复");
                    getView().dismissProgress();
                }
            }
        });
    }
    public void loginLoverSpace(String spaceName, String password){
        getView().showProgress();
        LoveModel.getInstance().loginSpace(spaceName, password, new DataCallback<User>() {

            @Override
            public void success(User data) {
                Utils.Toast("登录成功");
                getView().dismissProgress();
                UserModel.getInstance().saveUserToFile(data);
                getView().startActivity(new Intent(getView(),LoverSpaceListActivity.class));
                getView().finish();
            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                if(status==400){
                    Utils.Toast("空间名或密码错误");
                    getView().dismissProgress();
                }
            }
        });
    }

}
