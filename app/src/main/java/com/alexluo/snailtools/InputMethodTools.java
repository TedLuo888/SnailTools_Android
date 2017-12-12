package com.alexluo.snailtools;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by alexluo on 17-12-12.
 * <p></p>一些代码来自于QMUI_Android,github地址：https://github.com/QMUI/QMUI_Android
 */

public class InputMethodTools {

    /**
     * 显示软键盘的延迟时间
     */
    public static final int SHOW_KEYBOARD_DELAY_TIME = 200;
    private static final String TAG = "InputMethodTools";
    private final static int KEYBOARD_VISIBLE_THRESHOLD_DP = 100;

    /**
     * 针对给定的editText显示软键盘（editText会先获得焦点）. 可以和{@link #hideKeyboard(View)}
     * 搭配使用，进行键盘的显示隐藏控制。
     *
     */

    public static void showKeyboard(final EditText editText, boolean delay) {
        if (null == editText) {
            return;
        }

        if (!editText.requestFocus()) {
            LogTools.w(TAG, "showSoftInput() can not get focus");
            return;
        }
        if (delay) {
            editText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager imm = (InputMethodManager) editText.getContext().getApplicationContext()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            }, SHOW_KEYBOARD_DELAY_TIME);
        } else {
            InputMethodManager imm = (InputMethodManager) editText.getContext().getApplicationContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * 隐藏软键盘 可以和{@link #showKeyboard(EditText, boolean)}搭配使用，进行键盘的显示隐藏控制。
     *
     * @param view 当前页面上任意一个可用的view
     */
    public static boolean hideKeyboard(final View view) {
        if (null == view) {
            return false;
        }

        InputMethodManager inputManager = (InputMethodManager) view.getContext().getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        // 即使当前焦点不在editText，也是可以隐藏的。
        return inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 设置输入法显示和隐藏监听器
     *
     * @param activity Activity
     * @param listener KeyboardVisibilityEventListener
     */
    @SuppressWarnings("deprecation")
    public static void setVisibilityEventListener(final Activity activity,
                                                  final KeyboardVisibilityEventListener listener) {

        if (activity == null) {
            throw new NullPointerException("Parameter:activity must not be null");
        }

        if (listener == null) {
            throw new NullPointerException("Parameter:listener must not be null");
        }

        final View activityRoot = ViewTools.getActivityRoot(activity);

        final ViewTreeObserver.OnGlobalLayoutListener layoutListener =
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    private final Rect r = new Rect();

                    private final int visibleThreshold = Math.round(
                            DensityTools.dpToPx(activity, KEYBOARD_VISIBLE_THRESHOLD_DP));

                    private boolean wasOpened = false;

                    @Override
                    public void onGlobalLayout() {
                        activityRoot.getWindowVisibleDisplayFrame(r);

                        int heightDiff = activityRoot.getRootView().getHeight() - r.height();

                        boolean isOpen = heightDiff > visibleThreshold;

                        if (isOpen == wasOpened) {
                            // keyboard state has not changed
                            return;
                        }

                        wasOpened = isOpen;

                        listener.onVisibilityChanged(isOpen);
                    }
                };
        activityRoot.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        activity.getApplication()
                .registerActivityLifecycleCallbacks(new ActivityLifeCycle(activity) {
                    @Override
                    protected void onTargetActivityDestroyed() {
                        if (SDKTools.isSdkLargeJELLY_BEAN()) {
                            activityRoot.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(layoutListener);
                        } else {
                            activityRoot.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(layoutListener);
                        }
                    }
                });
    }

    /**
     * 判断输入法是否显示
     *
     * @param activity Activity
     * @return Whether keyboard is visible or not
     */
    public static boolean isKeyboardVisible(Activity activity) {
        Rect r = new Rect();

        View activityRoot = ViewTools.getActivityRoot(activity);
        int visibleThreshold =
                Math.round(DensityTools.dpToPx(activity, KEYBOARD_VISIBLE_THRESHOLD_DP));

        activityRoot.getWindowVisibleDisplayFrame(r);

        int heightDiff = activityRoot.getRootView().getHeight() - r.height();

        return heightDiff > visibleThreshold;
    }


    /**
     * 输入法显示和隐藏回调
     */
    public interface KeyboardVisibilityEventListener {

        /**
         * 在输入法显示和隐藏时会调用该方法
         * @param isOpen
         */
        void onVisibilityChanged(boolean isOpen);
    }


    public static abstract class ActivityLifeCycle implements Application.ActivityLifecycleCallbacks{

        private final Activity mTargetActivity;

        public ActivityLifeCycle(Activity targetActivity) {
            mTargetActivity = targetActivity;
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if (activity == mTargetActivity) {
                mTargetActivity.getApplication().unregisterActivityLifecycleCallbacks(this);
                onTargetActivityDestroyed();
            }
        }

        protected abstract void onTargetActivityDestroyed();
    }



}
