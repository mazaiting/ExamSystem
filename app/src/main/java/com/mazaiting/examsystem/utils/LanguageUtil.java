package com.mazaiting.examsystem.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * 语言工具类
 * Created by mazaiting on 2018/4/28.
 */

public class LanguageUtil {
    /**单例*/
    private static LanguageUtil sLanguageUtil = null;
    private LanguageUtil(){}
    public static LanguageUtil getInstance() {
        if (null == sLanguageUtil) {
            synchronized (LanguageUtil.class) {
                if (null == sLanguageUtil) {
                    sLanguageUtil = new LanguageUtil();
                }
            }
        }
        return sLanguageUtil;
    }
    /**
     * 设置应用语言为维语或者简体中文
     *
     * @param context 上下文
     */
    public void setLanguage(Context context, String language) {
        setLanguage(context, language, "CN");
    }

    /**
     * 设置应用语言
     *
     * @param context 上下文
     */
    private void setLanguage(Context context, String language, String country) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        // 设置维语
        Locale locale = new Locale(language, country);
        configuration.setLocale(locale);
        context.createConfigurationContext(configuration);
    }

    /**
     * 重启应用
     *
     * @param context 包上下文
     * @param cls     当前类对象
     */
    public void restartActivity(Context context, Class<?> cls) {
        //重启MainActivity
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
