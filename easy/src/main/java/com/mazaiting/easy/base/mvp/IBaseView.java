package com.mazaiting.easy.base.mvp;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * MVP 中的 View 接口
 * @author mazaiting
 * @date 2018/2/5
 */

public interface IBaseView {
    /**
     * 显示正在加载中...
     */
    void onShowLoading();

    /**
     * 显示请求成功
     */
    void onShowSuccess();

    /**
     * 显示请求失败
     * @param message 错误信息
     */
    void onShowFailed(String message);

    /**
     * 显示当前网络不可用
     */
    void onShowNoNet();

    /**
     * 重试
     */
    void onRetry();

    /**
     * 绑定生命周期
     * @param <T> 视图
     * @return LifecycleTransformer
     */
    <T>LifecycleTransformer<T> bindToLife();
}
