package com.mazaiting.examsystem.receiver;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mazaiting.easy.base.receiver.BaseNetChangeReceiver;
import com.mazaiting.examsystem.base.config.Config;

/**
 * 网络改变广播接收器
 * @author mazaiting
 * @date 2018/3/30
 */

public class NetChangeReceiver extends BaseNetChangeReceiver {

    @Override
    protected void typeNone(Context context) {
        saveNetStatus(context, TYPE_NONE);
    }

    @Override
    protected void typeMobile(Context context) {
        saveNetStatus(context, TYPE_MOBILE);
    }

    @Override
    protected void typeWifi(Context context) {
        saveNetStatus(context, TYPE_WIFI);
    }

    /**
     * 存储网络状态
     * @param context 上下文
     * @param type 网络状态
     */
    private void saveNetStatus(Context context, int type) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        sharedPreferences.edit().putInt(Config.TYPE_NET, type).apply();
    }
}
