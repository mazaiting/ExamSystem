package com.mazaiting.widget.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

/**
 * 带取消、确定的DialogFragment
 * Created by mazaiting on 18/4/9.
 */

public class PnDialogFragment extends DialogFragment {
    /**标题*/
    private String mTitle;
    /**提示消息*/
    private String mMessage;
    private DialogInterface.OnClickListener mNegativeListener;
    private DialogInterface.OnClickListener mPositiveListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置标题
        if (!TextUtils.isEmpty(mTitle)) {
            builder.setTitle(mTitle);
        }
        // 设置提示消息
        if (!TextUtils.isEmpty(mMessage)) {
            builder.setMessage(mMessage);
        }
        // 设置取消按钮
        builder.setNegativeButton("取消", mNegativeListener);
        // 设置确定按钮
        builder.setPositiveButton("确定", mPositiveListener);
        // 创建对话框
        AlertDialog dialog = builder.create();
        // 设置点击外部不可取消
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /**
     * 设置标题
     * @param title 标题
     * @return 当前对象
     */
    public PnDialogFragment setTitle(String title) {
        mTitle = title;
        return this;
    }

    /**
     * 设置内容
     * @param message 内容
     * @return 当前对象
     */
    public PnDialogFragment setMessage(String message) {
        mMessage = message;
        return this;
    }

    /**
     * 设置取消按钮监听
     * @param negativeListener 取消按钮监听
     * @return 当前对象
     */
    public PnDialogFragment setNegativeListener(DialogInterface.OnClickListener negativeListener) {
        mNegativeListener = negativeListener;
        return this;
    }

    /**
     * 设置确定按钮
     * @param positiveListener 确定按钮监听
     * @return 当前对象
     */
    public PnDialogFragment setPositiveListener(DialogInterface.OnClickListener positiveListener) {
        mPositiveListener = positiveListener;
        return this;
    }
}
