package com.test.weatherapp.util;

import android.util.Log;

import com.test.weatherapp.BuildConfig;

/**
 * Created by alex-balandin on 8/9/18
 */
public class Logging {
    private static boolean ENABLED = BuildConfig.BUILD_TYPE == "debug";

    public static void c(String logTag, Object... messages) {
        String message = concatAsString(messages);
        d(logTag, message);
    }

    public static void d(String logTag, String logMessage) {
        if (ENABLED) {
            Log.d(logTag, logMessage);
        }
    }

    public static void d(String logTag, Object... messages) {
        if (ENABLED) {
            Log.d(logTag, concatAsString(messages));
        }
    }

    public static void d(String logTag, Throwable e, Object... messages) {
        if (ENABLED) {
            Log.d(logTag, concatAsString(messages), e);
        }
    }


    public static void i(String logTag, String logMessage) {
        if (ENABLED) {
            Log.i(logTag, logMessage);
        }
    }

    public static void i(String logTag, Object... messages) {
        if (ENABLED) {
            Log.i(logTag, concatAsString(messages));
        }
    }


    public static void w(String logTag, Object... messages) {
        if (ENABLED) {
            Log.w(logTag, concatAsString(messages));
        }
    }

    public static void w(String logTag, Throwable e, Object... messages) {
        if (ENABLED) {
            Log.w(logTag, concatAsString(messages), e);
        }
    }


    public static void e(String logTag, Object... messages) {
        if (ENABLED) {
            Log.e(logTag, concatAsString(messages));
        }
    }

    public static void e(String logTag, Throwable e, Object... messages) {
        if (ENABLED) {
            Log.e(logTag, concatAsString(messages), e);
        }
    }

    private static String concatAsString(Object... message) {
        StringBuilder sb = new StringBuilder();
        for (Object item : message) {
            sb.append(item);
        }
        return sb.toString();
    }
}
