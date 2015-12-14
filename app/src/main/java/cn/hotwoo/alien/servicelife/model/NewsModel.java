package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

import com.jude.http.RequestManager;

import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.bean.News;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;


/**
 * Created by alien on 2015/7/27.
 */
public class NewsModel extends AbsModel {

    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
    }

    public static NewsModel getInstance() {
        return AbsModel.getInstance(NewsModel.class);
    }


    public void getHotMessage(DataCallback<News[]> callback) {
        RequestManager.getInstance().get(API.getNews, callback);
    }

    //type:headline,tech,entertain,social
    public void getNewHeadline(int page, DataCallback<News[]> callback) {
        RequestManager.getInstance().get(API.getNewHeadline + page, callback);
    }

    public void getNewTech(int page, DataCallback<News[]> callback) {
        RequestManager.getInstance().get(API.getNewTech + page, callback);
    }

    public void getNewEntertain(int page, DataCallback<News[]> callback) {
        RequestManager.getInstance().get(API.getNewEntertain + page, callback);
    }

    public void getNewSocial(int page, DataCallback<News[]> callback) {
        RequestManager.getInstance().get(API.getNewSocial + page, callback);
    }

}
