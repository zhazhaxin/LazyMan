package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.ResponseInfo;
import cn.hotwoo.alien.servicelife.util.Utils;
import rx.Observer;

/**
 * Created by alien on 2015/8/14.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    @Override
    protected void onCreateView(RegisterActivity view) {
        super.onCreateView(view);
    }

    public void register(String name, String password) {
        getView().showProgress();
        UserModel.getInstance().register(name, password, new Observer<ResponseInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseInfo s) {
                Utils.Log("register--info:" + s.getInfo());
                Utils.Toast("注册成功");
                getView().startActivity(new Intent(getView(), ModifyInfoActivity.class));
                getView().finish();
            }
        });
    }

}
