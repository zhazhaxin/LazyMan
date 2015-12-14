package cn.hotwoo.alien.servicelife.moudel.main;

import android.os.Bundle;

import com.jude.beam.bijection.Presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hotwoo.alien.servicelife.model.NewsModel;
import cn.hotwoo.alien.servicelife.model.bean.News;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;

/**
 * Created by Administrator on 2015/10/18.
 */
public class ListPresenter extends Presenter<ListFragment> {

    private List<News> cacheData = new ArrayList<>();
    private String type;
    private int page = 0;

    @Override
    protected void onCreate(ListFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        type = getView().getArguments().getString("type");
        refresh();
    }

    @Override
    protected void onCreateView(ListFragment view) {
        super.onCreateView(view);
        if (cacheData.size() > 0) {
            getView().setRefreshData(cacheData);
        }
    }

    public void refresh() {
        if (type.equals("头条")) {
            NewsModel.getInstance().getNewHeadline(0, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    cacheData.clear();
                    getView().setRefreshData((Arrays.asList(data)));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        } else if (type.equals("娱乐")) {
            NewsModel.getInstance().getNewEntertain(0, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    cacheData.clear();
                    getView().setRefreshData((Arrays.asList(data)));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        } else if (type.equals("社会")) {
            NewsModel.getInstance().getNewSocial(0, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    cacheData.clear();
                    getView().setRefreshData((Arrays.asList(data)));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        } else {
            NewsModel.getInstance().getNewTech(0, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    cacheData.clear();
                    getView().setRefreshData((Arrays.asList(data)));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        }
    }

    public void loadMore() {
        if (type.equals("头条")) {
            NewsModel.getInstance().getNewHeadline(page, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    getView().addData(Arrays.asList(data));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        } else if (type.equals("娱乐")) {
            NewsModel.getInstance().getNewEntertain(page, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    getView().addData(Arrays.asList(data));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        } else if (type.equals("社会")) {
            NewsModel.getInstance().getNewSocial(page, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    getView().addData(Arrays.asList(data));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        } else {
            NewsModel.getInstance().getNewTech(page, new DataCallback<News[]>() {
                @Override
                public void success(News[] data) {
                    getView().addData(Arrays.asList(data));
                    cacheData.addAll(Arrays.asList(data));
                    page++;
                    if (data.length < 14) {
                        getView().stopMore();
                    }
                }
            });
        }
    }

}
