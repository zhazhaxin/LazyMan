package cn.hotwoo.alien.servicelife.model.callback;

import org.json.JSONException;
import org.json.JSONObject;

import cn.hotwoo.alien.servicelife.config.API;

/**
 * Created by alien on 2015/7/28.
 */
public abstract class StatusCallback extends LinkCallback {

    @Override
    public void onSuccess(String s) {
        super.onSuccess(s);
        try {
            //把字符串s转化成了json格式
            JSONObject jsonObject=new JSONObject(s);
            if(jsonObject.getInt("status")== API.CODE.SUCCEED){
                success(s);
            }
            result(jsonObject.getInt("status"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public abstract void success(String response);
    public void result(int status){}
}
