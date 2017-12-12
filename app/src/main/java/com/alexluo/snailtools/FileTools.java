package com.alexluo.snailtools;

import android.content.Context;
import android.os.Environment;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by alexluo on 17-12-11.
 */

public class FileTools {
    private static final String TAG = "FileTools";
    public static final int FLAG_SHORTER = 1 << 0;
    public static final long KB_IN_BYTES = 1024;
    public static final long MB_IN_BYTES = KB_IN_BYTES * 1024;
    public static final long GB_IN_BYTES = MB_IN_BYTES * 1024;
    public static final long TB_IN_BYTES = GB_IN_BYTES * 1024;
    public static final long PB_IN_BYTES = TB_IN_BYTES * 1024;


    public static final String byteShort = "B" ;
    public static final String kilobyteShort= "KB" ;
    public static final String megabyteShort= "MB" ;
    public static final String gigabyteShort= "GB" ;
    public static final String terabyteShort= "TB" ;
    public static final String petabyteShort= "PB" ;




    /**
     * 格式化文件大小为可读字符串
     * @param fileSize long形式的文件大小
     * @param formatSort true表示只保留一位小数点
     * @return
     */
    public static String getReadableFileSize(long fileSize, boolean formatSort) {
       if(formatSort){
           return formatShortFileSize(fileSize);
       }else{
           return formatFileSize(fileSize);
       }
    }

    /**
     * 获取文件大小
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file){
        if(!file.exists()){
            return 0;
        }
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(file);
            return fis.available();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                closeQuietly(fis);
                LogTools.d(TAG,"close file input stream when get file size");
            }
        }

        return 0;

    }

    /**
     * 获取文件最新修改时间
     * @param filePath
     * @return
     */
    public static long getFileModifyTime(String filePath) {
        if (null == filePath) {
            LogTools.e(TAG,"Invalid param. filePath: " + filePath);
            return 0;
        }

        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return 0;
        }

        return file.lastModified();
    }

    /**
     * 判断输入文件是否存在
     * @param path
     * @return
     */
    public static boolean exists(String path){
        if(StringTools.isEmpty(path) || StringTools.isBlank(path)){
            return false;
        }

        return new File(path).exists();
    }

    /**
     * 判断文件夹中是否存在目标文件
     * @param dir 需要匹配的文件夹
     * @param targetFileName 需要查找是否存在的文件
     * @return
     */
    public static boolean existsInDir(String dir,String targetFileName){
        if(!exists(dir)){
            return false;
        }

        if(StringTools.isEmpty(targetFileName) || StringTools.isBlank(targetFileName)){
            return false;
        }
        File dirFile = new File(dir);

        if(!dirFile.isDirectory()){
            return false;
        }
        String[] children = dirFile.list();
        if(ArrayTools.isEmpty(children)){
            return false;
        }

        for(String child : children){
            if(targetFileName.equals(child)){
                return true;
            }
        }

        return false;
    }

    /**
     * 复制文件,只能复制非目录文件，需要复制目录请使用{@link #copyDir(String, String)}
     * @param srcFile 需要复制的文件
     * @param dstFile 复制之后的文件
     * @return 复制成功返回true，否则返回false
     * @throws IOException
     */
    public static boolean copyFile(String srcFile,String dstFile) throws IOException{

        if(StringTools.isEmpty(srcFile) || StringTools.isEmpty(dstFile)){
            return false;
        }

        File destFile = new File(dstFile);
        File sFile = new File(srcFile);

        if(!sFile.exists()){
            LogTools.e(TAG,"src file does not exists->"+srcFile);
            return false;
        }

        if (destFile.exists()) {
            destFile.delete();
        }
        FileInputStream inputStream = null;
        FileOutputStream out = null;
        try {
            inputStream = new FileInputStream(sFile);
            out = new FileOutputStream(destFile);
        }catch (FileNotFoundException e){

            return false;
        }
        try {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) >= 0) {
                out.write(buffer, 0, bytesRead);
            }
        } finally {
            out.flush();
            try {
                out.getFD().sync();
            } catch (IOException e) {

            }
            out.close();
        }
        return true;
    }

    /**
     * 复制目录
     * @param srcDir 源目录
     * @param dstDir 目标目录
     * @return
     * @throws IOException
     */
    public static boolean copyDir(String srcDir,String dstDir)throws IOException {
        if(StringTools.isEmpty(srcDir) || StringTools.isEmpty(dstDir)){
            return false;
        }

        File srcDirFile = new File(srcDir);
        if(!srcDirFile.exists()){
            LogTools.e(TAG,"src dir is not exists when copy dir ->"+srcDir);
            return false;
        }

        if(!srcDirFile.isDirectory()){
            LogTools.e(TAG,"src dir is not an directory when copy dir ->"+srcDir);
            return false;
        }

        File dstDirFile = new File(dstDir);
        if(!dstDirFile.exists()){
            dstDirFile.mkdirs();
        }

        File[] listFiles = srcDirFile.listFiles();

        if(listFiles == null || listFiles.length == 0){
            LogTools.e(TAG,"src dir is empty  when copy dir ->"+srcDir);
            return false;
        }

        for(File child : listFiles){
            if(child.isFile()){
                copyFile(child.getAbsolutePath(),dstDirFile.getAbsolutePath()+
                File.separator+child.getName());
            }else{
                copyDir(child.getAbsolutePath(),dstDirFile.getAbsolutePath()+
                        File.separator+child.getName());
            }
        }

        return true;
    }

    public static boolean moveFile(String srcFile,String dstFile){
        return false;
    }

    /**
     * 删除目标文件，该文件不能是目录，如果是目录请使用{@link #deleteDir(String)}
     * @param srcFile
     * @return
     */
    public static boolean deleteFile(String srcFile){
        if(StringTools.isEmpty(srcFile) || StringTools.isBlank(srcFile)){
            return false;
        }
        File file = new File(srcFile);
        if(file.exists() && !file.isDirectory()){
            return file.delete();
        }
        return false;
    }

    /**
     * 删除指定路径的目录，包括该目录的子目录以及子文件
     * @param dirFile
     * @return
     */
    public static boolean deleteDir(String dirFile){
        if(StringTools.isEmpty(dirFile) || StringTools.isBlank(dirFile)){
            return false;
        }
        File oldFile = new File(dirFile);
        File file = new File(oldFile.getAbsolutePath() + System.currentTimeMillis());
        oldFile.renameTo(file);
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] list = file.listFiles();
            for (int i = 0; i < list.length; i++) {
                if (list[i].isDirectory()) {
                    deleteDir(list[i].getAbsolutePath());
                } else {
                    list[i].delete();
                }
            }
        }

        file.delete();
        return true;
    }

    /**
     * 根据指定文件名创建文件
     * @param file
     * @return
     */
    public static boolean makeNewFile(String file){
        if(StringTools.isEmpty(file)){
            LogTools.e(TAG,"can't create file that named null or empty string");
            return false;
        }
        File newFile = new File(file);
        boolean success = false;
        if(newFile.exists()){
            return true;
        }else {
            try {
                success = newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                success = false;
            }
        }
        return success;
    }


    /**
     * 在指定目录中创建一些子目录
     * @param parentPath 自定父目录
     * @param dirNames 需要创建的子目录数组
     * @return
     */
    public static boolean mkdirsInParent(String parentPath,String... dirNames){

        if(!exists(parentPath)){
            if(!(new File(parentPath).mkdirs())){
                return false;
            }
        }


        File childDirFile = null;
        boolean success = false;

        for(String child : dirNames){
            childDirFile = new File(parentPath,child);
            success = childDirFile.mkdirs();
            if(!success){
                LogTools.e(TAG,"create dir ->"+child+" fail");
            }
        }

        return success;
    }


    /**
     * 批量创建文件目录，需要指定目录完整路径：
     * 例如：
     * <code>sdcard/Android/TestDirName</code>
     * @param dirNames 需要创建的目录路径集合
     * @return
     */
    public static boolean mkdirs(String... dirNames){
        File childDirFile = null;
        boolean success = false;
        for(String dirPath : dirNames){
            childDirFile = new File(dirPath);
            success = childDirFile.mkdirs();
            if(!success){
                LogTools.e(TAG,"create dir ->"+dirPath+" fail");
            }
        }
        return success;
    }

    /**
     * 判断sdcard是否已经挂载
     * @param context
     * @return
     */
    public static boolean hasSdcard(Context context){
        return  Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED;
    }

    /**
     * 检查目标文件是否有读取权限
     * @param targetFile
     * @return
     */
    public static boolean checkFileReadPermission(String targetFile){
        if(StringTools.isEmpty(targetFile)){
            LogTools.e(TAG,"target file is null");
            return false;
        }
        File file = new File(targetFile);
        if(!file.exists()){
            return false;
        }
        return file.canRead();
    }

    /**
     * 检查目标文件是否有写权限
     * @param targetFile
     * @return
     */
    public static boolean checkFileWritePermission(String targetFile){
        if(StringTools.isEmpty(targetFile)){
            LogTools.e(TAG,"target file is null");
            return false;
        }
        File file = new File(targetFile);
        if(!file.exists()){
            return false;
        }
        return file.canWrite();
    }



    /**
     * 关闭可关闭的数据
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                // Ignore.
            }
        }
    }



    static String formatFileSize( long sizeBytes) {
        return formatBytes(sizeBytes, 0);
    }

    static String formatShortFileSize( long sizeBytes) {
        return formatBytes(sizeBytes, FLAG_SHORTER);
    }

    static String formatBytes(long sizeBytes, int flags) {
        final boolean isNegative = (sizeBytes < 0);
        float result = isNegative ? -sizeBytes : sizeBytes;
        String suffix = byteShort;
        long mult = 1;
        if (result > 900) {
            suffix = kilobyteShort;
            mult = KB_IN_BYTES;
            result = result / 1024;
        }
        if (result > 900) {
            suffix = megabyteShort;
            mult = MB_IN_BYTES;
            result = result / 1024;
        }
        if (result > 900) {
            suffix = gigabyteShort;
            mult = GB_IN_BYTES;
            result = result / 1024;
        }
        if (result > 900) {
            suffix = terabyteShort;
            mult = TB_IN_BYTES;
            result = result / 1024;
        }
        if (result > 900) {
            suffix = petabyteShort;
            mult = PB_IN_BYTES;
            result = result / 1024;
        }
        // Note we calculate the rounded long by ourselves, but still let String.format()
        // compute the rounded value. String.format("%f", 0.1) might not return "0.1" due to
        // floating point errors.
        final int roundFactor;
        final String roundFormat;
        if (mult == 1 || result >= 100) {
            roundFactor = 1;
            roundFormat = "%.0f";
        } else if (result < 1) {
            roundFactor = 100;
            roundFormat = "%.2f";
        } else if (result < 10) {
            if ((flags & FLAG_SHORTER) != 0) {
                roundFactor = 10;
                roundFormat = "%.1f";
            } else {
                roundFactor = 100;
                roundFormat = "%.2f";
            }
        } else { // 10 <= result < 100
            if ((flags & FLAG_SHORTER) != 0) {
                roundFactor = 1;
                roundFormat = "%.0f";
            } else {
                roundFactor = 100;
                roundFormat = "%.2f";
            }
        }

        if (isNegative) {
            result = -result;
        }
        final String roundedString = String.format(roundFormat, result);

        return roundedString+suffix;
    }


}
