package com.mazaiting.examsystem.module.login;

import com.mazaiting.examsystem.base.component.ApplicationComponentImpl;

import dagger.Component;

/**
 * Created by mazaiting on 2018/4/24.
 */
@Component(dependencies = ApplicationComponentImpl.class)
public interface LoginComponent {
    /**
     * 注入登陆界面
     * @param activity 界面
     */
    void inject(LoginActivity activity);
}
