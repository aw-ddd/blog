package com.dwj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

    /**
     * 当前时间-时间戳
     *
     * @return 根路径
     */
    public static int timestamp() {
        long time = System.currentTimeMillis();
        return (int) (time / 1000);
    }

    /**
     * 获取当前时间-时间（yyyy-MM-dd HH:mm:ss）
     *
     * @return
     */
    public static String timesNow() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 格式化时间，默认“yyyy-MM-dd hh:mm:ss”
     *
     * @param timestamp
     * @return
     */
    public static String parseTimestamp(long timestamp) {
        return parseTimestamp(timestamp, "yyyy-MM-dd hh:mm:ss");
    }

    /**
     * 当前格式化时间
     *
     * @return
     */
    public static String formatNow(String aFmt) {
        Date date = new Date();
        String fmt = aFmt;
        if (fmt == null) {
            fmt = "yyyy-MM-dd hh:mm:ss";
        }
        SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
        String dateStr = dateFmt.format(date);
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param timestamp
     * @param fmt       时间格式化字符串
     * @return
     */
    public static String parseTimestamp(long timestamp, String fmt) {
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
        String dateStr = dateFmt.format(date);
        return dateStr;
    }

    /**
     * 时间转换为时间戳
     *
     * @param time
     * @return
     */
    public static long dateToTimestamp(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(time);
            long ts = date.getTime() / 1000;
            return ts;
        } catch (ParseException e) {
            return 0;
        }
    }

    /**
     * 时间戳(10位)转换为时间
     * @param time
     * @return
     */
    public static String timestampToDate(Long time) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time*1000))));
        return sd;
    }

}
