package com.example.admin.appclean.utils;

import com.example.admin.appclean.BuildConfig;

import timber.log.Timber;


public class LogUtil {

    private static boolean isDebug = BuildConfig.DEBUG_MODE;
    private static String TAG = Singleton.getInstance().getAppName();

    public static void Error(String tag, Object object) {
        if (isDebug) {

            Timber.e(tag, object);
        }
    }

    public static void Error(Object object) {
        LogUtil.Error(TAG, object);
    }

    public static void Warn(String tag, Object object) {
        if (isDebug) {
            Timber.w(tag, object);
        }
    }

    public static void Warn(Object object) {
        LogUtil.Warn(TAG, object);
    }

    public static void Debug(String msg) {
        if (isDebug) {
            Timber.d(msg);
        }
    }

    public static void Info(String msg) {
        if (isDebug) {
            Timber.i(msg);
        }
    }
}