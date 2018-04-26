package com.mazaiting.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mazaiting.widget.R;

/**
 * 拥有ImageView, TextView, EditText的布局
 *
 * @author mazaiting
 * @date 2017/9/20
 */

public class IteView extends LinearLayout {
  /**左侧图片框*/
  private ImageView mImageView;
  /**左侧提示文本*/
  private TextView mTextView;
  /**右侧输入框*/
  private EditText mEditText;

  public IteView(Context context) {
    this(context, null);
  }

  public IteView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public IteView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initView(context);
    getAttributes(context, attrs);
  }

  /**
   * 初始化View
   * @param context 上下文
   */
  private void initView(Context context) {
    // 加载布局
    View.inflate(context, R.layout.view_iteview, this);
    mImageView = this.findViewById(R.id.iv_picture_ite);
    mTextView = this.findViewById(R.id.tv_title_ite);
    mEditText = this.findViewById(R.id.et_content_ite);
  }

  /**
   * 获取所有的自定义属性
   */
  private void getAttributes(Context context, AttributeSet attrs) {
    // 获取属性列表
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IteView);
    // 获取属性个数
    int count = typedArray.getIndexCount();
    for (int i = 0; i < count; i++){
      int index = typedArray.getIndex(i);
      if (index == R.styleable.IteView_pic_ite) {
        int resourceId = typedArray.getResourceId(index, R.mipmap.email);
        mImageView.setImageResource(resourceId);
      } else if (index == R.styleable.IteView_txt_ite) {
        String string = typedArray.getString(index);
        mTextView.setText(string);
      } else if (index == R.styleable.IteView_hint_txt_ite) {
        String hint = typedArray.getString(index);
        mEditText.setHint(hint);
      }
    }
    typedArray.recycle();
  }

  /**
   * 设置显示为密码
   * @param type 密码
   */
  public void setTransformationMethod(TransformationMethod type){
    mEditText.setTransformationMethod(type);
  }

  /**
   * 获取编辑框文本
   * @return 字符串
   */
  public String getText() {
    return mEditText.getText().toString().trim();
  }

  /**
   * 文本
   * @param text 输入的文字
   */
  public void setText(String text){
    mEditText.setText(text);
    setSelection(text.length());
  }

  /**
   * 设置是否可用
   * @param enabled 是否可用
   */
  @Override
  public void setEnabled(boolean enabled){
    mEditText.setEnabled(enabled);
  }

  /**
   * 设置光标位置
   * @param index 光标位置
   */
  public void setSelection(int index){
    mEditText.setSelection(index);
  }
}
