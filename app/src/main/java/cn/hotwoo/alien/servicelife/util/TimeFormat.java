package cn.hotwoo.alien.servicelife.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alien on 2015/8/10.
 */
public class TimeFormat {

    private Calendar currentTime;

    public TimeFormat(){
        currentTime=Calendar.getInstance();
        currentTime.setTime(new Date());
    }

    /**
     * 返回对应的时间格式
     * @param millisecond  毫秒为单位
     * @param format  格式： y年，M月 d日 H时 m分 s秒  如：yy-MM-dd
     */
    public static String timeToString(long millisecond,String format){
        SimpleDateFormat dateFormat=new SimpleDateFormat(format);
        Date date = new Date(millisecond);
        return dateFormat.format(date);
    }

    public static String getCurrentTimeFormat(String timeFormat){
        return timeToString(System.currentTimeMillis(), timeFormat);
    }

    public interface DateFormat{
        String otherFormat(TimeFormat timeFormat,long millisecond);
    }

    public String toString(DateFormat dateFormat, long millisecond){
        SimpleDateFormat format=new SimpleDateFormat(dateFormat.otherFormat(this,millisecond));
        return format.format(new Date(millisecond));
    }

    public int getYear(){
        return currentTime.get(Calendar.YEAR);
    }
    public int getMonth(){
        return currentTime.get(Calendar.MONTH);
    }
    public int getDayOfMonth(){
        return currentTime.get(Calendar.DAY_OF_MONTH);
    }
    public int getDayOfWeek(){
        return currentTime.get(Calendar.DAY_OF_WEEK);
    }
}
