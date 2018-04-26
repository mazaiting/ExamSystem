package com.mazaiting.easy.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mazaiting.easy.app.BaseApplication;
import com.mazaiting.easy.base.interfaces.IView;
import com.mazaiting.easy.base.mvp.IBaseView;
import com.mazaiting.easy.base.presenter.BasePresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 * @author mazaiting
 * @date 2018/2/5
 */

public abstract class BaseActivity<T extends BasePresenter>
        extends RxAppCompatActivity implements IBaseView, IView{
    /**根布局*/
    protected View mRootView;
    /**ButterKnife绑定视图对象*/
    private Unbinder mUnbinder;
    /**主持人*/
    @Nullable
    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 创建布局
        mRootView = createView(null, null, savedInstanceState);
        // 设置布局内容
        setContentView(mRootView);
        // 注入ApplicationComponent
        inject(BaseApplication.getInstance().getApplicationComponent());
        // 绑定布局--与Presenter
        attachView();
        // 绑定布局
        bindView(mRootView, savedInstanceState);
        // 初始化数据
        initData();
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 创建布局
        View view = getLayoutInflater().inflate(getContentLayout(), container);
        // 绑定布局
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public View getView() {
        return mRootView;
    }

    /**
     * 绑定布局
     */
    private void attachView() {
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 与ButterKnife解除绑定
        if (null != mUnbinder) {
            mUnbinder.unbind();
        }
        // 与Presenter解除绑定
        if (null != mPresenter) {
            mPresenter.detachView();
        }
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    /**
     * 开启下一个Activity
     * @param clazz 类字节码
     * @param bundle 数据存储
     */
    protected void nextActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(BaseActivity.this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
