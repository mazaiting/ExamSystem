package com.mazaiting.examsystem.module.login;

import android.text.TextUtils;

import com.mazaiting.easy.utils.rx.BaseObserver;
import com.mazaiting.easy.utils.rx.RxScheduler;
import com.mazaiting.examsystem.api.UserApi;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.base.presenter.BaseNetPresenter;
import com.mazaiting.examsystem.bean.UserBean;

import javax.inject.Inject;

/**
 * 登陆主持人
 * Created by mazaiting on 2018/4/24.
 */

public class LoginPresenter extends BaseNetPresenter<LoginContract.View> implements LoginContract.Presenter {
    private UserApi mUserApi;
    @Inject
    LoginPresenter(UserApi api){
        this.mUserApi = api;
    }

    @Override
    public void login(String userName, String idCard, String imagePath) {
        if (isNetConnected()) {
            mView.onShowLoading();
            mUserApi.login(userName, idCard, imagePath)
                    .compose(RxScheduler.applySchedulers())
                    .compose(mView.bindToLife())
                    .subscribe(new BaseObserver<UserBean>() {

                        @Override
                        protected void onSuccess(UserBean userBean) {
                            // 0 为登录成功
                            userBean.setRet(0);
                            userBean.setMsg("");
                            if (0 == userBean.getRet()) {
                                // 判断是否登录成功
                                mView.onShowSuccess();
                                saveIdCard(idCard);
                                mView.onLoginSuccess();
                            } else {
                                mView.onShowFailed(userBean.getMsg());
                            }
                        }

                        @Override
                        protected void onFailed(Throwable e) {
                            mView.onShowFailed(e.getMessage());
                        }
                    });
        } else {
            mView.onShowNoNet();
        }
    }

    @Override
    public String getLanguage() {
        return getString(Config.KEY_LANGUAGE);
    }

    /**
     * 保存身份证号码
     * @param idCard 身份证号码
     */
    private void saveIdCard(String idCard) {
        putString(Config.KEY_ID_CARD, idCard);
    }


}
