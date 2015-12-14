package cn.hotwoo.alien.servicelife.moudel.main;

import android.content.Intent;
import android.os.Bundle;

import com.jude.beam.bijection.Presenter;

import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.moudel.user.LoginActivity;
import de.greenrobot.event.EventBus;

/**
 * Created by alien on 2015/7/27.
 */

public class DawerPresenter extends Presenter<DawerFragment> {

    private User user;

    @Override
    protected void onCreate(DawerFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        EventBus.getDefault().register(this);
        user= UserModel.getInstance().getUserFromFile();
    }

    @Override
    protected void onCreateView(DawerFragment view) {
        super.onCreateView(view);
        if(user != null){
            getView().updateUi(user);
        }
    }

    public void startActivity(Class<?> clazz){
        if(UserModel.getInstance().getUserFromFile()==null){
            clazz= LoginActivity.class;
        }
        getView().startActivity(new Intent(getView().getActivity(), clazz));
    }

    public void onEvent(String event){
        if(event.equals(UserModel.UPDATE_USER_DATA)){
            user= UserModel.getInstance().getUserFromFile();
            getView().updateUi(user);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
