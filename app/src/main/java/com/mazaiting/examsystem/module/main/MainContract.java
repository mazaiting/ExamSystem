package com.mazaiting.examsystem.module.main;

import com.mazaiting.easy.base.mvp.IBasePresenter;
import com.mazaiting.easy.base.mvp.IBaseView;
import com.mazaiting.examsystem.bean.SubjectBean;

import java.util.List;

/**
 * 主页面 联系人
 * Created by mazaiting on 2018/4/24.
 */

public class MainContract {
    interface View extends IBaseView {

        /**
         * 加载数据
         * @param list 数据列表
         */
        void onLoadData(List<SubjectBean.Subject> list);
    }
    interface Presenter extends IBasePresenter<View> {

        /**
         * 加载数据
         */
        void loadData();
    }
}
