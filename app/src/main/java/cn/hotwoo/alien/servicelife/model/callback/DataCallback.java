package cn.hotwoo.alien.servicelife.model.callback;


import com.activeandroid.Model;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.util.SpecificClassExclusionStrategy;
import cn.hotwoo.alien.servicelife.util.Utils;


/**
 * Created by zhuchenxi on 15/5/11.
 */
public abstract class DataCallback<T> extends LinkCallback {

    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        JSONObject jsonObject;
        int status = 0;
        String info = "";
        T data = null;
        try {
            jsonObject = new JSONObject(s);
            status = jsonObject.getInt(API.KEY.STATUS);
            info = jsonObject.getString(API.KEY.INFO);
            if (status == API.CODE.SUCCEED){
                Gson gson = new GsonBuilder().setExclusionStrategies(new SpecificClassExclusionStrategy(null, Model.class)).create();
                data = gson.fromJson(jsonObject.getString(API.KEY.DATA), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            }
        } catch (Exception e) {
            Utils.Log(e.getLocalizedMessage());
            error("数据解析错误");
            return ;
        }
        result(status, info);
        if (status == API.CODE.SUCCEED){
            success(data);
        }else if (status == API.CODE.PERMISSION_DENIED){
            authorizationFailure();
        }else if (status == API.CODE.Failure){
            failure(info);
        }else{
            error(info);
        }
        super.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        result(-1,"网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info){}
    public abstract void success(T data);
    public void failure(String info){
        cn.hotwoo.alien.servicelife.util.Utils.Toast(info);
    }
    public void authorizationFailure(){
        Utils.Toast("身份失效，请重新登录");}
    public void error(String errorInfo){
        Utils.Toast(errorInfo);
    }

}
