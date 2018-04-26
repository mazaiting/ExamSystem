package com.mazaiting.examsystem.base.presenter;

import android.content.SharedPreferences;

import com.mazaiting.easy.base.mvp.IBaseView;
import com.mazaiting.easy.base.presenter.BasePresenter;
import javax.inject.Inject;


/**
 * 带SharedPreferences的Presenter
 * @author mazaiting
 * @date 2018/2/27
 */

public class BaseSharedPreferencesPresenter<T extends IBaseView> extends BasePresenter<T> {
    @Inject
    SharedPreferences mSharedPreferences;

    /**
     * 存入Boolean值
     * @param key 键
     * @param value 值
     */
    protected void putBoolean(String key,boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 存入String值
     * @param key 键
     * @param value 值
     */
    protected void putString(String key,String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 存入int值
     * @param key 键
     * @param value 值
     */
    protected void putInt(String key,int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return Boolean数据
     */
    protected boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return Boolean数据
     */
    protected String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    /**
     * 获取对应键的值
     * @param key 键
     * @return int数据
     */
    protected int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
