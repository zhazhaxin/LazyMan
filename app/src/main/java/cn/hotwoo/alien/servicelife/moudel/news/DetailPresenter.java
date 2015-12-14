package cn.hotwoo.alien.servicelife.moudel.news;

import android.os.Bundle;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.listener.SocializeListeners;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.util.ShareManager;
import cn.hotwoo.alien.servicelife.util.Utils;

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
        ShareManager.getInstance(getView()).getController().setShareContent(title + "\n" + url);
        ShareManager.getInstance(getView()).getController().openShare(getView(), new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                if(share_media== SHARE_MEDIA.WEIXIN){
                    Utils.Toast("分享开始");
                    Utils.Toast(i + "");
                    if(i==200){
                        Utils.Toast("分享成功");
                    }
                }
            }
        });
    }
}
