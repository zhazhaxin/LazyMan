package cn.hotwoo.alien.servicelife.model.callback;

import com.jude.http.RequestListener;

/**
 * Created by alien on 2015/7/28.
 */
public class LinkCallback implements RequestListener {

    private LinkCallback link;

    public LinkCallback add(LinkCallback callback){
        callback.setLink(this);
        return callback;
    }

    public void setLink(LinkCallback link){
        this.link=link;
    }

    @Override
    public void onRequest() {
        if(link!=null){
            link.onRequest();
        }
    }

    @Override
    public void onSuccess(String s) {
        if(link!=null){
            link.onSuccess(s);
        }
    }

    @Override
    public void onError(String s) {
        if(link!=null){
            link.onError(s);
        }
    }
}
