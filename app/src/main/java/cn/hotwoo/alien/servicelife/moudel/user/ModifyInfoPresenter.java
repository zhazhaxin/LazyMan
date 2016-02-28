package cn.hotwoo.alien.servicelife.moudel.user;

import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.ResponseInfo;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.util.Utils;
import de.greenrobot.event.EventBus;
import rx.Observer;

/**
 * Created by alien on 2015/8/14.
 */
public class ModifyInfoPresenter extends BasePresenter<ModifyInfoActivity> {

    private User user;

    @Override
    protected void onCreate(ModifyInfoActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        user = new User();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreateView(ModifyInfoActivity view) {
        super.onCreateView(view);
        setUserData();
    }

    public void setUserData() {
        getView().setData(UserModel.getInstance().getUserFromFile());
    }

    public void setName(String name) {
        user.setName(name);
    }

    public void setGender(int gender) {
        user.setGender(gender);
    }

    public void setAge(int age) {
        user.setAge(age);
        getView().setAge(age);
    }

    public void setSign(String sign) {
        user.setSign(sign);
    }

    public void setSchool(String school) {
        user.setSchool(school);
    }

    public void setBirth(long birth) {
        user.setBirth(birth);
    }

    public void setMajor(String major) {
        user.setMajor(major);
    }

    public void setPhone(long phone) {
        user.setPhone(phone);
    }

    public void setQQ(long qq) {
        user.setQq(qq);
    }

    public void setIntro(String intro) {
        user.setIntro(intro);
    }

    public void updateUserInfo() {
        getView().showProgress("保存中");
        User oldUserData = UserModel.getInstance().getUserFromFile();
        if (user.getName() == null) {
            user.setName(oldUserData.getName());
        }
        if (user.getSign() == null) {
            user.setSign(oldUserData.getSign());
        }
        if (user.getBirth() == 0) {
            user.setBirth(oldUserData.getBirth());
        }
        if (user.getAge() == 0) {
            user.setAge(oldUserData.getAge());
        }
        if (user.getSchool() == null) {
            user.setSchool(oldUserData.getSchool());
        }
        if (user.getMajor() == null) {
            user.setMajor(oldUserData.getMajor());
        }
        if (user.getPhone() == 0) {
            user.setPhone(oldUserData.getPhone());
        }
        if (user.getQq() == 0) {
            user.setQq(oldUserData.getQq());
        }
        if (user.getIntro() == null) {
            user.setIntro(oldUserData.getIntro());
        }
        UserModel.getInstance().updateUserData(user, new Observer<ResponseInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Utils.Toast("网络错误");
            }

            @Override
            public void onNext(ResponseInfo s) {
                Utils.Toast("已保存");
                UserModel.getInstance().updateLocalData(); //更新用户信息
                getView().finish();
            }
        });
    }

    public void onEvent(String event) {
        Utils.Log("ModifyInfoPresenter----onEvent");
        if (event.equals(UserModel.UPDATE_USER_DATA))
            setUserData();
    }
}
