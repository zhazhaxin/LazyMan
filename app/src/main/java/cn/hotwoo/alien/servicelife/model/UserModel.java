package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.bean.BaseBean;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;
import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by alien on 2015/8/13.
 */
public class UserModel extends AbsModel {

    public static final String USERINFO = "userInfo";
    public static final String UPDATE_USER_DATA = "updateUserInfo";
    private static UpdateFaceService updateFaceService;
    private static Retrofit retrofit;

    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
        if (getUserFromFile() != null) {
            updateLocalData();
        }
        updateFaceService = getService();
    }

    public UpdateFaceService getService() {
        return getRetrofit().create(UpdateFaceService.class);
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (this) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(API.BASEURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    private interface UpdateFaceService {
        @FormUrlEncoded
        @POST("updateFace.php")
        Call<BaseBean> updateFace(@Field("id") int id, @Field("face") String img);
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
    public void userLogin(String name, String password, StatusCallback callback) {
        RequestMap param = new RequestMap();
        param.put("name", name);
        param.put("password", password);
        RequestManager.getInstance().post(API.login, param, callback.add(new DataCallback<User>() {
            @Override
            public void success(User data) {
                saveUserToFile(data);
            }
        }));
    }

    /**
     * 用户注册接口
     *
     * @param name
     * @param password
     * @param callback
     */
    public void userRegister(String name, String password, StatusCallback callback) {
        RequestMap param = new RequestMap();
        param.put("name", name);
        param.put("password", password);
        param.put("face", API.DEFAULT_FACE);
        RequestManager.getInstance().post(API.register, param, callback.add(new DataCallback<User>() {

            @Override
            public void success(User data) {
                saveUserToFile(data);
            }
        }));
    }

    /**
     * 修改个人信息
     *
     * @param u
     * @param callback
     */
    public void updateUserData(User u, StatusCallback callback) {
        RequestMap param = new RequestMap();
        param.put("id", getUserFromFile().getId() + "");
        param.put("name", u.getName());
        param.put("sign", u.getSign());
        param.put("school", u.getSchool());
        param.put("gender", u.getGender() + "");
        param.put("birth", u.getBirth() + "");
        param.put("age", u.getAge() + "");
        param.put("major", u.getMajor());
        param.put("phone", u.getPhone() + "");
        param.put("qq", u.getQq() + "");
        param.put("intro", u.getIntro());
        RequestManager.getInstance().post(API.updateUserData, param, callback);
    }

    /**
     * 更换头像
     *
     * @param img
     * @return
     */
    public void updateFace(final String img, StatusCallback callback) {
        RequestMap params = new RequestMap();
        params.put("id", getUserFromFile().getId() + "");
        params.put("face", img);
        RequestManager.getInstance().post(API.updateFace, params, callback);
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
