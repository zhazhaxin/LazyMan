package cn.hotwoo.alien.servicelife.moudel.lover;

import android.content.Intent;
import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.LoveModel;
import cn.hotwoo.alien.servicelife.model.bean.Love;
import cn.hotwoo.alien.servicelife.model.callback.DataCallback;

/**
 * Created by alien on 2015/9/1.
 */
public class LoverSpaceListPresenter extends BasePresenter<LoverSpaceListActivity> {

    @Override
    protected void onCreate(LoverSpaceListActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        getData();
    }

    public void getData(){
        LoveModel.getInstance().getLoves(new DataCallback<Love[]>() {
            @Override
            public void success(Love[] data) {
                getView().setData(data);
                if(data.length == 0)
                    getView().showEmpty("");
            }

            @Override
            public void onError(String s) {
                super.onError(s);
                getView().showEmpty("网络错误");
            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                getView().showEmpty("");
            }
        });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LoverSpaceListActivity.PUBLISH_REQUST_CODE&&resultCode== LovePublishPresenter.PUBLISH_RESULT_CODE){
            getData();
        }
    }
}
