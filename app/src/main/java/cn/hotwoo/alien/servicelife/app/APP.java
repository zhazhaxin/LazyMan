package cn.hotwoo.alien.servicelife.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.activeandroid.ActiveAndroid;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.http.RequestManager;

import cn.hotwoo.alien.servicelife.model.AbsModel;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/7/27.
 */
public class APP extends MultiDexApplication {

    private Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;

        Utils.init(this);
        Utils.setDebugtag("LazyMan");
        FileManager.init(this);
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(true, "LazyManNet");
        Fresco.initialize(this);
        ActiveAndroid.initialize(this);
        AbsModel.init(this);
    }

}
