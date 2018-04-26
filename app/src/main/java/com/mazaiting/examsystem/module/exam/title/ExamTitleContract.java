package com.mazaiting.examsystem.module.exam.title;

import com.mazaiting.easy.base.mvp.IBasePresenter;
import com.mazaiting.easy.base.mvp.IBaseView;
import com.mazaiting.examsystem.bean.ExamBean;

import java.util.List;

/**
 * 试卷题目 联系人
 * Created by mazaiting on 2018/4/25.
 */

class ExamTitleContract {
    interface View extends IBaseView {

        /**
         * 加载题目数据
         * @param questionList 题目列表
         */
        void onLoadData(List<ExamBean.QuestionListBean> questionList);
    }
    interface Presenter extends IBasePresenter<View> {

        /**
         * 根据试卷ID获取试卷
         * @param examId 试卷ID
         */
        void loadExamById(int examId);
    }
}
