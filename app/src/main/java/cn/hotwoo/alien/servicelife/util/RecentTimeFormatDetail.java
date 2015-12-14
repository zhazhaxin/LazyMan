package cn.hotwoo.alien.servicelife.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alien on 2015/8/29.
 */
public class RecentTimeFormatDetail implements TimeFormat.DateFormat {

    private Calendar calendar=Calendar.getInstance();
    private int hour;

    @Override
    public String otherFormat(TimeFormat timeFormat, long millisecond) {
        calendar.setTime(new Date());
        hour=calendar.get(Calendar.HOUR_OF_DAY);
        long mistime=System.currentTimeMillis()-millisecond;
        if (mistime > 0) {
            int second = (int) (mistime / 1000);
            if (second < 60) {
                if(second==0)
                    return "刚刚";
                return second+"秒前";
            } else if (second / 60 > 0 && second / 3600 < 1) {
                return second / 60+"分"+second % 60+"秒前";
            } else if (second / 3600 > 0 && second / 60 / 60 / 24 < 1) {
                return second / 3600+"小时"+second % 3600/60+"分前";
            } else if (second / 60 / 60 / 24 < 6 && second / 60 / 60 / 24 >= 1) {
                return second / 60 / 60 / 24 + "天前"+(second / 60 / 60 % 24-(24-hour)+1)+"时";
            } else return timeFormat.timeToString(millisecond,"yyyy-MM-dd   HH时:mm分");
        } else {
            long time = -mistime;
            int s = (int) (time / 1000);
            if (s < 60) {
                return s+"秒后";
            } else if (s / 60 > 0 && s / 3600 < 1) {
                return s/60+"分"+s%60+"秒后";
            } else if (s / 3600 > 0 && s / 3600 < 24) {
                return s/3600+"小时"+s%3600/60+"分后";
            } else if (s / 60 / 60 / 24 == 1) {
                return "明天"+(s/3600%24-(24-hour)+1)+"点后";
            } else
                return timeFormat.timeToString(millisecond,"yyyy-MM-dd   HH时:mm分");
        }
    }
}
