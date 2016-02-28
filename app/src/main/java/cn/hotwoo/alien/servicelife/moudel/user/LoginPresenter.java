package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.util.Utils;
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
                        Utils.Toast("网络错误");
                    }

                    @Override
                    public void onNext(User user) {
                        if (user.getId() != 0) {
                            Utils.Toast("登录成功");
                            UserModel.getInstance().saveUserToFile(user);
                        }
                    }
                });
//                .subscribe(new Action1<User>() {
//                    @Override
//                    public void call(User user) {
//                        if (user.getId() != 0) {
//                            Utils.Toast("登录成功");
//                            UserModel.getInstance().saveUserToFile(user);
//                        }
//                    }
//                });
    }

    public void register() {
        getView().startActivity(new Intent(getView(), RegisterActivity.class));
        getView().finish();
    }

}
