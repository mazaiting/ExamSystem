package com.mazaiting.examsystem.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mazaiting.easy.base.presenter.BasePresenter;
import com.mazaiting.examsystem.R;

import butterknife.BindView;

/**
 * 带有Toolbar的Activity
 * @author mazaiting
 * @date 2018/3/23
 */

public abstract class BaseToolbarActivity<T extends BasePresenter> extends BaseLoadingActivity<T>{
    /**导航栏*/
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    /**
     * 如果重写此方法，在方法体内先调用父类的bindView方法
     * @param view 视图
     * @param savedInstanceState bundle
     */
    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        super.bindView(view, savedInstanceState);
        if (null == mToolbar) {
            throw new RuntimeException("Please use toolbar in your layout file.");
        }
        // 设置导航栏
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
