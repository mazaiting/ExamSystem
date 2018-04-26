package com.mazaiting.examsystem.module.exam.title;

import com.google.gson.Gson;
import com.mazaiting.examsystem.base.presenter.BaseNetPresenter;
import com.mazaiting.examsystem.bean.ExamBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 考试题目主持人
 * Created by mazaiting on 2018/4/25.
 */

class ExamTitlePresenter extends BaseNetPresenter<ExamTitleContract.View> implements ExamTitleContract.Presenter {
    @Inject
    ExamTitlePresenter() {
    }

    @Override
    public void loadExamById(int examId) {
        File file = new File(String.valueOf(examId));
        if (file.exists()) {
//            getFileById();
//            mView.onLoadData();
        } else {
            // 检测是否有网络
            if (isNetConnected()) {
                mView.onShowLoading();
                Gson gson = new Gson();
                ExamBean examBean = gson.fromJson(EXAM_JSON, ExamBean.class);
                // 请求成功
                if (0 == examBean.getRet()) {
                    mView.onShowSuccess();
//                    saveFile();
                    mView.onLoadData(examBean.getQuestionList());
                } else {
                    mView.onShowFailed(examBean.getMsg());
                }
            } else {
                mView.onShowNoNet();
            }
        }
    }


    /**
     * 试卷列表
     */
//    private static final String EXAM_JSON= "{\"ret\": 0,\"msg\": \"ok\",\"data\": {\t\"titleList\": [{\t\"titleName\": \"你关注过自己身边的事故隐患吗？ （有效期为2016年1月至2016年7月）\",\"titleType\": \"1\",\"titleOption\": \"\",\"titleId\": \"10120\",\"itemList\": [{\"itemsName\": \"经常关注\",\"itemsId\": \"10310\"\t}, {\"itemsName\": \"偶尔关注\",\"itemsId\": \"10311\"}, {\"itemsName\": \"很少关注\",\"itemsId\": \"10312\"}, {\"itemsName\": \"不关注\",\"itemsId\": \"10313\"}]\t}, {\"titleName\": \"你知道安全生产月是几月吗？ （有效期为2016年5月至2016年7月）\",\"titleType\": \"1\",\"titleOption\": \"\",\"titleId\": \"10121\",\"itemList\": [{\t\"itemsName\": \"5月\",\"itemsId\": \"10314\"}, {\"itemsName\": \"7月\",\"itemsId\": \"10315\"}, {\"itemsName\": \"6月\",\"itemsId\": \"10316\"}, {\"itemsName\": \"10月\",\"itemsId\": \"10317\"}]\t}, {\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\"titleType\": \"1\",\"titleOption\": \"\",\"titleId\": \"10122\",\"itemList\": [{\"itemsName\": \"特别重大事故\",\"itemsId\": \"10318\"}, {\"itemsName\": \"重大事故\",\"itemsId\": \"10319\"}, {\"itemsName\": \"较大事故\",\"itemsId\": \"10320\"}, {\"itemsName\": \"一般事故\",\"itemsId\": \"10321\"}]}]}}";
    private static final String EXAM_JSON = "{\n" +
            "\t\"ret\": 0,\n" +
            "\t\"msg\": \"ok\",\n" +
            "\t\"questionList\": [{\n" +
            "\t\t\"titleName\": \"你关注过自己身边的事故隐患吗？ （有效期为2016年1月至2016年7月）\",\n" +
            "\t\t\"titleType\": \"2\",\n" +
            "\t\t\"titleId\": \"10120\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"经常关注\",\n" +
            "\t\t\t\"optionId\": \"10310\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"偶尔关注\",\n" +
            "\t\t\t\"optionId\": \"10311\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"很少关注\",\n" +
            "\t\t\t\"optionId\": \"10312\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"不关注\",\n" +
            "\t\t\t\"optionId\": \"10313\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"你知道安全生产月是几月吗？ （有效期为2016年5月至2016年7月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10121\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"5月\",\n" +
            "\t\t\t\"optionId\": \"10314\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"7月\",\n" +
            "\t\t\t\"optionId\": \"10315\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"6月\",\n" +
            "\t\t\t\"optionId\": \"10316\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"10月\",\n" +
            "\t\t\t\"optionId\": \"10317\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10122\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10123\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10124\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10125\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10126\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10127\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10128\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10129\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10130\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10131\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10132\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10133\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10134\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10135\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10136\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10137\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10138\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10139\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10140\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10141\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10142\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10143\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10144\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10145\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10146\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10147\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10148\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10149\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10150\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10151\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10152\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10153\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10154\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10155\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10156\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"titleName\": \"生产安全事故分哪些等级？ （有效期为2016年7月至2016年12月）\",\n" +
            "\t\t\"titleType\": \"1\",\n" +
            "\t\t\"titleId\": \"10157\",\n" +
            "\t\t\"optionList\": [{\n" +
            "\t\t\t\"optionName\": \"特别重大事故\",\n" +
            "\t\t\t\"optionId\": \"10318\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"重大事故\",\n" +
            "\t\t\t\"optionId\": \"10319\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"较大事故\",\n" +
            "\t\t\t\"optionId\": \"10320\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"optionName\": \"一般事故\",\n" +
            "\t\t\t\"optionId\": \"10321\"\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}";
}
