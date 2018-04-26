package com.mazaiting.widget.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mazaiting.widget.R;

/**
 * 对话框Fragment
 *
 * @author mazaiting
 * @date 2018/3/9
 */

public class LoadingDialogFragment extends DialogFragment {

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        ProgressDialog dialog = new ProgressDialog(getActivity());
//        dialog.setTitle("加载");
//        dialog.setMessage("正在加载中...");
//        dialog.setCancelable(false);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setIndeterminate(true);
//        return dialog;
//    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 请求无标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置点击外部不可关闭对话框
        getDialog().setCanceledOnTouchOutside(false);
        // 设置对话框背景透明
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }
}
