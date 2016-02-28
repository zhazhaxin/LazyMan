package cn.hotwoo.alien.servicelife.moudel.lover;

import android.content.Intent;
import android.os.Bundle;

import com.jude.library.imageprovider.ImageProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.LoveModel;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.bean.Love;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.Utils;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import me.nereo.multi_image_selector.MultiImageSelectorFragment;

/**
 * Created by alien on 2015/9/2.
 */
public class LovePublishPresenter extends BasePresenter<LovePublishActivity> implements MultiImageSelectorFragment.Callback{

    public static final int PUBLISH_RESULT_CODE=200;
    public static final int REQUEST_IMAGE=8;
    private Love love;
    private ImageProvider provider;
    private List<String> imgsPath=new ArrayList<>();

    @Override
    protected void onCreate(LovePublishActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        love = new Love();
        love.showImg="";
        provider = new ImageProvider(getView());
    }

    public void setTitle(String title) {
        love.title = title;
    }

    public void setContent(String content) {
        love.content = content;
    }

    public void setAthor(String athor) {
        if (athor == null)
            love.author = UserModel.getInstance().getUserFromFile().getName();
        else
            love.author = athor;
    }

    public void setLoveImg() {
        love.loveImg = UserModel.getInstance().getUserFromFile().getFace();
    }

    public void setTime() {
        love.time = System.currentTimeMillis() / 1000;
    }

    public void setShowImg(String img) {
        love.showImg = love.showImg + img + "-----";
    }


    public void getImgs(){
        Intent intent = new Intent(getView(), MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 18);
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

        getView().startActivityForResult(intent, REQUEST_IMAGE);
    }

    public void publish() {
        getView().showProgress();
        LoveModel.getInstance().publishLove(love, new StatusCallback() {
            @Override
            public void success(String response) {
                Utils.Toast("发布成功");
                getView().setResult(PUBLISH_RESULT_CODE);
                getView().finish();
            }

            @Override
            public void result(int status) {
                super.result(status);
                if (status == 201) {
                    Utils.Toast("标题重复");
                    getView().dismissProgress();
                }
            }
        });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {

    }

    public synchronized void uplaod(final String path){

    }

    @Override
    public void onSingleImageSelected(String path) {

    }

    @Override
    public void onImageSelected(String path) {

    }

    @Override
    public void onImageUnselected(String path) {

    }

    @Override
    public void onCameraShot(File imageFile) {

    }

}
