package com.mazaiting.easy.app;

import android.app.Application;

import com.mazaiting.easy.config.BaseConfig;

/**
 * Application基类，使用时只需继承此类即可
 * 1. 继承此类后如果需要进行需要对配置文件进行配置，则必须重写setConfig()方法，返回BaseConfig的子类
 * 2. 继承此类后必须实现initApp()方法，返回this即可。
 * 3. 如果要使用Dagger2框架，则需要重写initApplicationComponent()方法
 * @author mazaiting
 * @date 2018/2/5
 */

public abstract class BaseApplication extends Application {
    /**当前类对象*/
    protected static BaseApplication sBaseApplication;
    /**配置文件对象*/
    protected BaseConfig sConfig = null;
    /**全局应用组件*/
    protected IApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化App
        sBaseApplication = initApp();
        // 设置ApplicationComponent
        initApplicationComponent();
        // 初始化配置文件
        initConfig();
        // 初始化其他配置
        initOtherConfig();
    }
    /**
     * 设置ApplicationComponent
     */
    protected void initApplicationComponent() {}

    /**
     * 初始化App
     * @return 返回当前类对象
     */
    protected abstract BaseApplication initApp();

    /**
     * 初始化配置文件
     */
    private void initConfig() {
        sConfig = setConfig();
        if (null != sConfig) {
            // 初始化各类工具
            sConfig.init(this);
        }
    }

    /**
     * 初始化其他配置，如果有需要则重写
     */
    protected void initOtherConfig() {}

    /**
     * 设置配置文件
     * @return BaseConfig子类对象
     */
    protected BaseConfig setConfig() {
        return null;
    }

    /**
     * 获取Application组件
     * @return IApplicationComponent
     */
    public IApplicationComponent getApplicationComponent() {
        if (null == mApplicationComponent) {
            throw new RuntimeException("mApplicationComponent is null! Please override initApplicationComponent function!");
        }
        return mApplicationComponent;
    }

    /**
     * 获取当前类对象
     */
    public static BaseApplication getInstance() {
        return sBaseApplication;
    }
}
