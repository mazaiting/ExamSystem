package com.mazaiting.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mazaiting.widget.R;

import java.util.List;

/**
 * 拥有ImageView, TextView, Spinner的布局
 *
 * @author mazaiting
 * @date 2017/9/20
 */

public class ItsView extends LinearLayout {
  /**左侧图片*/
  private ImageView mImageView;
  /**提示文本*/
  private TextView mTextView;
  /**右侧下拉列表*/
  private Spinner mSpinner;
  /**当前选择的位置*/
  private int mPosition;
  /**下拉列表选择监听*/
  private SpinnerSelectListener mSpinnerSelectListener;

  public ItsView(Context context) {
    this(context, null);
  }

  public ItsView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ItsView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    View.inflate(context, R.layout.view_itsview, this);
    mImageView = this.findViewById(R.id.iv_picture_its);
    mTextView = this.findViewById(R.id.tv_title_its);
    mSpinner = this.findViewById(R.id.spin_menu_its);
  }

  /**
   * 获取所有的自定义属性
   * @param context 上下文
   * @param attrs 属性
   */
  private void getAttributes(Context context, AttributeSet attrs) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItsView);
    int count = typedArray.getIndexCount();
    for (int i = 0; i < count; i++){
      int index = typedArray.getIndex(i);
      if (index == R.styleable.ItsView_pic_its) {
        int resourceId = typedArray.getResourceId(index, R.mipmap.email);
        mImageView.setImageResource(resourceId);
      } else if (index == R.styleable.ItsView_txt_its) {
        String string = typedArray.getString(index);
        mTextView.setText(string);
      } else if (index == R.styleable.ItsView_menu_its) {
        int menuId = typedArray.getResourceId(index, R.array.its_view_menu);
        final String[] stringArray = getResources().getStringArray(menuId);
        setSpinnerResources(context, stringArray);
      }
    }
    typedArray.recycle();
  }

  /**
   * 设置Spinner显示资源
   * @param context 上下文
   * @param list 列表
   */
  public void setSpinnerResources(Context context, final List<String> list) {
    ArrayAdapter<String>
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
    setResourcesSpinner(adapter);
  }

  /**
   * 设置Spinner显示资源
   * @param context 上下文
   * @param stringArray 数组资源
   */
  public void setSpinnerResources(Context context, final String[] stringArray) {
    ArrayAdapter<String>
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
    setResourcesSpinner(adapter);
  }

  /**
   * 设置Spinner资源
   * @param adapter 适配器
   */
  private void setResourcesSpinner(ArrayAdapter<String> adapter) {
    // 设置下拉显示资源
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // 设置适配器
    mSpinner.setAdapter(adapter);
    // 条目选择监听
    mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position;
        // 设置监听
        if (null != mSpinnerSelectListener) {
          mSpinnerSelectListener.changed(position);
        }
      }
      @Override public void onNothingSelected(AdapterView<?> parent) {}
    });
  }

  /**
   * 获取下拉列表当前选择的位置
   */
  public int getPosition() {
    return mPosition;
  }

  /**
   * 获取当前选择的条目
   */
  public String getSelectItem(){
    if (null != mSpinner) {
      return mSpinner.getSelectedItem().toString();
    }
    return null;
  }

  /**
   * 设置下拉列表选择监听
   * @param spinnerSelectListener 下拉列表选择监听
     */
  public void setSpinnerSelectListener(SpinnerSelectListener spinnerSelectListener) {
    this.mSpinnerSelectListener = spinnerSelectListener;
  }

  /**
   * 下拉列表选择监听
   */
  public interface SpinnerSelectListener{
    /**
     * 改变
     * @param position 位置
     */
    void changed(int position);
  }
}
