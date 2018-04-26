package com.mazaiting.easy.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mazaiting.easy.R;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.mazaiting.widget.multistate.MultiStateView;
import com.mazaiting.widget.multistate.SimpleMultiStateView;

/**
 * 多状态视图Activity基类
 * @author mazaiting
 * @date 2018/2/7
 */

public abstract class BaseMultiStateActivity<T extends BasePresenter> extends BaseActivity<T> {
    @Nullable
    SimpleMultiStateView mSimpleMultiStateView;

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        initStateView(view);
    }

    /**
     * 初始化状态视图
     * @param view 视图
     */
    private void initStateView(View view) {
        // 获取多状态视图
        mSimpleMultiStateView = view.findViewById(R.id.simpleMultiStateView);
        // 判断是否为空
        if (null == mSimpleMultiStateView) {
            throw new NullPointerException("you have to provide one SimpleMultiStateView and android:id is simpleMultiStateView.");
        }
        // 初始化mSimpleMultiStateView
        mSimpleMultiStateView
//                .setEmptyResId(R.layout.view_empty)
//                .setFailedResId(R.layout.view_failed)
//                .setNoNetResId(R.layout.view_no_net)
//                .setLoadingResId(R.layout.view_loading)
                .build()
                .setOnReLoadListener(new MultiStateView.OnReloadListener() {
                    @Override
                    public void onReload() {
                        onRetry();
                    }
                });
    }

    @Nullable
    public SimpleMultiStateView getSimpleMultiStateView() {
        return mSimpleMultiStateView;
    }

    @Override
    public void onShowLoading() {
        if (null != mSimpleMultiStateView) {
            mSimpleMultiStateView.showLoadingView();
        }
    }

    @Override
    public void onShowSuccess() {
        if (null != mSimpleMultiStateView) {
            mSimpleMultiStateView.showContent();
        }
    }

    @Override
    public void onShowFailed(String message) {
        if (null != mSimpleMultiStateView) {
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void onShowNoNet() {
        if (null != mSimpleMultiStateView) {
            mSimpleMultiStateView.showNoNetView();
        }
    }
}
