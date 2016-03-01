package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.util.Utils;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * Created by alien on 2015/8/13.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    @Override
    protected void onCreateView(LoginActivity view) {
        super.onCreateView(view);
    }

    public void login(String name, String password) {
        getView().showProgress();
        UserModel.getInstance().login(name, password)
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e instanceof HttpException){
                            HttpException httpException = (HttpException) e;
                            if(httpException.code() == 400){
                                try {

                                    JSONObject jsonObject = null;
                                    try {
                                        Utils.Log("response:" + ((HttpException) e).response().errorBody().string());
                                        jsonObject = new JSONObject(((HttpException) e).response().errorBody().string());
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    Utils.Toast(jsonObject.getString("error"));
                                } catch (JSONException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onNext(User user) {
                            Utils.Toast("登录成功");
                            UserModel.getInstance().saveUserToFile(user);
                            getView().finish();
                    }
                });
    }

    public void register() {
        getView().startActivity(new Intent(getView(), RegisterActivity.class));
        getView().finish();
    }

}
