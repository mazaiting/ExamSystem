package com.mazaiting.examsystem.base.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.mazaiting.examsystem.R;

import butterknife.BindView;

/**
 * 带刷新的Activity
 * @author mazaiting
 * @date 2018/3/23
 */

public abstract class BaseRefreshToolbarActivity<T extends BasePresenter> extends BaseToolbarActivity<T>{
    /**列表*/
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    /**下拉刷新*/
    @BindView(R.id.swipe_layout)
    protected SwipeRefreshLayout mSwipeLayout;
    /**适配器*/
    protected BaseQuickAdapter mAdapter;

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        super.bindView(view, savedInstanceState);
        // 设置下拉刷新的颜色
        mSwipeLayout.setColorSchemeColors(Color.rgb(47,223,189));
        // 设置布局方向
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 创建适配器
        mAdapter = setAdapter();
        // 打开加载动画
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        // 设置适配器
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 设置列表适配器
     * @return 返回适配器
     */
    protected abstract BaseQuickAdapter setAdapter();
}
