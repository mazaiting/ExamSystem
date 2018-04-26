package com.mazaiting.examsystem.module.main;

import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.base.presenter.BaseNetPresenter;
import com.mazaiting.examsystem.bean.SubjectBean;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * 主页面 支持人
 * Created by mazaiting on 2018/4/24.
 */

public class MainPresenter extends BaseNetPresenter<MainContract.View> implements MainContract.Presenter {
    @Inject
    MainPresenter() {
    }

    @Override
    public void loadData() {
        // 判断网路是否连接
        if (isNetConnected()) {
            mView.onShowLoading();
            // 获取身份证号码
            String idCard = getIdCard();

            mView.onShowSuccess();
            SubjectBean examBean = new SubjectBean();
            examBean.setRet(0);
            examBean.setMsg("");
            List<SubjectBean.Subject> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                SubjectBean.Subject subject = new SubjectBean.Subject();
                subject.setId(i);
                subject.setName("item" + i);
                if (i == 3) {
                    subject.setScore(0f);
                    subject.setAnswer(true);
                } else {
                    subject.setScore(80f);
                    subject.setAnswer(false);
                }
                list.add(subject);
            }
            examBean.setList(list);

            if (0 == examBean.getRet()) {
                mView.onShowSuccess();
                mView.onLoadData(examBean.getList());
            } else {
                mView.onShowFailed(examBean.getMsg());
            }
        } else {
            mView.onShowNoNet();
        }
    }

    /**
     * 获取身份证
     *
     * @return 身份证号码
     */
    private String getIdCard() {
        return getString(Config.KEY_ID_CARD);
    }
}
