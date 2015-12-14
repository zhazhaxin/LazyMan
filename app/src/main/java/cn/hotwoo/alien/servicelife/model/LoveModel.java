package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.bean.Love;
import cn.hotwoo.alien.servicelife.model.bean.User;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/9/2.
 */
public class LoveModel extends AbsModel {

    private User data;

    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
    }

    public static LoveModel getInstance() {
        return getInstance(LoveModel.class);
    }

    public void createLovespace(String name, String password, StatusCallback callback) {
        data = UserModel.getInstance().getUserFromFile();
        RequestMap param = new RequestMap();
        param.put("id", data.getId() + "");
        param.put("spaceName", name);
        param.put("lovePassword", password);
        RequestManager.getInstance().post(API.createLovespace, param, callback);
    }

    public void getLoves(DataCallback<Love[]> callback) {
        data = UserModel.getInstance().getUserFromFile();
        RequestMap param = new RequestMap();
        param.put("id", data.getId() + "");
        param.put("spaceName", data.getLoverSpace());
        param.put("lovePassword", data.getLovePassword());
        RequestManager.getInstance().post(API.getLoves, param, callback);
    }

    public void publishLove(Love love, StatusCallback callback) {
        data = UserModel.getInstance().getUserFromFile();
        RequestMap param = new RequestMap();
        param.put("lovePassword", data.getLovePassword());
        param.put("spaceName", data.getLoverSpace());
        param.put("author", love.author);
        param.put("loveimg", love.loveImg);
        param.put("title", love.title);
        param.put("content", love.content);
        param.put("showimg", love.showImg);
        param.put("time", love.time + "");
        RequestManager.getInstance().post(API.publishLove, param, callback);
    }

    public void loginSpace(String spaceName, String lovePassword, DataCallback<User> callback) {
        String realPassword = Utils.md5(lovePassword);
        RequestMap param = new RequestMap();
        param.put("id", UserModel.getInstance().getUserFromFile().getId() + "");
        param.put("lovePassword", realPassword);
        param.put("spaceName", spaceName);
        RequestManager.getInstance().post(API.loverSpaceLogin, param, callback);
    }
}

