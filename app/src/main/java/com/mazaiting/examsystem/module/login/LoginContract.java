package com.mazaiting.examsystem.module.login;

import com.mazaiting.easy.base.mvp.IBasePresenter;
import com.mazaiting.easy.base.mvp.IBaseView;

/**
 * 登陆联系人
 * Created by mazaiting on 2018/4/24.
 */

public class LoginContract {

    interface View extends IBaseView {

        /**
         * 登陆成功
         */
        void onLoginSuccess();
    }

    interface Presenter extends IBasePresenter<View> {

        /**
         * 登陆
         * @param userName 用户姓名
         * @param idCard 用户身份证号
         * @param imagePath 用户拍照图片路径
         */
        void login(String userName, String idCard, String imagePath);
    }
}
