package com.alexluo.snailtools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.FloatRange;

/**
 * Created by alexluo on 17-12-8.
 */

public class BitmapTools {


    /**
     * 缩放bitmap
     * @param srcBitmap
     * @param scale 缩放比例，0到1之间
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap srcBitmap,@FloatRange(from = 0.0f,to = 1.0f) float scale,
    boolean recycle){
        if(srcBitmap == null){
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        Bitmap bm =Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix,
                true);
        if(recycle){
            srcBitmap.recycle();
        }
        return bm;
    }

    /**
     * 根据指定区域裁剪Bitmap
     * @param srcBitmap
     * @param dstRect 指定裁剪区域
     * @param recycle 是否回收源Bitmap
     * @return
     */
    public static Bitmap clipBitmap(Bitmap srcBitmap, Rect dstRect,boolean recycle){
        int w = srcBitmap.getWidth();
        int h = srcBitmap.getHeight();
        int clipWidth = dstRect.right - dstRect.left;
        int clipHeight = dstRect.bottom - dstRect.top;
        if(clipWidth > w){
            clipWidth = w;
        }

        if(clipHeight > h){
            clipHeight = h;
        }

        Bitmap bm = Bitmap.createBitmap(srcBitmap, dstRect.left,
                dstRect.top, clipWidth, clipHeight, null, false);
        if(recycle){
            srcBitmap.recycle();
        }
        return bm;
    }

    /**
     * 旋转bitmap
     * @param source
     * @param rotation 旋转角度
     * @param recycle 是否需要回收源bitmap
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap source, int rotation, boolean recycle) {
        if (rotation == 0) {
            return source;
        }
        int w = source.getWidth();
        int h = source.getHeight();
        Matrix m = new Matrix();
        m.postRotate(rotation);
        Bitmap bitmap = Bitmap.createBitmap(source, 0, 0, w, h, m, true);
        if (recycle){
            source.recycle();
        }
        return bitmap;
    }


    /**
     * 获取Bitmap的像素点
     * @param srcDst
     * @param b
     * @return
     */
    public static int[] getBitmapPixels(Bitmap srcDst,boolean b){
        int[] srcBuffer = new int[srcDst.getWidth() * srcDst.getHeight()];
        srcDst.getPixels(srcBuffer,
                0, srcDst.getWidth(), 0, 0, srcDst.getWidth(), srcDst.getHeight());
        return srcBuffer;
    }


    /**
     * 计算读取bitmap时所需要的采样率
     * @param options
     * @param reqWidth 最终需要显示的宽度
     * @param reqHeight 最终需要显示的高度
     * @return
     */
    public static int calculateBitmapInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 获取bitmap占用的内存大小
     * @param targetBitmap
     * @return
     */
    public static int getBitmapSizeInMemory(Bitmap targetBitmap){
        //Android从4.4开始以后使用getAllocationByteCount获取bitmap占用内存大小
        if(SDKTools.isSdkLargeKITKAT()){
            return targetBitmap.getAllocationByteCount();
        }
        return targetBitmap.getByteCount();
    }



    /**
     * 内部自己用
     * @param bitmap
     * @return
     * @hide
     */
    static int generateBitmapYAverage(Bitmap bitmap) {
        bitmap = scaleBitmap(bitmap,0.1f,false);
        int[] pixels = getBitmapPixels(bitmap, true);
        long totalY = 0;
        for (int pixel : pixels) {
            totalY += (Color.red(pixel) * 0.299f + Color.green(pixel) * 0.587f + Color.blue(pixel) * 0.114f);
        }
        return (int) (totalY / pixels.length);
    }

}
