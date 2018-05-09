package com.mazaiting.examsystem.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mazaiting.easy.base.activity.BaseActivity;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.utils.DialogUtil;
import com.mazaiting.widget.fragment.LoadingDialogFragment;

/**
 * 加载页面基类
 * @author mazaiting
 * @date 2018/3/23
 */

public abstract class BaseLoadingActivity<T extends BasePresenter> extends BaseSettingActivity<T>{

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
        mLoadingDialogFragment.show(getSupportFragmentManager(), Config.DIALOG_LOADING);
    }

    @Override
    public void onShowSuccess() {
        closeLoadingDialog();
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

    @Override
    public void onShowFailed(String message) {
        closeLoadingDialog();
    }

    @Override
    public void onShowNoNet() {
        DialogUtil.getInstance().startWifiDialog(this);
        Toast.makeText(this, "无网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRetry() {

    }
}
