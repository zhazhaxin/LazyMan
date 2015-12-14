package cn.hotwoo.alien.servicelife.moudel.user;

import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/31.
 */
public class UpdateNameAndSignPresenter extends BasePresenter<UpdateNameAndSignActivity> {

    private User user;

    @Override
    protected void onCreate(UpdateNameAndSignActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        user= UserModel.getInstance().getUserFromFile();
    }

    @Override
    protected void onCreateView(UpdateNameAndSignActivity view) {
        super.onCreateView(view);
        if(user!=null)
            setData();
    }

    public void setData(){
        getView().setData(user);
    }

    public void updateNameAndSign(String name,String sign){
            UserModel.getInstance().updateNameAndSign(name, sign, new StatusCallback() {
            @Override
            public void success(String response) {
                UserModel.getInstance().updateLocalData();
                Utils.Toast("保存成功");
                getView().finish();
            }

            @Override
            public void result(int status) {
                super.result(status);
                if(status==201){
                    Utils.Toast("没有修改");
                    getView().finish();
                }
            }
        });
    }
}
