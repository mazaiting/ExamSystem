package com.mazaiting.easy.base.mvp;

/**
 * MVP 中的 Presenter接口
 * @author mazaiting
 * @date 2018/2/5
 */

public interface IBasePresenter<T extends IBaseView> {
    /**
     * 绑定View
     * @param view view
     */
    void attachView(T view);

    /**
     * 与View解除绑定
     */
    void detachView();
}
