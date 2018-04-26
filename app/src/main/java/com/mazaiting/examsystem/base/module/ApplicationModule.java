package com.mazaiting.examsystem.base.module;

import android.content.Context;

import com.mazaiting.easy.app.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 全局应用Module
 * @author mazaiting
 * @date 2018/3/22
 */
@Module
public class ApplicationModule {
    /** 上下文 */
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    /**
     * 提供 Application
     * @return BaseApplication
     */
    @Provides
    BaseApplication providedApplication() {
        return (BaseApplication) context.getApplicationContext();
    }

    /**
     * 提供上下文
     * @return 上下文
     */
    @Provides
    Context providedContext() {
        return context;
    }
}
