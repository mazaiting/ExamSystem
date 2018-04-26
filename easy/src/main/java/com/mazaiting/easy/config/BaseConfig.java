package com.mazaiting.easy.config;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.github.moduth.blockcanary.BlockCanary;
import com.mazaiting.easy.app.AppBlockCanaryContext;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * 配置文件基类
 * @author mazaiting
 * @date 2018/2/5
 *
 * 官方标准字体
    style="@style/TextAppearance.AppCompat.Display4"
    style="@style/TextAppearance.AppCompat.Display3"
    style="@style/TextAppearance.AppCompat.Display2"
    style="@style/TextAppearance.AppCompat.Display1"
    style="@style/TextAppearance.AppCompat.Headline"
    style="@style/TextAppearance.AppCompat.Title"
    style="@style/TextAppearance.AppCompat.Subhead"
    style="@style/TextAppearance.AppCompat.Body2"
    style="@style/TextAppearance.AppCompat.Body1"
    style="@style/TextAppearance.AppCompat.Caption"
    style="@style/TextAppearance.AppCompat.Button"

   Button的Material Design设计
    style="@style/Widget.AppCompat.Button"
    style="@style/Widget.AppCompat.Button.Borderless"
    style="@style/Widget.AppCompat.Button.Borderless.Colored"
    style="@style/Widget.AppCompat.Button.Small"
 */

public abstract class BaseConfig {
    /**
     * 初始化调试参数
     * @param application 全局Application
     */
    public void init(Application application) {
        // 初始化日志打印
        initLogger();
        // 初始化UI卡顿检测工具
        initBlockCanary(application);
        // 初始化网页调试
        initStetho(application);
    }

    /**
     * 初始化日志打印
     */
    private void initLogger() {
        if (isUseLogger() && isDebug()) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    /**
     * 初始化UI卡顿检测工具
     * @param application 全局Application
     */
    private void initBlockCanary(Application application) {
        if (isUseBlockCanary() && isDebug()) {
            BlockCanary.install(application, new AppBlockCanaryContext()).start();
        }
    }

    /**
     * 初始化 在谷歌浏览器中输入 chrome://inspect#device
     * @param application application 全局Application
     */
    private void initStetho(Application application) {
        if (isUseStetho() && isDebug()) {
            Stetho.initializeWithDefaults(application);
        }
    }

    /**
     * 是否处于调试阶段
     * @return true, 调试；false, 发布版
     */
    protected boolean isDebug() {
        return false;
    }

    /**
     * 是否开启内存检测
     * @return true, 开启；false, 不开启
     */
    protected boolean isUseLogger() {
        return false;
    }

    /**
     * 是否开启UI卡顿检测
     * @return true, 开启；false, 不开启
     */
    protected boolean isUseBlockCanary() {
        return false;
    }

    /**
     * 是否开启网页调试
     * @return true, 开启；false, 不开启
     */
    protected boolean isUseStetho() {
        return false;
    }

}
