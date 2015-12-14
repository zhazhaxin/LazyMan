package cn.hotwoo.alien.servicelife.moudel.note;

import android.os.Bundle;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

import cn.hotwoo.alien.servicelife.app.BasePresenter;
import cn.hotwoo.alien.servicelife.model.bean.Note;
import cn.hotwoo.alien.servicelife.util.Utils;
import de.greenrobot.event.EventBus;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by alien on 2015/8/29.
 */
public class NoteListPresenter extends BasePresenter<NoteListActivity> {

    private List<Note> notes;

    @Override
    protected void onCreate(NoteListActivity view, Bundle savedState) {
        super.onCreate(view, savedState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onCreateView(NoteListActivity view) {
        super.onCreateView(view);
        refreshData();
    }

    public void onEvent(String event){
        refreshData();
    }

    public void refreshData(){
        getData().subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                notes=new Select().from(Note.class).orderBy("id DESC").execute();
                getView().setAdapterData(notes);
            }
        });
    }

    public void deleteRecord(Note note){
        new Delete().from(Note.class).where("id=?", note.getId()).execute();
        getView().removeData(note);
    }

    //试试Rx
    public Observable<List<Note>> getData(){  //被观察者
       return Observable.create(new Observable.OnSubscribe<List<Note>>() {

           @Override
           public void call(Subscriber<? super List<Note>> subscriber) {
               try{
                   subscriber.onNext(notes);//订阅者得到数据以后更新自己的ui
                   subscriber.onCompleted();
               }
               catch (NullPointerException e){
                   Utils.Log("data is null");
               }
           }
       });
    }
}
