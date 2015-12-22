package cn.hotwoo.alien.servicelife.app;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.http.RequestManager;

import cn.hotwoo.alien.servicelife.model.AbsModel;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;
import cn.hotwoo.oss.OSSManager;

/**
 * Created by alien on 2015/7/27.
 */
public class APP extends Application {

    private Context mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext=this;

        Utils.init(this);
        Utils.setDebugtag("LazyMan");
        FileManager.init(this);

        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(true, "LazyManNet");

        Fresco.initialize(this);
        ActiveAndroid.initialize(this);

        AbsModel.init(this);

        OSSManager.init(mApplicationContext);
    }

}
