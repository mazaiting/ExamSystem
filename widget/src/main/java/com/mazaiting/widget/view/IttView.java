package com.mazaiting.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mazaiting.widget.R;

/**
 * 拥有一个ImageView和两个TextView
 * @author mazaiting
 * @date 2018/3/23
 */

public class IttView extends LinearLayout {

    private ImageView mIvPicture;
    private TextView mTvTitle;
    private TextView mTvContent;

    public IttView(Context context) {
        this(context, null);
    }

    public IttView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IttView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        getAttributes(context, attrs);
    }

    /**
     * 初始化View
     * @param context 上下文
     */
    private void initView(Context context) {
        View.inflate(context, R.layout.view_ittview, this);
        mIvPicture = this.findViewById(R.id.iv_picture_itt);
        mTvTitle = this.findViewById(R.id.tv_title_itt);
        mTvContent = this.findViewById(R.id.tv_content_itt);
    }

    /**
     * 获取属性
     * @param context 上下文
     * @param attrs 属性
     */
    private void getAttributes(Context context, AttributeSet attrs) {
        // 获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IttView);
        // 获取属性个数
        int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            // 获取对应属性ID
            int index = typedArray.getIndex(i);
            if (R.styleable.IttView_pic_itt == index) {
                int resourceId = typedArray.getResourceId(index, R.mipmap.email);
                mIvPicture.setImageResource(resourceId);
            } else if (R.styleable.IttView_title_itt == index) {
                String title = typedArray.getString(index);
                mTvTitle.setText(title);
            } else if (R.styleable.IttView_content_itt == index) {
                String content = typedArray.getString(index);
                mTvContent.setText(content);
            }
        }
        // 回收
        typedArray.recycle();
    }

    /**
     * 设置显示内容
     * @param content 显示内容
     */
    public void setTvContent(String content) {
        mTvContent.setText(content);
    }
}
