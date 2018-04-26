package com.mazaiting.easy.app;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.mazaiting.easy.BuildConfig;

import java.io.File;

/**
 * UI 卡顿检测实现的工具类
 * @author mazaiting
 * @date 2018/2/5
 */

public class AppBlockCanaryContext extends BlockCanaryContext {
    /**
     * 获取 版本号和平台名称
     */
    @Override
    public String provideQualifier() {
        return BuildConfig.VERSION_NAME + BuildConfig.FLAVOR;
    }

    /**
     * 获取 用户id
     */
    @Override
    public String provideUid() {
        return "zaitingma@foxmail.com";
    }

    /**
     * 获取 网络类型
     */
    @Override
    public String provideNetworkType() {
        return "设置网络类型";
    }

    /**
     * 获取 发生卡顿的时间
     */
    @Override
    public int provideMonitorDuration() {
        return super.provideMonitorDuration();
    }

    /**
     * 设置 卡顿时间，提供一个卡顿时间入口, 设置为1000ms
     */
    @Override
    public int provideBlockThreshold() {
        return 1000;
    }

    /**
     * 设置 跳转间隔
     */
    @Override
    public int provideDumpInterval() {
        return provideBlockThreshold();
    }

    /**
     * 设置 日志保存的路径
     */
    @Override
    public String providePath() {
        return "/blockcanary/";
    }

    /**
     * 设置 是否显示通知
     */
    @Override
    public boolean displayNotification() {
        return true;
    }

    /**
     * 设置 不开启文件压缩
     */
    @Override
    public boolean zip(File[] src, File dest) {
        return false;
    }

    /**
     * 上传 错误文件
     */
    @Override
    public void upload(File zippedFile) {
        // 不实现，为不上传
    }

}
