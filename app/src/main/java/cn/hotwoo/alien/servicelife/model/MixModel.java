package cn.hotwoo.alien.servicelife.model;

import android.content.Context;

/**
 * Created by alien on 2015/8/14.
 */
public class MixModel extends AbsModel {

    @Override
    public void onAppCreate(Context context) {
        super.onAppCreate(context);
    }

    public static MixModel getInstance(){
        return getInstance(MixModel.class);
    }

}
