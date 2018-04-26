package com.mazaiting.easy.utils.rx;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.mazaiting.easy.app.BaseApplication;
import com.mazaiting.easy.config.BaseConfig;
import com.mazaiting.easy.config.Constant;
import com.orhanobut.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 网络工具类
 * @author mazaiting
 * @date 2018/2/6
 */

public class RxUtil {

    /**
     * 提供一个带缓存的OkHttpClient
     * @param application 全局Application
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClientWithCache(BaseApplication application) {
        OkHttpClient.Builder builder = getOkHttpClientBuilder(application);
        // 设置缓存
        builder.cache(getOkHttpCache(application));
        return builder.build();
    }

    /**
     * 提供一个不带缓存的OkHttpClient
     * @param application 全局Application
     * @return OkHttpClient
     */
    public static OkHttpClient getOkHttpClient(BaseApplication application) {
        OkHttpClient.Builder builder = getOkHttpClientBuilder(application);
        return builder.build();
    }

    /**
     * 获取
     * @param application 全局对象
     * @return OkHttpClient.Builder
     */
    private static OkHttpClient.Builder getOkHttpClientBuilder(BaseApplication application) {
        return new OkHttpClient.Builder()
                // 设置网络请求拦截器
                .addInterceptor(getHttpLoggingInterceptor())
                // 设置网页拦截
                .addNetworkInterceptor(new StethoInterceptor())
                // 设置连接超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                // 设置读取超时时间
                .readTimeout(15, TimeUnit.SECONDS)
                // 设置持久化Cookie
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(application)));
    }

    /**
     * 提供一个缓存对象
     * @param application 全局对象
     * @return 缓存对象
     */
    private static Cache getOkHttpCache(BaseApplication application) {
        // 可缓存的大小
        int cacheSize = 20 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    /**
     * 获取日志拦截器
     * @return Http日志拦截器
     */
    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        // 日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        // 新建拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                if (!TextUtils.isEmpty(message)) {
                    Log.d(Constant.TAG, message);
                }
            }
        });
        // 设置显示级别
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
