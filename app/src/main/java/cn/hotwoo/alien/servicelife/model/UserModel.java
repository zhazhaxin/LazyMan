package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.bean.ResponseInfo;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.model.server.SchedulerTransformer;
import cn.hotwoo.alien.servicelife.model.server.ServiceAPIModule;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;
import de.greenrobot.event.EventBus;
import rx.Observable;

/**
 * Created by alien on 2015/8/13.
 */
public class UserModel extends AbsModel {

    public static final String USERINFO = "userInfo";
    public static final String UPDATE_USER_DATA = "updateUserInfo";

    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
        if (getUserFromFile() != null) {
            updateLocalData();
        }
    }

    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    public User getUserFromFile() {
        return (User) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.OBJECT, USERINFO));
    }

    /**
     * 获取个人信息，更新本地信息
     */
    public void updateLocalData() {
        RequestMap param = new RequestMap();
        param.put("id", getUserFromFile().getId() + "");
        RequestManager.getInstance().post(API.getUserData, param, new DataCallback<User>() {
            @Override
            public void success(User data) {
                Utils.Log("saveUserToFile");
                saveUserToFile(data);
            }
        });
    }

    public void saveUserToFile(User data) {
        Utils.writeObjectToFile(data, FileManager.getInstance().getChild(FileManager.Dir.OBJECT, USERINFO));
        EventBus.getDefault().post(UPDATE_USER_DATA);
    }

    /**
     * 用户登录接口
     *
     * @param name
     * @param password
     */
    public Observable<User> login(String name, String password) {
        return ServiceAPIModule.getInstance().providerServiceAPI().login(name, password).compose(new SchedulerTransformer<User>());
    }

    /**
     * 用户注册接口
     *
     * @param name
     * @param password
     */
    public Observable<ResponseInfo> register(String name, String password) {
        return ServiceAPIModule.getInstance().providerServiceAPI().register(name, password, API.DEFAULT_FACE).compose(new SchedulerTransformer<ResponseInfo>());
    }

    /**
     * 修改个人信息
     */
    public Observable<ResponseInfo> updateUserData(User user) {
        return ServiceAPIModule.getInstance().providerServiceAPI()
                .updateUserData(user.getId(), user.getName(), user.getSign(), user.getSchool(), user.getGender(),
                        user.getBirth(), user.getAge(), user.getMajor(), user.getPhone(), user.getQq(), user.getIntro())
                .compose(new SchedulerTransformer<ResponseInfo>());
    }

    /**
     * 更换头像
     *
     * @param img
     * @return
     */
    public Observable<ResponseInfo> updateFace(final String img) {
        return ServiceAPIModule.getInstance().providerServiceAPI().updateFace(getUserFromFile().getId(), img).compose(new SchedulerTransformer<ResponseInfo>());
    }

    /**
     * 更新用户名和个性签名
     *
     * @param name
     * @param sign
     * @param callback
     */
    public void updateNameAndSign(String name, String sign, StatusCallback callback) {
        RequestMap param = new RequestMap();
        param.put("id", getUserFromFile().getId() + "");
        param.put("name", name);
        param.put("sign", sign);
        RequestManager.getInstance().post(API.updateNameAndSign, param, callback);
    }
}
