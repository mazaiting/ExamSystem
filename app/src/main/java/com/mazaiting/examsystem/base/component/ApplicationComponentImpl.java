package com.mazaiting.examsystem.base.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.mazaiting.easy.app.IApplicationComponent;
import com.mazaiting.examsystem.api.UserApi;
import com.mazaiting.examsystem.base.module.ApiModule;
import com.mazaiting.examsystem.base.module.ApplicationModule;
import com.mazaiting.examsystem.base.module.NetModule;

import dagger.Component;

/**
 * 全局ApplicationComponent组件
 * @author mazaiting
 * @date 2018/3/22
 */
@Component(modules = {NetModule.class, ApiModule.class, ApplicationModule.class})
public interface ApplicationComponentImpl extends IApplicationComponent {

    /**
     * 获取SharedPreferences
     * @return SharedPreferences对象
     */
    SharedPreferences getSharedPreferences();

    /**
     * 上下文
     * @return 上下文
     */
    Context getContext();

    /**
     * 用户操作 接口
     * @return 用户操作相关接口
     */
    UserApi getUserApi();
}
