package com.mazaiting.widget.multistate;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;

import com.mazaiting.widget.R;

/**
 * 状态视图
 * @author mazaiting
 * @date 2018/2/6
 */
public class SimpleMultiStateView extends MultiStateView {
    /**最小显示时间，单位: ms*/
    private static final int MIN_SHOW_TIME = 400;
    /**最小延迟时间，单位: ms*/
    private static final int MIN_DELAY = 600;
    /**当前目标状态，默认值为-1*/
    private int mTargetState = -1;
    /**开始加载时间，默认值为-1*/
    private long mLoadingStartTime = -1;
    /**空视图资源ID*/
    private int mResIdEmpty;
    /**加载视图资源ID*/
    private int mResIdLoading;
    /**失败视图资源ID*/
    private int mResIdFailed;
    /**无网络视图资源ID*/
    private int mResIdNoNet;
    /**动画隐藏任务*/
    private final Runnable mLoadingHideRunnable = new Runnable() {
        @Override
        public void run() {
            // 设置当前视图状态
            setViewState(mTargetState);
            // 设置开始时间为-1
            mLoadingStartTime = -1;
            // 设置当前状态值为-1
            mTargetState = -1;
        }
    };

    /****************************************************************************************
     ************************************构造方法*********************************************
     ****************************************************************************************/
    public SimpleMultiStateView(Context context) {
        this(context, null);
    }

    public SimpleMultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleMultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取配置文件中的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleMultiStateView);
        // 获取空布局资源ID
        mResIdEmpty = typedArray.getResourceId(R.styleable.SimpleMultiStateView_empty, -1);
        // 获取加载布局资源ID
        mResIdLoading = typedArray.getResourceId(R.styleable.SimpleMultiStateView_loading, -1);
        // 获取失败布局资源ID
        mResIdFailed = typedArray.getResourceId(R.styleable.SimpleMultiStateView_fail, -1);
        // 获取无网络布局资源ID
        mResIdNoNet = typedArray.getResourceId(R.styleable.SimpleMultiStateView_no_net, -1);
        // 回收，防止内存泄漏
        typedArray.recycle();
        setViewForStatus();
    }

    /**
     * 根据状态设置视图
     */
    private void setViewForStatus() {
        // 设置空布局
        setViewForStatus(STATE_EMPTY, mResIdEmpty);
        // 设置失败布局
        setViewForStatus(STATE_FAILED, mResIdFailed);
        // 设置加载布局
        setViewForStatus(STATE_LOADING, mResIdLoading);
        // 设置无网络布局
        setViewForStatus(STATE_NO_NET, mResIdNoNet);
    }

    /**
     * 根据状态设置视图
     * @param state 状态
     * @param resId 资源ID
     */
    private void setViewForStatus(int state, int resId) {
        if (-1 != resId) {
            // 根据状态添加视图
            addViewForStatus(state, resId);
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 移除加载隐藏任务
        removeCallbacks(mLoadingHideRunnable);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 移除加载隐藏任务
        removeCallbacks(mLoadingHideRunnable);
    }

    /**
     * 显示进度加载中
     */
    public void showLoadingView() {
        setViewState(STATE_LOADING);
    }

    /**
     * 显示错误视图
     */
    public void showErrorView() {
        if (STATE_CONTENT != getViewState()) {
            this.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setViewState(STATE_FAILED);
                }
            }, 100);
        }
    }

    /**
     * 显示空视图
     */
    public void showEmptyView() {
        if (STATE_CONTENT != getViewState()) {
            this.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setViewState(STATE_EMPTY);
                }
            }, 100);
        }

    }

    /**
     * 显示无网络视图
     */
    public void showNoNetView() {
        if (STATE_CONTENT != getViewState()) {
            this.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setViewState(STATE_NO_NET);
                }
            }, 100);
        }
    }

    /**
     * 显示内容视图
     */
    public void showContent() {
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                setViewState(STATE_CONTENT);
            }
        }, 100);
    }

    @Override
    public void setViewState(int state) {
        // 判断状态是否为加载状态 && 当前的状态是否为加载状态
        if (STATE_LOADING != state && STATE_LOADING == getViewState()) {
            // 计算时间差
            long time = System.currentTimeMillis() - mLoadingStartTime;
            // 判断所用时间是否小于最小显示时间
            if (time < MIN_SHOW_TIME) {
                // 设置目标状态
                mTargetState = state;
                // 执行延时任务
                postDelayed(mLoadingHideRunnable, MIN_DELAY);
            } else {
                // 最小显示时间内，则执行父类方法
                super.setViewState(state);
            }
            return;
        } else if (STATE_LOADING == state) {
            // 判断当前状态是否是加载中，设置开始时间
            mLoadingStartTime = System.currentTimeMillis();
        }
        super.setViewState(state);
    }


    /**
     * 设置空视图的资源ID
     * @param emptyResId 资源ID
     * @return 当前对象
     */
    public SimpleMultiStateView setEmptyResId(@LayoutRes int emptyResId) {
        this.mResIdEmpty = emptyResId;
        addViewForStatus(STATE_EMPTY, mResIdEmpty);
        return this;
    }

    /**
     * 设置失败视图的资源ID
     * @param failedResId 资源ID
     * @return 当前对象
     */
    public SimpleMultiStateView setFailedResId(@LayoutRes int failedResId) {
        this.mResIdFailed = failedResId;
        addViewForStatus(STATE_FAILED, mResIdFailed);
        return this;
    }

    /**
     * 设置加载视图的资源ID
     * @param loadingResId 资源ID
     * @return 当前对象
     */
    public SimpleMultiStateView setLoadingResId(@LayoutRes int loadingResId) {
        this.mResIdLoading = loadingResId;
        addViewForStatus(STATE_LOADING, mResIdLoading);
        return this;
    }

    /**
     * 设置无网络视图的资源ID
     * @param noNetResId 资源ID
     * @return 当前对象
     */
    public SimpleMultiStateView setNoNetResId(@LayoutRes int noNetResId) {
        mResIdNoNet = noNetResId;
        addViewForStatus(STATE_NO_NET, mResIdNoNet);
        return this;
    }

    /**
     * 建造者模式，链式调用
     * @return 当前对象
     */
    public SimpleMultiStateView build() {
        showLoadingView();
        return this;
    }

}