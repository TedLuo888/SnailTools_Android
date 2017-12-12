package com.alexluo.snailtools;

import android.os.Build;

/**
 * Created by alexluo on 17-12-8.
 */

public class SDKTools {


    /**
     * Android版本大于等于6.0
     * @return
     */
    public static boolean isSdkLargeM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    /**
     * Android版本大于等于5.0
     * @return
     */
    public static boolean isSdkLargeLOLLIPOP(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Android版本大于等于4.0
     * @return
     */
    public static boolean isSdkLargeICECREAM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * Android版本大于等于3.0
     * @return
     */
    public static boolean isSdkLargeHONEYCOMB(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * Android版本大于等于4.4
     * @return
     */
    public static boolean isSdkLargeKITKAT(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isSdkLargeJELLY_BEAN(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

}
