package cn.hotwoo.alien.servicelife.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by alien on 2015/9/1.
 */
@Table(name="love")
public class Love extends Model{

    @Column(name = "author")
    public String author;
    @Column(name = "title")
    public String title;
    @Column(name="content")
    public String content;
    @Column(name="loveimg")
    public String loveImg;
    @Column(name = "showimg")
    public String showImg;
    @Column(name = "time")
    public long time;


}
