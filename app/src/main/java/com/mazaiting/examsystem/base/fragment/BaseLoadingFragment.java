package com.mazaiting.examsystem.base.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mazaiting.easy.base.fragment.BaseLazyFragment;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.widget.fragment.LoadingDialogFragment;

/**
 * 带有加载进度条的Fragment基类
 * Created by mazaiting on 2018/4/25.
 */

public abstract class BaseLoadingFragment<T extends BasePresenter> extends BaseLazyFragment<T>{
    /**加载进度条*/
    protected LoadingDialogFragment mLoadingDialogFragment;

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onShowLoading() {
        if (null == mLoadingDialogFragment) {
            // 创建进度条Fragment
            mLoadingDialogFragment = new LoadingDialogFragment();
        }
        // 显示
        mLoadingDialogFragment.show(getActivity().getSupportFragmentManager(), Config.DIALOG_LOADING);
    }

    @Override
    public void onShowSuccess() {
        closeLoadingDialog();
    }

    @Override
    public void onShowFailed(String message) {
        closeLoadingDialog();
    }

    @Override
    public void onShowNoNet() {
//        DialogUtil.getInstance().startWifiDialog(getActivity());
        Toast.makeText(getActivity(), "无网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetry() {

    }

    /**
     * 关闭对话框
     */
    private void closeLoadingDialog() {
        // 可用
//        if (mLoadingDialogFragment.isCancelable()) {
//            mLoadingDialogFragment.dismiss();
//        }
        // 可用，如果出现问题，使用上方的代码
        mLoadingDialogFragment.dismissAllowingStateLoss();
    }
}
