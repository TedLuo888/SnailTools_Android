package com.alexluo.snailtools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexluo on 17-12-8.
 */

public class TimeTools {

    /**
     * 格式化为年月日时分秒，例如2017-12-11 13:24:55
     */
    public static final SimpleDateFormat Y_M_D_H_M_S = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 格式化为年月日，例如2017-12-11
     */
    public static final SimpleDateFormat Y_M_D = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 格式化为年月，例如2017-12
     */
    public static final SimpleDateFormat Y_M = new SimpleDateFormat("yyyy-MM");

    /**
     * 格式化为月日，例如12-11
     */
    public static final SimpleDateFormat M_D = new SimpleDateFormat("MM-dd");

    /**
     * 格式化为时分秒，例如13:24:55
     */
    public static final SimpleDateFormat H_M_S = new SimpleDateFormat("HH:mm:ss");

    /**
     * 格式化为时分，例如13:24
     */
    public static final SimpleDateFormat H_M = new SimpleDateFormat("HH:mm");

    /**
     * 格式化为分秒，例如24:55
     */
    public static final SimpleDateFormat M_S = new SimpleDateFormat("mm:ss");


    /**
     * 将long形式的时间数据转换为指定格式的时间字符串
     *
     * @param timeInMillis 需要转换的时间值
     * @param dateFormat 日期格式，可以参考本类中提供的格式，或者自定义格式
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }


    /**
     * 获取当前时间，已long形式输出结果
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }













}
