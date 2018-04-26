package com.mazaiting.easy.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络改变广播接收器-- 基类
 * @author mazaiting
 */
public abstract class BaseNetChangeReceiver extends BroadcastReceiver {
    /**无网络类型*/
    public static final int TYPE_NONE = 0x01;
    /**手机网络类型*/
    public static final int TYPE_MOBILE = 0x02;
    /**无线网络类型*/
    public static final int TYPE_WIFI = 0x03;
    @Override
    public void onReceive(Context context, Intent intent) {
        /**
         * 这个监听网络连接的设置，包括Wifi和移动数据的打开和关闭
         * 最好还是用这个监听，Wifi如果打开，关闭以及链接上可用的连接都会接到监听
         * 这个广播的最大的弊端势必上边两个广播的反应都要慢
         */
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            // 获取连接管理者
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取网络状态信息
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            // 判断网络信息是否为空并且已连接
            if (null != networkInfo && networkInfo.isConnected()) {
                if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                    // 判断是否为Wifi
                    typeWifi(context);
//                    Log.e(TAG, "onReceive: 当前Wifi可用");
//                    Toast.makeText(context, "当前Wifi可用", Toast.LENGTH_SHORT).show();
                } else if (ConnectivityManager.TYPE_MOBILE == networkInfo.getType()) {
                    // 判断是否为移动网络
                    typeMobile(context);
//                    Log.e(TAG, "onReceive: 当前移动网络可用");
//                    Toast.makeText(context, "当前移动网络可用", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 无网络
                typeNone(context);
//                Log.e(TAG, "onReceive: 当前没有网络连接，请确保你已经打开网络!");
//                Toast.makeText(context, "当前没有网络连接，请确保你已经打开网络!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 手机网络
     * @param context 上下文
     */
    protected void typeMobile(Context context) {

    }

    /**
     * 无线网络
     * @param context 上下文
     */
    protected void typeWifi(Context context) {

    }

    /**
     * 无网络
     * @param context 上下文
     */
    protected abstract void typeNone(Context context);

}
