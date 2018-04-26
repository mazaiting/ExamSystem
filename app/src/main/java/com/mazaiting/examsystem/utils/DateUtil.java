package com.mazaiting.examsystem.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * Created by mazaiting on 18/4/2.
 */

public class DateUtil {
    /**一小时有60分钟*/
    private static final int MINUTE = 60;
    /**一小时有3600秒*/
    private static final int HOUR = 60 * 60;
    /**将时间格式化为 "2018"形式*/
    public static final String FORMAT_Y = "yyyy";
    /**将时间格式化为 "2018-04"形式*/
    public static final String FORMAT_YM = "yyyy-MM";
    /**将时间格式化为 "2018-04-02"形式*/
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    /**将时间格式化为 "2018-04-02 20"形式*/
    public static final String FORMAT_YMD_H = "yyyy-MM-dd HH";
    /**将时间格式化为 "2018-04-02 20:09"形式*/
    public static final String FORMAT_YMD_HM = "yyyy-MM-dd HH:mm";
    /**将时间格式化为 "2018-04-02 20:09:34"形式*/
    public static final String FORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    /**将时间格式化为 "20:09:34"形式*/
    public static final String FORMAT_HMS = "HH:mm:ss";

    /**
     * 将时间格式化为 xxx 形式
     * xxx 可选值
     *
     * @param currentTimeMillis 当前时间
     * @return 2018-04-02 形式的时间
     */
    public static String getCurrentTime(String format, long currentTimeMillis) {
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    /**
     * 入参是一个每秒减小1的秒数
     * 返回一个格式化的时间
     *
     * 该方法没有局限性，当时间大于24小时时仍然会返回正确的小时数
     * 当时分秒小于10时，不会向前加0，如果需要，可以稍微处理一下再返回
     */
    public static String countTimer(Long time) {
        int countTime = time.intValue();
        return countTime / HOUR + ":" + countTime % HOUR / MINUTE + ":" + countTime % MINUTE;
    }
}
