package com.mazaiting.examsystem.bean;

import com.mazaiting.easy.base.BaseBean;

import java.util.List;

/**
 * 试卷 Bean
 * Created by mazaiting on 2018/4/24.
 */

public class SubjectBean extends BaseBean{
    private List<Subject> list;

    public List<Subject> getList() {
        return list;
    }

    public void setList(List<Subject> list) {
        this.list = list;
    }

    public static class Subject {
        /**试卷ID*/
        private Integer id;
        /**试卷名称*/
        private String name;
        /**试卷分数*/
        private Float score;
        /**是否可以做答*/
        private Boolean isAnswer;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Float getScore() {
            return score;
        }

        public void setScore(Float score) {
            this.score = score;
        }

        public Boolean getAnswer() {
            return isAnswer;
        }

        public void setAnswer(Boolean answer) {
            isAnswer = answer;
        }

        @Override
        public String toString() {
            return "Subject{" +
                    "isAnswer=" + isAnswer +
                    ", score=" + score +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
