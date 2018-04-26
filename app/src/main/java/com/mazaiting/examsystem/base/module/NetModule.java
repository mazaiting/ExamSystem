package com.mazaiting.examsystem.base.module;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.mazaiting.easy.app.BaseApplication;
import com.mazaiting.easy.utils.rx.RxUtil;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络Module
 * 提供Retrofie实例
 * @author mazaiting
 * @date 2018/3/22
 */
@Module
public class NetModule {
    /**
     * 提供SharedPreference 存储对象
     * @param application 全局Application
     * @return SharedPreferences存储对象
     */
    @Provides
    SharedPreferences providedSharedPreferences(BaseApplication application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    /**
     * 获取Retrofit.Builder对象
     * @param application 全局Application对象
     * @return Retrofit.Builder对象
     */
    @Provides
    Retrofit.Builder providedRetrofit(BaseApplication application) {
        return new Retrofit.Builder()
                // 添加Gson解析
                .addConverterFactory(GsonConverterFactory.create())
                // 添加RxJava适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                // 设置OkHttpClient
                .client(RxUtil.getOkHttpClientWithCache(application));
    }
}
