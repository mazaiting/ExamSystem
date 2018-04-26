package com.mazaiting.examsystem.module.main;

import com.mazaiting.examsystem.base.component.ApplicationComponentImpl;

import dagger.Component;

/**
 * 主页面组件
 * Created by mazaiting on 2018/4/24.
 */

@Component(dependencies = ApplicationComponentImpl.class)
public interface MainComponent {
    /**
     * 注入主页面
     * @param activity 页面
     */
    void inject(MainActivity activity);
}
