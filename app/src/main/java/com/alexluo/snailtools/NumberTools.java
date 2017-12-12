package com.alexluo.snailtools;

import java.math.BigDecimal;

/**
 * Created by alexluo on 17-12-11.
 */

public class NumberTools {

    /**
     * 保留小数点后几位,四舍五入
     * @param input 原始数据
     * @param decimalPlaces 需要保留的小数点位数
     * @return
     */
    public static double format(double input,int decimalPlaces){
        BigDecimal bd = new BigDecimal(input);
        return bd.setScale(decimalPlaces,BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 保留小数点后几位,四舍五入
     * @param input 原始数据
     * @param decimalPlaces 需要保留的小数点位数
     * @return
     */
    public static double format(float input,int decimalPlaces){
        return format((double)input,decimalPlaces);
    }

}
