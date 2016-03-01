package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.ResponseInfo;
import cn.hotwoo.alien.servicelife.util.Utils;
import rx.functions.Action1;

/**
 * Created by alien on 2015/8/14.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    public static final String NAME = "NAME";
    public static final String PASSWORD = "PASSWORD";

    @Override
    protected void onCreateView(RegisterActivity view) {
        super.onCreateView(view);
    }

    public void register(final String name, final String password) {
        getView().showProgress();
        UserModel.getInstance().register(name, password).subscribe(new Action1<ResponseInfo>() {
            @Override
            public void call(ResponseInfo responseInfo) {
                Utils.Log("register--info:" + responseInfo.getInfo());
                Utils.Toast("注册成功");
                Intent intent = new Intent(getView(), LoginActivity.class);
                intent.putExtra(NAME,name);
                intent.putExtra(PASSWORD,password);
                getView().startActivity(intent);
                getView().finish();
            }
        });
    }

}
