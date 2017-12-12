package com.alexluo.snailtools;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by alexluo on 17-12-8.
 */

public class DeviceTools {
    private static final String TAG = "DeviceTools";
    private static final String GET_METHOD_NAME = "get";
    private static final String GET_INT_METHOD_NAME = "getInt";
    private static final String GET_LONG_METHOD_NAME = "getLong";
    private static final String GET_BOOLEAN_METHOD_NAME = "getBoolean";

    private static Class<?> SystemProperties;

    /**
     * 只有一个参数的获取字符串的get方法
     */
    private static Method getString1;

    /**
     * 有两个参数的获取字符串的get方法
     */
    private static Method getString2;

    /**
     * 获取整型的get方法
     */
    private static Method getInt;



    /**
     * 获取长整型的get方法
     */
    private static Method getLong;


    /**
     * 获取布尔型的get方法
     */
    private static Method getBoolean;

    /**
     * 类加载时先加载需要反射调用的class和method，提高调用具体
     * 方法时的性能
     */
    static {
        try {
            SystemProperties = Class.forName("android.os.SystemProperties");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            LogTools.e(TAG,"get SystemProperties catched exception->"+e);
        }

        try{
            if(SystemProperties != null) {
                getString1 = SystemProperties.getDeclaredMethod(GET_METHOD_NAME,String.class);
                getString1.setAccessible(true);

                getString2 = SystemProperties.getDeclaredMethod(GET_METHOD_NAME,String.class,String.class);
                getString2.setAccessible(true);

                getInt = SystemProperties.getDeclaredMethod(GET_INT_METHOD_NAME,String.class,int.class);
                getInt.setAccessible(true);

                getLong = SystemProperties.getDeclaredMethod(GET_LONG_METHOD_NAME,String.class,long.class);
                getLong.setAccessible(true);

                getBoolean = SystemProperties.getDeclaredMethod(GET_BOOLEAN_METHOD_NAME,String.class,boolean.class);
                getBoolean.setAccessible(true);
            }

        }catch (NoSuchMethodException e){
            e.printStackTrace();
            LogTools.e(TAG,"get get() method catched exception->"+e);
        }

    }


    /**
     * 从build.prop获取指定key的字符串属性值
     * @param key  属性值的key，例如"ro.build.display.id"等
     * @param defaultValue 默认值，当获取不到该key对应的值时所使用的默认值
     * @return
     */
    public static String getStringFromBuildProp(String key,String defaultValue){
        if(getString2 != null){
            try {
                return (String)getString2.invoke(null, key, defaultValue);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    /**
     * 从build.prop获取指定key的字符串属性值
     * @param key 属性值的key，例如"ro.build.display.id"等
     * @return
     */
    public static String getStringFromBuildProp(String key){
        if(getString1 != null){
            try {
                return (String)getString1.invoke(null, key);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return "";
    }


    /**
     * 从build.prop获取指定key的整型属性值
     * @param key 属性值的key，例如"ro.build.version.sdk"等
     * @param defaultValue  默认值，当获取不到该key对应的值时所使用的默认值
     * @return
     */
    public static int getIntFromBuildProp(String key,int defaultValue){
        if(getInt != null){
            try {
                return (int)getInt.invoke(null, key, defaultValue);
            }catch (Exception e){
                LogTools.e(TAG,"get int value catched exception->"+e);
                e.printStackTrace();
            }
        }
        return defaultValue;
    }



    /**
     * 从build.prop获取指定key的长整型属性值
     * @param key 属性值的key，例如"ro.build.version.sdk"等
     * @param defaultValue  默认值，当获取不到该key对应的值时所使用的默认值
     * @return
     */
    public static long getLongFromBuildProp(String key,long defaultValue){
        if(getLong != null){
            try {
                return (long)getLong.invoke(null, key, defaultValue);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return defaultValue;
    }





    /**
     * 从build.prop获取指定key的布尔型属性值
     * @param key 属性值的key，例如"ro.build.version.sdk"等
     * @param defaultValue  默认值，当获取不到该key对应的值时所使用的默认值
     * @return
     */
    public static boolean getBooleanFromBuildProp(String key,boolean defaultValue){
        if(getBoolean != null){
            try {
                return (boolean)getBoolean.invoke(null, key, defaultValue);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return defaultValue;
    }






    /**
     * 获取手机IMEI号
     * @param context
     * @return 如果有IMEI就返回字符串形式的IMEI
     */
    public static String getIMEI(Context context){
        TelephonyManager tpm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tpm.getDeviceId();
    }


    /**
     *获取手机型号
     * @return
     */
    public static String getModel(){
        return Build.MODEL;
    }




    /**
     * 判断是否平板设备
     * @param context
     * @return 如果是平板返回true，否则返回false
     */
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }







}
