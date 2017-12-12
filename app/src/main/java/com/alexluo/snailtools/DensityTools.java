package com.alexluo.snailtools;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by alexluo on 17-12-8.
 */

public class DensityTools {



    /**
     * 获取当前手机的density值
     * @return
     */
    public static int getBestDensity() {
        // TODO Auto-generated method stub
        return DeviceTools.getIntFromBuildProp("qemu.sf.lcd_density",
                DeviceTools.getIntFromBuildProp("ro.sf.lcd_density", DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * 获取屏幕宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){

        return getDisplayMericsFromResource(context).widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context){

        return getDisplayMericsFromResource(context).heightPixels;
    }

    /**
     * 获取ActionBar高度
     * @param context
     * @return
     */
    public static int getActionBarHeight(Context context){
        return ResourceTools.getAttrDimen(context,android.R.attr.actionBarSize);
    }


    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context){

        return ResourceTools.getDimenByName("status_bar_height",context);
    }

    /**
     * 获取虚拟按键栏高度
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context){
        if(hasNavigationBar(context)){
            return ResourceTools.getDimenByName("navigation_bar_height",context);
        }
        return 0;
    }

    /**
     * 判断手机是否有虚拟按键(NavigationBar)
     * @param context
     * @return
     */
    public static boolean hasNavigationBar(Context context){
        boolean hasNavigationBarInConfig = ResourceTools.getBoolByName("config_showNavigationBar",context);
        boolean hasNavigationBarInBuild = "0".equals(DeviceTools.getStringFromBuildProp("qemu.hw.mainkeys"));
        return hasNavigationBarInConfig && hasNavigationBarInBuild;
    }

    /**
     * 获取DisplayMetrics
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMerics(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static DisplayMetrics getDisplayMericsFromResource(Context context){
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 判断当前Activity是否是全屏显示
     * @param activity
     * @return true表示全屏显示，false不是
     */
    public static boolean activityIsFullScreen(Activity activity) {
        WindowManager.LayoutParams p = activity.getWindow().getAttributes();
        return (p.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }


    /**
     * 把用dp表示的尺寸值转化为以px表示的值
     * @param context
     * @param dpValue 用dp表示的尺寸值
     * @return px value
     */
    public static int dpToPx(Context context,int dpValue) {
        return (int) (dpValue * getDisplayMericsFromResource(context).density + 0.5f);
    }

    /**
     * 把用px表示的尺寸值转化为以dp表示的值
     * @param context
     * @param pxValue 用px表示的尺寸值
     * @return dp值
     */
    public static int pxToDp(Context context,float pxValue) {
        return (int) (pxValue / getDisplayMericsFromResource(context).density + 0.5f);
    }

}
