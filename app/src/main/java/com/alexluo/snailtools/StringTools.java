package com.alexluo.snailtools;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by alexluo on 17-12-8.
 */

public class StringTools {

    public static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 返回一个字符串的长度，调用该方法不会报空指针异常
     * @param str
     * @return
     */
    public static int lengthSafely(CharSequence str){
        return str == null ? 0:str.length();
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str){
        return TextUtils.isEmpty(str);
    }



    public static boolean isBlank(CharSequence str){
        if(isEmpty(str)){
            return true;
        }

       return str.toString().trim().length() == 0;
    }


    /**
     * 以指定字符格式进行编码字符串
     * @param str
     * @param charset
     * @return
     */
    public static String encodeWithCharset(String str,String charset) {
        if (!isEmpty(str)) {
            try {
                return URLEncoder.encode(str, isEmpty(charset)?DEFAULT_CHARSET:charset);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * 以指定字符格式进行解码码字符串
     * @param str
     * @param charset
     * @return
     */
    public static String decodeWithCharset(String str,String charset) {
        if(!isEmpty(str)){
            try {
                return URLDecoder.decode(str,isEmpty(charset)?DEFAULT_CHARSET:charset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return str;
    }










}
