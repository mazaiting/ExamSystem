package com.mazaiting.easy.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mazaiting.easy.base.presenter.BasePresenter;

/**
 * 懒加载Fragment基类
 * @author mazaiting
 * @date 2018/2/5
 */
public abstract class BaseLazyFragment<T extends BasePresenter> extends BaseFragment<T> {
    /**标记视图是否已经准备好*/
    private boolean isViewPrepared;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 标记视图已经创建好
        isViewPrepared = true;
        // 懒加载数据
        lazyFetchDataIfPrepared();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 判断是否用户可见
        if (isVisibleToUser) {
            // 如果可见，则进行懒加载数据
            lazyFetchDataIfPrepared();
        }
    }

    /**
     * 如果准备好，进行懒加载数据
     */
    private void lazyFetchDataIfPrepared() {
        // 判断是否用户可见， 是否为加载过数据， 是否视图已经准备好
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            // 设置加载过数据
            hasFetchData = true;
            // 懒加载数据
            lazyFetchData();
        }
    }

    /**
     * 设置为当前未懒加载的Fragment
     * @return true 为懒加载数据
     */
    @Override
    public boolean isLazyFetchData() {
        return true;
    }

    /**
     * 懒加载数据的抽象方法
     */
    protected abstract void lazyFetchData();

}
