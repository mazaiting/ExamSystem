package com.mazaiting.examsystem.bean;

import com.mazaiting.easy.base.BaseBean;

import java.util.List;

/**
 * 试卷 Bean
 * Created by mazaiting on 2018/4/24.
 */

public class ExamBean extends BaseBean {
    /**问题列表*/
    private List<QuestionListBean> questionList;

    public List<QuestionListBean> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionListBean> questionList) {
        this.questionList = questionList;
    }

    public static class QuestionListBean {
        /**
         * titleName : 你关注过自己身边的事故隐患吗？ （有效期为2016年1月至2016年7月）
         * titleType : 1
         * titleId : 10120
         * optionList : [{"optionName":"经常关注","optionId":"10310"},{"optionName":"偶尔关注","optionId":"10311"},{"optionName":"很少关注","optionId":"10312"},{"optionName":"不关注","optionId":"10313"}]
         */
        /**题目名称*/
        private String titleName;
        /**题目类型：1 为单选， 2 为多选*/
        private String titleType;
        /**题目ID*/
        private String titleId;
        /**
         * 用户选择的答案
         * 用来保存当前用户选择的答案，条目ID来保存，中间用"-"分隔开
         * 形如：10310-10311-10312
         */
        private String titleOption;
        /**选项列表*/
        private List<OptionListBean> optionList;

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleType() {
            return titleType;
        }

        public void setTitleType(String titleType) {
            this.titleType = titleType;
        }

        public String getTitleId() {
            return titleId;
        }

        public void setTitleId(String titleId) {
            this.titleId = titleId;
        }

        public String getTitleOption() {
            return titleOption;
        }

        public void setTitleOption(String titleOption) {
            this.titleOption = titleOption;
        }

        public List<OptionListBean> getOptionList() {
            return optionList;
        }

        public void setOptionList(List<OptionListBean> optionList) {
            this.optionList = optionList;
        }

        public static class OptionListBean {
            /**
             * optionName : 经常关注
             * optionId : 10310
             */
            /**选项内容*/
            private String optionName;
            /**选项ID*/
            private String optionId;

            public String getOptionName() {
                return optionName;
            }

            public void setOptionName(String optionName) {
                this.optionName = optionName;
            }

            public String getOptionId() {
                return optionId;
            }

            public void setOptionId(String optionId) {
                this.optionId = optionId;
            }
        }
    }
}
