package com.mazaiting.examsystem.module.exam.title;

import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.bean.ExamBean;

import java.util.List;

/**
 * 试卷题目适配器
 * Created by mazaiting on 2018/4/25.
 */

class ExamTitleAdapter extends BaseQuickAdapter<ExamBean.QuestionListBean, BaseViewHolder> {
    /**当前选中位置*/
    private int mSelectedPosition = 0;
    ExamTitleAdapter() {
        super(R.layout.item_exam_title);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExamBean.QuestionListBean questionList) {
        // 设置题号
        helper.setText(R.id.exam_title_tv_id, questionList.getTitleId())
                // 设置题目名称
                .setText(R.id.exam_title_tv_name, questionList.getTitleName());
        setOptional(helper, questionList);
        setSelectedBackground(helper);
    }

    /**
     * 设置选项
     * @param helper 帮助类
     * @param questionList 当前条目
     */
    private void setOptional(BaseViewHolder helper, ExamBean.QuestionListBean questionList) {
        // 创建用于拼接答案的StringBuilder
        StringBuilder builder = new StringBuilder();
        // 判断是否已经选择了答案
        if (!TextUtils.isEmpty(questionList.getTitleOption())) {
            // 获取当前选项
            int[] options = getOptionalByItem(questionList);
            // 遍历
            for (int option : options) {
                // 判断数组数据是否为默认值
                if (option != Integer.MIN_VALUE) {
                    // 拼接选项
                    builder.append(Config.OPTION[option]);
                } else {
                    break;
                }
            }
        }
        setOptionWithString(helper, builder.toString());
    }

    /**
     * 根据字符串设置选项
     * @param helper 帮助类
     * @param value 选项
     */
    private void setOptionWithString(BaseViewHolder helper, String value) {
        // 设置选项
        helper.setText(R.id.exam_title_tv_option, value);
    }

    /**
     * 设置背景色
     * @param helper 帮助对象
     */
    private void setSelectedBackground(BaseViewHolder helper) {
        // 判断是否为当前选中条目
        if (mSelectedPosition == helper.getAdapterPosition()) {
            helper.setBackgroundRes(R.id.exam_title_rl, R.color.md_grey_100);
        } else {
            helper.setBackgroundRes(R.id.exam_title_rl, android.R.color.white);
        }
    }

    /**
     * 根据当前条目获取选项
     * @param questionList 条目
     * @return 返回当前选项
     */
    private int[] getOptionalByItem(ExamBean.QuestionListBean questionList) {
        // 获取选项列表
        List<ExamBean.QuestionListBean.OptionListBean> itemList = questionList.getOptionList();
        // 初始化选项ID
        int[] optionInts = {Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MIN_VALUE};

        // 记录数据个数
        int count = 0;
        // 判断选项是否为空
        if (!TextUtils.isEmpty(questionList.getTitleOption())) {
            // 遍历选项ID
            for (int i = 0; i < itemList.size(); i++) {
                // 存放选项
                String[] options = new String[4];
                // 判断是否为多选
                if ("1".equals(questionList.getTitleType())) {
                    options[0] = questionList.getTitleOption();
                } else {
                    options = questionList.getTitleOption().split("-");
                }
                // 获取条目选项
                ExamBean.QuestionListBean.OptionListBean item = itemList.get(i);
                // 遍历
                for (String option : options) {
                    // 判断选项条目ID是否相等
                    if (item.getOptionId().equals(option)) {
                        optionInts[count++] = i;
                        break;
                    }
                }
            }
        }
        return optionInts;
    }

    /**
     * 设置是否选中条目
     * @param selectedPosition 选中位置
     */
    void setSelectedPosition(int selectedPosition) {
        // 将新选中的条目赋值
        this.mSelectedPosition = selectedPosition;
        // 更新列表
        notifyDataSetChanged();
    }
}
