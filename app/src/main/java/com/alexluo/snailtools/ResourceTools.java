package com.alexluo.snailtools;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by alexluo on 17-12-8.
 */

public class ResourceTools {





    /**************************获取Android隐藏资源**********************************************/

    /**
     * 通过资源名称和类型获取资源ID
     * @param resName 资源名称
     * @param resType 资源类型
     * @param context 上下文
     * @return
     */
    public static int getResourceIdByName(String resName, String resType, Context context){
        return context.getResources().getIdentifier(resName,resType,"android");
    }

    /**
     * 获取Android隐藏的bool资源值
     * @param resName 资源名称
     * @param context 上下文
     * @return
     */
    public static boolean getBoolByName(String resName, Context context){
        int id = getResourceIdByName(resName,"bool",context);
        if(id != 0){
            return context.getResources().getBoolean(id);
        }

        return false;
    }

    /**
     * 获取Android隐藏的dimen值
     * @param resName 资源名称
     * @param context 上下文
     * @return 如果获取失败则返回-1
     */
    public static int getDimenByName(String resName, Context context){
        int id = getResourceIdByName(resName,"dimen",context);
        if(id != 0){
            return context.getResources().getDimensionPixelOffset(id);
        }
        return -1;
    }


    /**
     * 获取Android隐藏的字符串
     * @param resName 资源名称
     * @param context 上下文
     * @return 如果获取失败则返回null
     */
    public static String getStringByName(String resName, Context context){
        int id = getResourceIdByName(resName,"string",context);
        if(id != 0){
            return context.getResources().getString(id);
        }
        return null;
    }


    /**
     * 获取Android隐藏的图片资源
     * @param resName 资源名称
     * @param context 上下文
     * @return 如果获取失败则返回null
     */
    public static Drawable getDrawableByName(String resName, Context context){
        int id = getResourceIdByName(resName,"drawable",context);
        if(id != 0){
            return context.getResources().getDrawable(id);
        }
        return null;
    }



    /**
     * 获取Android隐藏的颜色资源
     * @param resName 资源名称
     * @param context 上下文
     * @return 如果获取失败则返回-1
     */
    public static int getColorByName(String resName, Context context){
        int id = getResourceIdByName(resName,"color",context);
        if(id != 0){
            return context.getResources().getColor(id);
        }
        return -1;
    }



    /**
     * 获取Android隐藏的颜色资源
     * @param resName 资源名称
     * @param context 上下文
     * @return 如果获取失败则返回-1
     */
    public static ColorStateList getColorStateListByName(String resName, Context context){
        int id = getResourceIdByName(resName,"color",context);
        if(id != 0){
            return context.getResources().getColorStateList(id);
        }
        return null;
    }


    /**************************获取主题（Theme）中属性对应的资源**********************************************/


    /**
     * 获取属性对应的float值
     * @param context
     * @param attrRes
     * @return
     */
    public static float getAttrFloatValue(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.getFloat();
    }

    /**
     * 获取属性对应的Color值
     * @param context
     * @param attrRes
     * @return
     */
    public static int getAttrColor(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.data;
    }

    /**
     * 获取属性对应的ColorStateList
     * @param context
     * @param attrRes
     * @return
     */
    public static ColorStateList getAttrColorStateList(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return context.getResources().getColorStateList(typedValue.resourceId);
    }

    /**
     * 获取属性对应的Drawable
     * @param context
     * @param attrRes
     * @return
     */
    public static Drawable getAttrDrawable(Context context, int attrRes){
        int[] attrs = new int[] { attrRes };
        TypedArray ta = context.obtainStyledAttributes(attrs);
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        return drawable;
    }

    /**
     * 获取属性对应的dimen
     * @param context
     * @param attrRes
     * @return
     */
    public static int getAttrDimen(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, DensityTools.getDisplayMerics(context));
    }


    /**
     *
     * @param context
     * @param attrRes
     * @return
     */
    public static boolean getAttrBoolean(Context context, int attrRes){
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        if(typedValue.resourceId != 0){
            return context.getResources().getBoolean(typedValue.resourceId);
        }
        return typedValue.data != 0;
    }








    /****************************将字符串形式的内容解析为所需值***********************************************/


    /**
     * 将字符串形式的整型数字转换为int
     * @param integer
     * @return
     */
    public static int parseInteger(String integer){
        try{
            return Integer.parseInt(integer);
        }catch(NumberFormatException ex){
            return 0;
        }
    }

    /**
     * 将字符串形式的布尔型转换为boolean
     * @param bool
     * @return
     */
    public static boolean parseBool(String bool){
        try{
            return Boolean.parseBoolean(bool);
        }catch(Exception ex){
            return false;
        }
    }

    /**
     * 将文本形式的尺寸值转换为具体数据
     * @param dimen
     * @param res
     * @return
     */
    public static float parseDimen(String dimen,Resources res){
        int length = dimen.length();
        int index  = -1;
        for(int i = 0 ;i<length;i++){
            char ch = dimen.charAt(i);
            if((ch > 'a' && ch < 'z') || (ch > 'A' && ch < 'Z')){
                index = i;
                break;
            }
        }

        String unitStr = dimen.substring(index, length);
        String value = dimen.substring(0,index);
        int unit = -1;
        if("px".equals(unitStr)){
            unit = TypedValue.COMPLEX_UNIT_PX;
        }else if("dp".equals(unitStr) || "dip".equals(unitStr)){
            unit = TypedValue.COMPLEX_UNIT_DIP;
        }else if("sp".equals(unitStr)){
            unit = TypedValue.COMPLEX_UNIT_SP;
        }else if("pt".equals(unitStr)){
            unit = TypedValue.COMPLEX_UNIT_PT;
        }else if("in".equals(unitStr)){
            unit = TypedValue.COMPLEX_UNIT_IN;
        }else if("mm".equals(unitStr)){
            unit = TypedValue.COMPLEX_UNIT_MM;
        }

        return TypedValue.applyDimension(unit, Float.parseFloat(value), res.getDisplayMetrics());


    }











}
