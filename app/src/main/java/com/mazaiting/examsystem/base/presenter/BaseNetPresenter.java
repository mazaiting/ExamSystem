package com.mazaiting.examsystem.base.presenter;

import android.content.Context;

import com.mazaiting.easy.base.mvp.IBaseView;
import com.mazaiting.easy.utils.NetUtil;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.receiver.NetChangeReceiver;

import javax.inject.Inject;

/**
 * 检测网络 主持人
 * Created by mazaiting on 18/3/30.
 */

public class BaseNetPresenter<T extends IBaseView> extends BaseSharedPreferencesPresenter<T> {

    @Inject
    Context mContext;

    /**
     * 是否有网络连接
     * @return true, 有网络连接; false, 无网络连接.
     */
    protected boolean isNetConnected() {
        boolean isNet = true;
        int type = getInt(Config.TYPE_NET);
        if (0 == type || NetChangeReceiver.TYPE_NONE == type) {
            boolean isNetworkConnected = NetUtil.isNetworkConnected(mContext);
            if (isNetworkConnected) {
                boolean isWifiEnabled = NetUtil.isWifiEnabled(mContext);
                if (isWifiEnabled) {
                    // 无线网络
                    putTypeNet(NetChangeReceiver.TYPE_WIFI);
                } else {
                    // 移动网络
                    putTypeNet(NetChangeReceiver.TYPE_MOBILE);
                }
                isNet = true;
            } else {
                // 无网络
                putTypeNet(NetChangeReceiver.TYPE_NONE);
                isNet = false;
            }
        }
        return isNet;
    }

    /**
     * 存储网络类型
     * @param type 网络类型
     */
    private void putTypeNet(int type) {
        putInt(Config.TYPE_NET, type);
    }
}
