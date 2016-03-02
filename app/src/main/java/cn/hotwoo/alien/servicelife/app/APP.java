package cn.hotwoo.alien.servicelife.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
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
public class APP extends MultiDexApplication implements Application.ActivityLifecycleCallbacks{

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

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
