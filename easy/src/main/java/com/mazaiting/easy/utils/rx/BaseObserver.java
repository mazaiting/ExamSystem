package com.mazaiting.easy.utils.rx;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 自定义通知
 * @author mazaiting
 * @date 2018/2/5
 */

public abstract class BaseObserver<T> implements Observer<T> {
    /**
     * 请求成功
     * @param t 数据
     */
    protected abstract void onSuccess(T t);

    /**
     * 请求失败
     * @param e 异常信息
     */
    protected abstract void onFailed(Throwable e);

    @Override
    public void onNext(@NonNull T t) {
        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailed(e);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onComplete() {

    }
}
