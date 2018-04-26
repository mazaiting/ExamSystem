package com.mazaiting.examsystem.module.exam.title;

import com.mazaiting.examsystem.base.component.ApplicationComponentImpl;

import dagger.Component;

/**
 * 考试 组件
 * Created by mazaiting on 2018/4/25.
 */
@Component(dependencies = ApplicationComponentImpl.class)
public interface ExamTitleComponent {
    /**
     * 注入考试题目Fragment
     * @param fragment 碎片
     */
    void inject(ExamTitleFragment fragment);
}
