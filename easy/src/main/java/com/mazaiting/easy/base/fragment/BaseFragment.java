package com.mazaiting.easy.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazaiting.easy.app.BaseApplication;
import com.mazaiting.easy.base.interfaces.IView;
import com.mazaiting.easy.base.mvp.IBaseView;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * @author mazaiting
 * @date 2018/2/5
 */

public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements IView, IBaseView{
    /**上下文*/
    protected Context mContext;
    /**当前View*/
    protected View mRootView;
    /***ButterKnife对象，可用于解除绑定*/
    protected Unbinder mUnbinder;
    /**主持人*/
    @Nullable @Inject
    protected T mPresenter;
    /**判断是否加载过数据*/
    protected boolean hasFetchData = false;

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 判断View是否为空
        if (null != mRootView) {
            // 不为空时获取它的父布局
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            // 判断父布局是否为空
            if (null != parent) {
                // 如果不为空，则移除当前布局
                parent.removeView(mRootView);
            }
        } else {
            // 为空时创建View
            mRootView = createView(inflater, container, savedInstanceState);
        }
        // 设置上下文
        mContext = mRootView.getContext();
        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 使用布局填充器获取布局
        View view = inflater.inflate(getContentLayout(), container, false);
        // ButterKnife绑定布局
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 注入当前Fragment
        inject(BaseApplication.getInstance().getApplicationComponent());
        // 视图与Presenter绑定
        attachView();
        // 绑定View
        bindView(view, savedInstanceState);
        // 判断是否懒加载数据
        if (!isLazyFetchData()) {
            // 初始化数据
            initData();
            // 设置为已经加载数据
            hasFetchData = true;
        }
    }

    /**
     * 判断是否是懒加载, 默认为false, 即为非懒加载
     * @return true, 是懒加载；false, 不是懒加载
     */
    protected boolean isLazyFetchData() {
        return false;
    }

    /**
     * 绑定布局
     */
    private void attachView() {
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 获取当前布局
     * @return 当前布局
     */
    @Nullable @Override
    public View getView() {
        return mRootView;
    }

    /**
     * 绑定声明周期
     */
    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 判断是否为空
        if (null != mUnbinder) {
            // 不为空，则使ButterKnife与View解除绑定
            mUnbinder.unbind();
        }
        // 判断是否为空
        if (null != mPresenter) {
            // 不为空，则使Presenter与View解除绑定
            mPresenter.detachView();
        }
    }
}
