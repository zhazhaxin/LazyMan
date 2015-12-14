package cn.hotwoo.alien.servicelife.moudel.user;

import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import de.greenrobot.event.EventBus;

/**
 * Created by alien on 2015/8/14.
 */
public class UserInfoDetailPresenter extends BasePresenter<UserInfoDetailActivity> {

    private User data;

    @Override
    protected void onCreate(UserInfoDetailActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreateView(UserInfoDetailActivity view) {
        super.onCreateView(view);
        setData();
    }

    public void setData(){
        getView().setData(UserModel.getInstance().getUserFromFile());
    }

    public void onEvent(String event){
        if(event.equals(UserModel.UPDATE_USER_DATA))
            setData();
    }
}
