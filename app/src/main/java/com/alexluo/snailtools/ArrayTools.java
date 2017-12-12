package com.alexluo.snailtools;

import java.util.Collection;

/**
 * Created by alexluo on 17-12-11.
 */

public class ArrayTools {


    /**
     * 判断数组是否为空，主要用于判断对象型数据数组，基本类型数组
     * 请使用{@link #isEmpty(Object)}
     * @param srcArray
     * @param <A>
     * @return
     */
    public static <A> boolean isEmpty(A[] srcArray){
        return  srcArray == null || srcArray.length == 0;
    }

    /**
     * 判断数组是否为空，主要用于判断基本类型数组
     * @param srcArray
     * @return
     */
    public static  boolean isEmpty(Object srcArray){
        if(srcArray == null){
            return true;
        }

        if(srcArray instanceof int[]){
            return ((int[])srcArray).length == 0;
        }

        if(srcArray instanceof char[]){
            return ((char[])srcArray).length == 0;
        }


        if(srcArray instanceof float[]){
            return ((float[])srcArray).length == 0;
        }


        if(srcArray instanceof double[]){
            return ((double[])srcArray).length == 0;
        }


        if(srcArray instanceof long[]){
            return ((long[])srcArray).length == 0;
        }

        if(srcArray instanceof short[]){
            return ((short[])srcArray).length == 0;
        }

        if(srcArray instanceof byte[]){
            return ((byte[])srcArray).length == 0;
        }

        if(srcArray instanceof boolean[]){
            return ((boolean[])srcArray).length == 0;
        }

        return isEmpty((Object[]) srcArray);
    }


    /**
     * 判断集合是否为空
     * @param srcArray
     * @param <C>
     * @return
     */
    public static <C> boolean isEmpty(Collection<C> srcArray){
        return  srcArray == null || srcArray.size() == 0;
    }

}
