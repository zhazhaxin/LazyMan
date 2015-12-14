package cn.hotwoo.alien.servicelife.moudel.note;

import android.os.Bundle;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.bean.Note;
import cn.hotwoo.alien.servicelife.util.Utils;
import de.greenrobot.event.EventBus;

/**
 * Created by alien on 2015/8/29.
 */
public class NotePublishPresenter extends BasePresenter<NotePublishActivity> {

    private Note note;

    @Override
    protected void onCreate(NotePublishActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        note = new Note();
        Utils.Log("onCreate");
    }

    public void setTitle(String title) {
        note.title = title;
    }

    public void setContent(String content) {
        note.content = content;
    }

    public void setTime(long time) {
        note.time = time;
    }

    public void submitData(){
        setTime(System.currentTimeMillis());
        note.save();
        Utils.Toast("保存成功");
        EventBus.getDefault().post("记录成功");
        getView().finish();
    }

}
