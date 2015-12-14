
package cn.hotwoo.alien.servicelife.wxapi;

import android.os.Bundle;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

import cn.hotwoo.alien.servicelife.util.ShareManager;
import cn.hotwoo.alien.servicelife.util.Utils;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareManager.getInstance(this).getController().openShare(this, new SocializeListeners.SnsPostListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, SocializeEntity socializeEntity) {
                if(i==200){
                    Utils.Toast("分享成功");
                }
            }
        });
    }
}
