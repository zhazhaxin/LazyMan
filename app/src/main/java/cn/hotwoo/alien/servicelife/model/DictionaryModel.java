package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

import java.util.HashMap;

import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.callback.Callback;

/**
 * Created by alien on 2015/8/18.
 */
public class DictionaryModel extends AbsModel {


    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
        setHeader();
    }

    public static DictionaryModel getInstance() {

        return getInstance(DictionaryModel.class);
    }

    public void setHeader() {
        HashMap header = new HashMap();
        header.put("apikey", "da45bc6c527a85aae6a06014ac14200e");
        RequestManager.getInstance().setHeader(header);
    }

    public void query(String query, Callback callback) {
        RequestMap param = new RequestMap();
        param.put("showapi_appid", 6215 + "");
        param.put("showapi_sign", "5804ff0d54d24904b76855a4ee66cae6");
        param.put("showapi_timestamp", System.currentTimeMillis() + "");
        param.put("q",query);

        RequestManager.getInstance().post(API.query, param, callback);
    }
}
