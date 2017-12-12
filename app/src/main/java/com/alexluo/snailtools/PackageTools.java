package com.alexluo.snailtools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by alexluo on 17-12-8.
 */

public class PackageTools {

    /**
     * 获取application的versionCode
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }catch (PackageManager.NameNotFoundException e){

        }
        return 0;
    }


    /**
     * 获取application的versionName
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }catch (PackageManager.NameNotFoundException e){

        }
        return null;
    }

    /**
     * 获取application名称
     * @param context
     * @return
     */
    public static String getAppName(Context context){
        try{
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }

}
