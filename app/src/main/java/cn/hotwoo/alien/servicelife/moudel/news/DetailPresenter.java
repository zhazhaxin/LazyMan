package cn.hotwoo.alien.servicelife.moudel.news;

import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;

/**
 * Created by alien on 2015/8/14.
 */
public class DetailPresenter extends BasePresenter<DetailActivity> {

    private String url;
    private String title;

    @Override
    protected void onCreate(DetailActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        url=getView().getIntent().getStringExtra("url");
        title=getView().getIntent().getStringExtra("title");
    }

    @Override
    protected void onCreateView(DetailActivity view) {
        super.onCreateView(view);
        getView().setArticle(url);
    }

    public void shareNews(){

    }
}
