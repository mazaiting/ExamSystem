package com.mazaiting.easy.base.interfaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazaiting.easy.app.IApplicationComponent;

/**
 * View 接口， 用于Activity和Fragment中
 * @author mazaiting
 * @date 2018/2/5
 */

public interface IView {

    /**
     * 创建视图
     * @param inflater 布局填充器
     * @param container ViewGroup
     * @param savedInstanceState bundle
     * @return 视图
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 获取布局
     * @return 布局
     */
    View getView();

    /**
     * 获取布局ID
     * @return 布局ID
     */
    int getContentLayout();

    /**
     * 注入ApplicationComponent, 若项目中未使用Dagger2框架，此方法中可不填写任何内容
     * @param applicationComponent Application组件
     */
    void inject(IApplicationComponent applicationComponent);

    /**
     * 绑定布局
     * @param view 视图
     * @param savedInstanceState bundle
     */
    void bindView(View view, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData();
}
