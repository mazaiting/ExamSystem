package com.mazaiting.examsystem.utils;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * SharedPreferences存储管理工具类
 * Created by mazaiting on 2018/5/2.
 */

public class SharedPreferencesUtil {

    /**
     * 储存字符串
     * @param context 上下文
     * @param key 键
     * @param value 值
     */
    public static void putString(Context context, String key, String value) {
        // 保存语言
        PreferenceManager
                .getDefaultSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply();
    }

    /**
     * 读取字符串
     * @param context 上下文
     * @param key 键
     */
    public static String getString(Context context, String key) {
        // 读取语言
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(key, "");
    }
}
