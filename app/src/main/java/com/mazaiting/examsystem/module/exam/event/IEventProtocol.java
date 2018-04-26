package com.mazaiting.examsystem.module.exam.event;

import com.mazaiting.examsystem.bean.ExamBean;

/**
 * Fragment与Activity之间的通信协议
 * Created by mazaiting on 2018/4/25.
 */

public interface IEventProtocol {
    /*********************************************************************
     *************************ExamTitleFragment***************************
     *********************************************************************/
    /**
     * 更新条目 消息
     * ExamContentFragment中发送此事件，在ExamTitleFragment接收此事件并更新条目
     */
    class MessageUpdateItem {
        /**条目ID*/
        private String itemId;
        public MessageUpdateItem(String itemId){
            this.itemId = itemId;
        }
        public String getItemId() {
            return itemId;
        }
    }

    /**
     * 下一题或者下一题 消息
     * ExamContentFragment中发送此事件，在ExamTitleFragment接收此事件并移动当前项
     */
    class MessageLastOrNextAnswer {
        /**方向，指向上或者下*/
        private DIRECT direct;
        public MessageLastOrNextAnswer(DIRECT direct) {
            this.direct = direct;
        }
        public DIRECT getDirect() {
            return direct;
        }
    }

    /**
     * 枚举
     * UP 上
     * DOWN 下
     */
    enum DIRECT {
        UP,
        DOWN
    }

    /*********************************************************************
     *************************ExamContentFragment***************************
     *********************************************************************/
    /**
     * 设置内容Fragment数据
     * ExamTitleFragment中发送此事件，ExamContentFragment中接收此事件并更新页面内容
     */
    class MessageContent {
        /**条目ID*/
        private ExamBean.QuestionListBean titleList;
        public MessageContent(ExamBean.QuestionListBean titleList){
            this.titleList = titleList;
        }
        public ExamBean.QuestionListBean getTitleList() {
            return titleList;
        }
    }
}
