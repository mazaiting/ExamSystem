package com.mazaiting.widget.multistate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mazaiting.widget.R;

/**
 * 多状态视图
 * @author mazaiting
 * @date 2018/2/6
 */
public class MultiStateView extends FrameLayout {
    /**内容状态*/
    public static final int STATE_CONTENT = 0x10001;
    /**加载状态*/
    public static final int STATE_LOADING = 0x10002;
    /**空状态*/
    public static final int STATE_EMPTY = 0x10003;
    /**失败状态*/
    public static final int STATE_FAILED = 0x10004;
    /**无网络状态*/
    public static final int STATE_NO_NET = 0x10005;
    /**存储View的数组，key为int类型*/
    private SparseArray<View> mStateViewArray = new SparseArray<>();
    /**存储布局ID的数组，key,value均为int类型*/
    private SparseIntArray mLayoutIdArray = new SparseIntArray();
    /**内容视图*/
    private View mContentView;
    /**当前状态*/
    private int mCurrentState = STATE_CONTENT;
    /**填充监听*/
    private OnInflateListener mOnInflateListener;
    /**重新加载数据监听*/
    private OnReloadListener mOnReLoadListener;

    /****************************************************************************************
     ************************************构造方法*********************************************
     ****************************************************************************************/
    public MultiStateView(Context context) {
        this(context, null);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /****************************************************************************************
     ************************************添加布局**********************************************
     ****************************************************************************************/
    @Override
    public void addView(View child) {
        validContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        validContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        validContentView(child);
        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, index, params);
    }

    /**
     * 改变视图状态
     * @param state 状态类型
     */
    public void setViewState(int state) {
        // 判断当前的视图是否为空，如果为空则直接返回
        if (null == getCurrentView()) {
            return;
        }
        // 判断要设置的状态是否与当前状态相同
        if (state != mCurrentState) {
            // 根据状态获取对应的视图
            View view = getView(state);
            // 将当前视图设置为不可见
            getCurrentView().setVisibility(GONE);
            // 判断对应状态的视图是否可见
            if (null == view) {
                // 获取布局ID
                int resLayoutID = mLayoutIdArray.get(state);
                // 判断布局ID是否有效
                if (0 == resLayoutID) {
                    return;
                }
                // 根据布局填充器加载布局
                view = LayoutInflater.from(getContext()).inflate(resLayoutID, this, false);
                // 根据状态设置布局
                mStateViewArray.put(state, view);
                // 将当前布局添加到父布局中
                addView(view);
                // 判断状态是否为失败
                if (state == STATE_FAILED) {
                    // 获取重试view
                    View bt = view.findViewById(R.id.retry_bt);
                    // 检测是否为空
                    if (null != bt) {
                        // 按钮点击
                        bt.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (mOnReLoadListener != null) {
                                    // 加载
                                    mOnReLoadListener.onReload();
                                    // 设置状态
                                    setViewState(STATE_LOADING);
                                }
                            }
                        });
                    }
                }
            }
            // 设置状态为当前状态
            mCurrentState = state;
            // 设置当前视图可见
            view.setVisibility(VISIBLE);
            // 判断布局填充监听是否为空
            if (null != mOnInflateListener) {
                // 执行布局填充的方法
                mOnInflateListener.onInflate(state, view);
            }
        }
    }

    /**
     * 获取当前状态
     * @return 状态
     */
    public int getViewState() {
        return mCurrentState;
    }

    /**
     * 获取指定状态的View
     * @param state 状态类型
     * @return 指定状态的View
     */
    public View getView(int state) {
        return mStateViewArray.get(state);
    }

    /**
     * 获取当前状态的View
     * @return 当前状态的View
     */
    public View getCurrentView() {
        // 判断当前状态是否可用
        if (-1 == mCurrentState) {
            return null;
        }
        // 根据当前状态获取布局
        View view = getView(mCurrentState);
        // 判断当前状态是否是内容状态，并判断布局是否为空
        if (STATE_CONTENT == mCurrentState && null == view) {
            throw new NullPointerException("content is null!");
        } else if (null == view) {
            throw new NullPointerException("current state view is null! state = " + mCurrentState);
        }
        return view;
    }

    /**
     * 根据状态设置布局ID
     * @param status 布局状态
     * @param resLayoutId 布局ID
     */
    public void addViewForStatus(int status, int resLayoutId) {
        mLayoutIdArray.put(status, resLayoutId);
    }

    /**
     * 设置监听
     * @param onInflateListener 布局填充监听
     */
    public void setOnInflateListener(OnInflateListener onInflateListener) {
        mOnInflateListener = onInflateListener;
    }

    /**
     * 设置重新加载监听
     * @param onReLoadListener 重新加载监听
     */
    public void setOnReLoadListener(OnReloadListener onReLoadListener) {
        this.mOnReLoadListener = onReLoadListener;
    }

    /**
     * 验证过当前内容布局是否可用
     * @param view 视图
     * @return true, 可用；false, 不可用。
     */
    private boolean isValidContentView(View view) {
        // 如果当前内容视图为空
        if (null == mContentView) {
            // 遍历判断是否存在此视图
            for (int i = 0; i < mStateViewArray.size(); i++) {
                if (-1 != mStateViewArray.indexOfValue(view)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 检查当前view是否为内容视图
     * @param view 视图
     */
    private void validContentView(View view) {
        // 判断此视图是否可用
        if (isValidContentView(view)) {
            // 可用为内容布局赋值
            mContentView = view;
            // 设置内容布局
            mStateViewArray.put(STATE_CONTENT, view);
        } else if (STATE_CONTENT != mCurrentState) {
            // 判断当前状态是否为内容状态，如果不等于则设置当前内容状态不可见
            mContentView.setVisibility(GONE);
        }
    }

    /**
     * 重新加载接口
     */
    public interface OnReloadListener {
        /**
         * 重新加载数据
         */
        void onReload();
    }

    /**
     * 布局填充监听
     */
    public interface OnInflateListener {
        /**
         * 布局填充监听方法
         * @param state 状态
         * @param view 布局
         */
        void onInflate(int state, View view);
    }
}