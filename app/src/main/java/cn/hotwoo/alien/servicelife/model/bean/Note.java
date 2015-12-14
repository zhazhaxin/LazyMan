package cn.hotwoo.alien.servicelife.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alien on 2015/8/29.
 */
@Table(name="Note")
public class Note extends Model{

    @Column(name="title")
    public String title;
    @Column(name="content")
    public String content;
    @Column(name="time")
    public long time;

    public Note(){}

    public Note(String title,String content,long time){
        this.content=content;
        this.title=title;
        this.time=time;
    }
}
