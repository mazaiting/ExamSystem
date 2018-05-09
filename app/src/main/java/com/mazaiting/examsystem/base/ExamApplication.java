package com.mazaiting.examsystem.base;

import com.mazaiting.easy.app.BaseApplication;
import com.mazaiting.easy.config.BaseConfig;
import com.mazaiting.examsystem.BuildConfig;
import com.mazaiting.examsystem.base.component.DaggerApplicationComponentImpl;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.base.module.ApplicationModule;
import com.mazaiting.examsystem.base.module.NetModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * 全局 Application
 *
 * @author mazaiting
 * @date 2018/3/21
 */

public class ExamApplication extends BaseApplication {

    @Override
    protected void initOtherConfig() {
        super.initOtherConfig();

//        initLeakCanary();
    }

    /**
     * 初始化内存检测
     */
    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    @Override
    protected BaseApplication initApp() {
        return this;
    }

    @Override
    protected BaseConfig setConfig() {
        return new Config();
    }

    @Override
    protected void initApplicationComponent() {
        mApplicationComponent
                = DaggerApplicationComponentImpl
                .builder()
                .applicationModule(new ApplicationModule(this))
                .netModule(new NetModule())
                .build();
    }
}
