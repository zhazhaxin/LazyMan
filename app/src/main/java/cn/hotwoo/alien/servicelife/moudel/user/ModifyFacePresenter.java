package cn.hotwoo.alien.servicelife.moudel.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.alibaba.sdk.android.oss.model.OSSException;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.io.File;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.config.API;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.model.callback.StatusCallback;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;
import cn.hotwoo.oss.CompressImgTool;
import cn.hotwoo.oss.OSSManager;
import cn.hotwoo.oss.callback.CallBack;

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
                compressPath = CompressImgTool.compressImageFile(uriFile, FileManager.getInstance().getCacheDir(FileManager.Dir.IMAGE), 200, 200);
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
        OSSManager.resumableUpload(compressPath, new CallBack() {
            @Override
            public void callback(String objectKey) {
                String face = API.ALIYUN_EDNPOINT + File.separator + objectKey;
                UserModel.getInstance().updateFace(face, new StatusCallback() {
                    @Override
                    public void success(String response) {
                        UserModel.getInstance().updateLocalData();
                        Utils.Toast("头像更换成功");
                        getView().finish();
                    }
                });

            }

            @Override
            public void error(OSSException e) {
                Utils.Toast("添加失败");
                getView().dismissProgress();
            }
        });
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        imageProvider.onActivityResult(requestCode, resultCode, data);
    }
}
