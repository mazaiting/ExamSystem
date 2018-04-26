package com.mazaiting.easy.base.presenter;

import com.mazaiting.easy.base.mvp.IBasePresenter;
import com.mazaiting.easy.base.mvp.IBaseView;


/**
 * @author mazaiting
 * @date 2018/2/5
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter<T> {
    protected T mView;

    /**
     * 绑定View
     */
    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    /**
     * 解除View的绑定
     */
    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }
}
