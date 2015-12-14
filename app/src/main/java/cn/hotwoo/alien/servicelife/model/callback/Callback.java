package cn.hotwoo.alien.servicelife.model.callback;

import com.jude.http.RequestListener;

/**
 * Created by alien on 2015/7/28.
 */
public abstract class Callback implements RequestListener {

    @Override
    public void onRequest() {

    }

    @Override
    public void onSuccess(String s) {
        success(s);
    }

    @Override
    public void onError(String s) {
        error(s);
    }

    public abstract void success(String s);

    public abstract void error(String s);
}
