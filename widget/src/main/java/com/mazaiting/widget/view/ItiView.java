package com.mazaiting.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mazaiting.widget.R;

/**
 * 拥有两个ImageView, TextView的布局
 *
 * @author mazaiting
 * @date 2017/9/20
 */

public class ItiView extends LinearLayout {
  /**左侧图片*/
  private ImageView mIvLeftPicture;
  /**中间文本*/
  private TextView mTextView;
  /**右侧图片箭头*/
  private ImageView mIvArrow;

  public ItiView(Context context) {
    this(context, null);
  }

  public ItiView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ItiView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context);
    getAttributes(context, attrs);
  }

  /**
   * 初始化View
   * @param context 上下文
   */
  private void initView(Context context) {
    View.inflate(context, R.layout.view_itiview, this);
    mIvLeftPicture = this.findViewById(R.id.iv_picture_iti);
    mTextView = this.findViewById(R.id.tv_title_iti);
    mIvArrow = this.findViewById(R.id.iv_arrow_iti);
    // 设置默认图片
    mIvArrow.setImageResource(R.mipmap.arrow_right);
  }

  /**
   * 获取所有的自定义属性
   * @param context 上下文
   * @param attrs 属性
   */
  private void getAttributes(Context context, AttributeSet attrs) {
    // 获取属性
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItiView);
    // 获取属性列表
    int count = typedArray.getIndexCount();
    for (int i = 0; i < count; i++){
      int index = typedArray.getIndex(i);
      if (index == R.styleable.ItiView_pic_iti) {
        int resourceId = typedArray.getResourceId(index, R.mipmap.email);
        mIvLeftPicture.setImageResource(resourceId);
      } else if (index == R.styleable.ItiView_txt_iti) {
        String string = typedArray.getString(index);
        mTextView.setText(string);
      }
    }
    // 回收
    typedArray.recycle();
  }

  /**
   * 设置坐标的图片是否显示
   * @param type View.VISIBILE显示，View.GONE不显示
   */
  public void setLeftVisibility(int type){
    mIvLeftPicture.setVisibility(type);
  }

  /**
   * 设置文本
   * @param text 文本内容
   */
  public void setText(@NonNull String text){
    mTextView.setText(text);
  }
}
