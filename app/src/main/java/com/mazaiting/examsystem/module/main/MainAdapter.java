package com.mazaiting.examsystem.module.main;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.bean.SubjectBean;

/**
 * 主页面试卷列表适配器
 * Created by mazaiting on 2018/4/24.
 */

class MainAdapter extends BaseQuickAdapter<SubjectBean.Subject, BaseViewHolder> {
    MainAdapter() {
        super(R.layout.item_main_exam);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectBean.Subject item) {
        // 设置科目名称
        helper.setText(R.id.main_exam_name, item.getName())
                // 设置分数
                .setText(R.id.main_exam_score, item.getScore().toString());
        // 判断分数是否大于0, 不大于0则不显示
        if (item.getScore() > 0f) {
            // 判断分数是否大于60, 大于60则将分数设置为绿色，否则设置为红色
            if (item.getScore() >= 60f) {
                helper.setTextColor(R.id.main_exam_score, Color.parseColor("#00ff00"));
            } else {
                helper.setTextColor(R.id.main_exam_score, Color.parseColor("#ff0000"));
            }
        } else {
            helper.setVisible(R.id.main_exam_score, false);
        }
    }
}
