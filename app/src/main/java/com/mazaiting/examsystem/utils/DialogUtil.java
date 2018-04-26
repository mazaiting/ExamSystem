package com.mazaiting.examsystem.utils;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.mazaiting.widget.fragment.PnDialogFragment;

/**
 * 对话框工具类
 * Created by mazaiting on 18/4/11.
 */

public class DialogUtil {
    /**无网络对话框tag*/
    public static final String DIALOG_PN = "dialog_pn";

    // 单例
    private static DialogUtil sDialogUtil = null;
    private DialogUtil(){}
    public static DialogUtil getInstance(){
        if (null == sDialogUtil) {
            synchronized (DialogUtil.class){
                if (null == sDialogUtil) {
                    sDialogUtil = new DialogUtil();
                }
            }
        }
        return sDialogUtil;
    }

    /**
     * 开启Wifi界面
     */
    public void startWifiDialog(AppCompatActivity activity){
        PnDialogFragment testFragment =
                new PnDialogFragment()
                        .setTitle("无网络连接")
                        .setMessage("请在设置页开启无线网络或移动网络！")
                        .setPositiveListener((dialog, which) -> {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            activity.startActivity(intent);
                        })
                        .setNegativeListener(null);

        testFragment.show(activity.getSupportFragmentManager(), DIALOG_PN);
    }

    /**
     * 开启对话框
     * @param title 标题
     * @param message 消息
     * @param listener 确定按钮监听
     * @param manager Fragment管理者
     */
    public void startPnDialog(String title, String message, DialogInterface.OnClickListener listener,
                              FragmentManager manager) {
        if (TextUtils.isEmpty(title)) {
            title = "提示";
        }
        new PnDialogFragment()
                .setTitle(title)
                .setMessage(message)
                .setPositiveListener(listener)
                .show(manager, DIALOG_PN);
    }
}
