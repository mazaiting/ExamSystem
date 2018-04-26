package com.mazaiting.examsystem.module.exam.content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.bean.ExamBean;
import com.mazaiting.examsystem.module.exam.ExamMainActivity;
import com.mazaiting.examsystem.module.exam.event.IEventProtocol;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 内容Fragment
 * Created by mazaiting on 2018/4/24.
 */

public class ExamContentFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    /**
     * 绑定视图对象
     */
    private Unbinder mUnbinder;
    /**
     * 题目名称
     */
    @BindView(R.id.exam_content_tv_name)
    TextView mTvName;
    /**
     * 选项A
     */
    @BindView(R.id.exam_content_rg_option_a)
    RadioButton mRgOptionA;
    /**
     * 选项B
     */
    @BindView(R.id.exam_content_rg_option_b)
    RadioButton mRgOptionB;
    /**
     * 选项C
     */
    @BindView(R.id.exam_content_rg_option_c)
    RadioButton mRgOptionC;
    /**
     * 选项D
     */
    @BindView(R.id.exam_content_rg_option_d)
    RadioButton mRgOptionD;
    /**
     * 选项按钮组
     */
    @BindView(R.id.exam_content_rg_option)
    RadioGroup mRgOption;
    /**多选按钮组*/
    @BindView(R.id.exam_content_ll_more_option)
    LinearLayout mLlMoreOption;
    /**多选A*/
    @BindView(R.id.exam_content_cb_option_a)
    CheckBox mCbOptionA;
    /**多选B*/
    @BindView(R.id.exam_content_cb_option_b)
    CheckBox mCbOptionB;
    /**多选C*/
    @BindView(R.id.exam_content_cb_option_c)
    CheckBox mCbOptionC;
    /**多选D*/
    @BindView(R.id.exam_content_cb_option_d)
    CheckBox mCbOptionD;
    /**
     * 按钮数组
     */
    private final int[] IDS_RG = {
            R.id.exam_content_rg_option_a,
            R.id.exam_content_rg_option_b,
            R.id.exam_content_rg_option_c,
            R.id.exam_content_rg_option_d,
    };
    /**
     * 多选按钮数组
     */
    private final int[] IDS_LL = {
            R.id.exam_content_cb_option_a,
            R.id.exam_content_cb_option_b,
            R.id.exam_content_cb_option_c,
            R.id.exam_content_cb_option_d,
    };
    /**存放多选选项*/
    private SparseArray<String> mSparseArray = new SparseArray<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setListener();
        return view;
    }

    /**
     * 设置监听事件
     */
    private void setListener() {
        // 设置单选按钮组点击监听
        mRgOption.setOnCheckedChangeListener((group, checkedId) -> {
            String itemId = "";
            switch (checkedId) {
                case R.id.exam_content_rg_option_a:
                    itemId = (String) mRgOptionA.getTag();
                    break;
                case R.id.exam_content_rg_option_b:
                    itemId = (String) mRgOptionB.getTag();
                    break;
                case R.id.exam_content_rg_option_c:
                    itemId = (String) mRgOptionC.getTag();
                    break;
                case R.id.exam_content_rg_option_d:
                    itemId = (String) mRgOptionD.getTag();
                    break;
                default:
                    break;
            }
            updateItem(itemId);
        });

        // 设置复选框选择状态改变监听
        mCbOptionA.setOnCheckedChangeListener(this);
        mCbOptionB.setOnCheckedChangeListener(this);
        mCbOptionC.setOnCheckedChangeListener(this);
        mCbOptionD.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // 获取按钮对应的选项ID
        String itemId = (String) buttonView.getTag();
        // 判断是否为选中，如果选中则保存，如果未选中则移除
        if (isChecked) {
            mSparseArray.put(buttonView.getId(), itemId);
        } else {
            mSparseArray.remove(buttonView.getId());
        }
        // 初始化StringBuilder对象
        StringBuilder builder = new StringBuilder();
        // 遍历选项数组
        for (int i = 0; i < mSparseArray.size(); i++) {
            int key = mSparseArray.keyAt(i);
            if (0 != i) {
                builder.append("-");
            }
            builder.append(mSparseArray.get(key));
        }
        // 更新条目
        updateItem(builder.toString());
    }

    /**
     * 设置题目内容
     *
     * @param question 标题列表
     */
    private void setTitleContent(ExamBean.QuestionListBean question) {
        // 设置题目内容
        mTvName.setText(question.getTitleName());
        // 判断题目选项类型，1 为单选，2 为多选
        if ("1".equals(question.getTitleType())) {
            // 设置单选按钮组可见
            mRgOption.setVisibility(View.VISIBLE);
            // 设置多选复选框不可见
            mLlMoreOption.setVisibility(View.GONE);
            setRgOptionItem(question);
            setRgCheckOption(question);
        } else {
            // 设置单选按钮组不可见
            mRgOption.setVisibility(View.GONE);
            // 设置多选复选框可见
            mLlMoreOption.setVisibility(View.VISIBLE);
            setCbOptionItem(question);
            setCbCheckOption(question);
        }
    }

    /**
     * 设置多选列表
     * @param question 选项列表
     */
    private void setCbCheckOption(ExamBean.QuestionListBean question) {
        // 设置默认选项
        int[] optionDefault = new int[4];
        // 记录选项个数
        int count = 0;
        if (null != question.getTitleOption()) {
            // 选项数组
            String[] options = question.getTitleOption().split("-");
            // 遍历选项列表
            for (int i = 0; i < question.getOptionList().size(); i++) {
                ExamBean.QuestionListBean.OptionListBean optionList = question.getOptionList().get(i);
                for (String option : options) {
                    if (optionList.getOptionId().equals(option)) {
                        optionDefault[count++] = i;
                        break;
                    }
                }
            }
        }
        // 遍历选项数组，设置选项
        for (int i = 0; i < count; i++) {
            switch (optionDefault[i]) {
                case 0:
                    mSparseArray.put(IDS_LL[0], (String) mCbOptionA.getTag());
                    mCbOptionA.setChecked(true);
                    break;
                case 1:
                    mSparseArray.put(IDS_LL[1], (String) mCbOptionB.getTag());
                    mCbOptionB.setChecked(true);
                    break;
                case 2:
                    mSparseArray.put(IDS_LL[2], (String) mCbOptionC.getTag());
                    mCbOptionC.setChecked(true);
                    break;
                case 3:
                    mSparseArray.put(IDS_LL[3], (String) mCbOptionD.getTag());
                    mCbOptionD.setChecked(true);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置选中的选项
     *
     * @param question 选项列表
     */
    private void setRgCheckOption(ExamBean.QuestionListBean question) {
        // 设置默认选项
        int option = -1;
        if (null != question.getTitleOption()) {
            // 遍历选项列表
            for (int i = 0; i < question.getOptionList().size(); i++) {
                ExamBean.QuestionListBean.OptionListBean itemList = question.getOptionList().get(i);
                // 根据选项ID选择选项
                if (question.getTitleOption().equals(itemList.getOptionId())) {
                    option = i;
                    break;
                }
            }
        }
        // 判断选项是否为默认值
        if (-1 == option) {
            mRgOption.check(0);
        } else {
            mRgOption.check(IDS_RG[option]);
        }
    }

    /**
     * 设置条目选项
     *
     * @param question 题目列表
     */
    private void setRgOptionItem(ExamBean.QuestionListBean question) {
        // 获取题目选项列表
        List<ExamBean.QuestionListBean.OptionListBean> itemList = question.getOptionList();
        // 设置选项
        if (null != itemList.get(0)) {
            // 设置选项文本
            mRgOptionA.setText(itemList.get(0).getOptionName());
            // 设置选项ID
            mRgOptionA.setTag(itemList.get(0).getOptionId());
        }
        if (null != itemList.get(1)) {
            mRgOptionB.setText(itemList.get(1).getOptionName());
            mRgOptionB.setTag(itemList.get(1).getOptionId());
        }
        if (null != itemList.get(2)) {
            mRgOptionC.setText(itemList.get(2).getOptionName());
            mRgOptionC.setTag(itemList.get(2).getOptionId());
        }
        if (null != itemList.get(3)) {
            mRgOptionD.setText(itemList.get(3).getOptionName());
            mRgOptionD.setTag(itemList.get(3).getOptionId());
        }
    }

    /**
     * 设置
     * @param question 选项列表
     */
    private void setCbOptionItem(ExamBean.QuestionListBean question) {
        // 获取题目选项列表
        List<ExamBean.QuestionListBean.OptionListBean> itemList = question.getOptionList();
        // 设置选项
        if (null != itemList.get(0)) {
            // 设置选项文本
            mCbOptionA.setText(itemList.get(0).getOptionName());
            // 设置选项ID
            mCbOptionA.setTag(itemList.get(0).getOptionId());
        }
        if (null != itemList.get(1)) {
            mCbOptionB.setText(itemList.get(1).getOptionName());
            mCbOptionB.setTag(itemList.get(1).getOptionId());
        }
        if (null != itemList.get(2)) {
            mCbOptionC.setText(itemList.get(2).getOptionName());
            mCbOptionC.setTag(itemList.get(2).getOptionId());
        }
        if (null != itemList.get(3)) {
            mCbOptionD.setText(itemList.get(3).getOptionName());
            mCbOptionD.setTag(itemList.get(3).getOptionId());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 注册通信组件
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 注销通信组件
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 设置页面数据
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onContent(IEventProtocol.MessageContent event) {
        setTitleContent(event.getTitleList());
    }

    @OnClick({R.id.exam_content_tv_last_answer, R.id.exam_content_tv_next_answer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exam_content_tv_last_answer:
                EventBus.getDefault().post(new IEventProtocol.MessageLastOrNextAnswer(IEventProtocol.DIRECT.UP));
                break;
            case R.id.exam_content_tv_next_answer:
                EventBus.getDefault().post(new IEventProtocol.MessageLastOrNextAnswer(IEventProtocol.DIRECT.DOWN));
                break;
        }
    }


    /**
     * 更新选项条目
     *
     * @param itemId 条目ID
     */
    private void updateItem(String itemId) {
        EventBus.getDefault().post(new IEventProtocol.MessageUpdateItem(itemId));
    }
}
