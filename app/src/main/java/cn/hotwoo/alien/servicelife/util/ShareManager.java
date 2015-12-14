package cn.hotwoo.alien.servicelife.util;

import android.app.Activity;

import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

/**
 * Created by alien on 2015/8/17.
 */
public class ShareManager {

    private UMSocialService mController;

    private static ShareManager instance;
    private ShareManager(Activity activity){
        mController = UMServiceFactory.getUMSocialService("com.umeng.share");
        initShare(activity);
    }

    public static ShareManager getInstance(Activity activity){
        if(instance==null){
            synchronized (ShareManager.class){
                if(instance==null){
                    instance=new ShareManager(activity);
                }
            }
        }
        return instance;
    }

    public void initShare(Activity activity){

        //新浪平台
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        //添加qq平台
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(activity, "1104914024", "dA8aZjRzjeMJqmG8");
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(activity, "1104914024", "dA8aZjRzjeMJqmG8");
        qqSsoHandler.addToSocialSDK();
        qZoneSsoHandler.addToSocialSDK();

        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(activity,"wx3b8290997b8e5c1b","41317b0ec7e164122af50f601c2e86b5");
        wxHandler.addToSocialSDK();
        // 添加微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(activity,"wx3b8290997b8e5c1b","41317b0ec7e164122af50f601c2e86b5");
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    public UMSocialService getController(){
        return mController;
    }

}
