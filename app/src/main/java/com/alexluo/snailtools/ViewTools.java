package com.alexluo.snailtools;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by alexluo on 17-12-12.
 */

public class ViewTools {


    /**
     * 获取Activity根View
     * <p>
     *     代码来自于QMUI_Android,github地址：https://github.com/QMUI/QMUI_Android
     * </p>
     * @param activity
     * @return
     */
    public static View getActivityRoot(Activity activity) {
        return ((ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT)).getChildAt(0);
    }

}
