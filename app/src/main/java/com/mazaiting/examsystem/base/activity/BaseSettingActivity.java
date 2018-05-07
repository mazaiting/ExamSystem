package com.mazaiting.examsystem.base.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.mazaiting.easy.base.activity.BaseActivity;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.utils.LanguageUtil;
import com.mazaiting.examsystem.utils.SharedPreferencesUtil;

/**
 * 多语言控制的基类Activity
 * Created by mazaiting on 2018/5/2.
 */

public abstract class BaseSettingActivity<T extends BasePresenter> extends BaseActivity<T> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onBeforeView();
        super.onCreate(savedInstanceState);
    }

    /**
     * 在设置布局之前调用此方法
     */
    protected void onBeforeView() {
        // 获取语言
        String language = SharedPreferencesUtil.getString(BaseSettingActivity.this, Config.KEY_LANGUAGE);
        // 设置语言
        LanguageUtil.getInstance().setLanguage(BaseSettingActivity.this, language);
    }
}
