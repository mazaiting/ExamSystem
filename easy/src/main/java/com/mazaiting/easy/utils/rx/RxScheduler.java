package com.mazaiting.easy.utils.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 调度线程控制
 * @author mazaiting
 * @date 2018/2/5
 */

public class RxScheduler {

    /**
     * 设置订阅线程为io线程及通知为主线程
     */
    private static final ObservableTransformer S_OBSERVABLE_TRANSFORMER = new ObservableTransformer() {
        @Override
        public ObservableSource apply(@NonNull Observable upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    /**
     * 创建了调度线程
     */
    public static ObservableTransformer applySchedulers() {
        return S_OBSERVABLE_TRANSFORMER;
    }
}
