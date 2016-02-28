package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.util.Utils;

/**
 * Created by alien on 2015/8/30.
 */
public class ModifyFacePresenter extends BasePresenter<ModifyFaceActivity> {

    private ImageProvider imageProvider;
    private static String compressPath;
    private OnImageSelectListener imageSelectListener;

    @Override
    protected void onCreate(ModifyFaceActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        imageProvider = new ImageProvider(getView());
        initImageSelectListener();
    }

    public void initImageSelectListener() {
        imageSelectListener = new OnImageSelectListener() {
            @Override
            public void onImageSelect() {
                getView().showProgress("图片压缩中");
            }

            @Override
            public void onImageLoaded(Uri uri) {
                String uriFile = String.valueOf(uri);
                if (uriFile.startsWith("file://")) {
                    String[] division = uriFile.split("//");
                    uriFile = division[1];
                }
                getView().showImage("file://" + compressPath);
                getView().dismissProgress();
            }

            @Override
            public void onError() {
                Utils.Toast("加载失败!");
                getView().dismissProgress();
            }
        };
    }

    public void getImageFromAlbum() {
        imageProvider.getImageFromAlbum(imageSelectListener);
    }

    public void getImageFromCamera() {
        imageProvider.getImageFromCamera(imageSelectListener);
    }

    public void getImageFromNet() {
        imageProvider.getImageFromNet(imageSelectListener);
    }

    public void uploadFace() {
        if (compressPath == null || compressPath.isEmpty()) {
            Utils.Toast("请选择图片");
            return;
        }
        getView().showProgress("图片上传中");

    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        imageProvider.onActivityResult(requestCode, resultCode, data);
    }
}
