package cn.hotwoo.alien.servicelife.moudel.lover;

import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;

/**
 * Created by alien on 2015/9/3.
 */
public class LookImagePresenter extends BasePresenter<LookImageActivity> {

    private String[] imgs;

    @Override
    protected void onCreate(LookImageActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        imgs= (String[]) getView().getIntent().getSerializableExtra(LoverSpaceListActivity.IMGS);
    }

    public String[] getImgs(){
        return imgs;
    }
}
