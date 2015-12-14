package cn.hotwoo.alien.servicelife.moudel.setting;

import android.content.Intent;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.UserModel;
import cn.hotwoo.alien.servicelife.moudel.user.LoginActivity;
import cn.hotwoo.alien.servicelife.util.FileManager;
import cn.hotwoo.alien.servicelife.util.Utils;
import de.greenrobot.event.EventBus;

/**
 * Created by alien on 2015/8/17.
 */
public class SetPresenter extends BasePresenter<SetActivity> {

    @Override
    protected void onCreateView(SetActivity view) {
        super.onCreateView(view);

    }

    public void outLogin(){
        boolean deleteObject = FileManager.getInstance().clearDir(FileManager.Dir.OBJECT);
        if(deleteObject){
            getView().startActivity(new Intent(getView(), LoginActivity.class));
            EventBus.getDefault().post(UserModel.UPDATE_USER_DATA);
            getView().finish();
        }
    }

    public void clearData() {
        boolean deleteImage = FileManager.getInstance().clearDir(FileManager.Dir.IMAGE);
        if (deleteImage) {
            Utils.Toast("缓存已经清理");
        }
    }
}
