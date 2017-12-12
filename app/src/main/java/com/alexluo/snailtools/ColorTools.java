package com.alexluo.snailtools;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.graphics.Palette;

/**
 * Created by alexluo on 17-12-8.
 */

public class ColorTools {


    private static int  generateColor(float factor, int color1, int color2){
        return Color.rgb((int)(Color.red(color1) * (1.0F - factor) + factor * Color.red(color2)),
                (int)(Color.red(color1) * (1.0F - factor) + factor * Color.green(color2)),
                (int)(Color.red(color1) * (1.0F - factor) + factor * Color.blue(color2)));
    }

    /**
     * 根据Bitmap图片将文本颜色变黑或者变白
     * @param backgroudBitmap 文本背景图
     * @return  文本颜色，如果背景图主色调偏白，返回黑色，反之返回白色
     */
    public static int getReverseTextColorFromBitmap(Bitmap backgroudBitmap){
        Palette.Builder builder = new Palette.Builder(backgroudBitmap).maximumColorCount(12);
        Palette p = builder.generate();
        Palette.Swatch swatch1 = p.getMutedSwatch();
        Palette.Swatch swatch2 = p.getVibrantSwatch();
        int color =-1;
        if(BitmapTools.generateBitmapYAverage(backgroudBitmap)>170){
            if(swatch1!=null&&swatch2!=null){
                color =  generateColor(0.45F, 0xFF000000, generateColor(0.5F, swatch1.getRgb(), swatch2.getRgb()));
            }else if(swatch1==null&&swatch2==null){
                color = 0xE5000000;
            }else if(swatch1!=null){
                color = generateColor(0.45F, 0xFF000000, swatch1.getRgb());
            }else if(swatch2!=null){
                color = generateColor(0.45F, 0xFF000000, swatch2.getRgb());
            }else{
                color = 0xFFFFFFFF;
            }

        }else{
            color = 0xFFFFFFFF;
        }
        if(color!=-1){
            color = 0xE5000000;
        }
        return color;
    }








    /**
     * 解析字符串形式的颜色为具体颜色值，例如可以将“#3399ff”转换为对应的颜色值
     * @param color
     * @return
     */
    public static int parseColor(String color){
        try{
            return Color.parseColor(color);
        }catch(IllegalArgumentException ex){
            return 0;
        }

    }

}
