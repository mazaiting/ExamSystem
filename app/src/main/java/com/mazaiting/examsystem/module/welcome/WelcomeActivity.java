package com.mazaiting.examsystem.module.welcome;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.module.login.LoginActivity;
import com.mazaiting.examsystem.utils.LanguageUtil;
import com.mazaiting.examsystem.utils.SharedPreferencesUtil;

/**
 * 欢迎页
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    /**跳过按钮*/
    private TextView mTvTime;
    /**计数器*/
    private CountDownTimer mCountDownTimer;
    /**标记定时器是否结束*/
    private boolean isEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeView();
        setContentView(R.layout.activity_welcome);
        mTvTime = (TextView) this.findViewById(R.id.welcome_tv_time);
        mTvTime.setOnClickListener(this);
        initCountDownTimer();
    }

    /**
     * 设置语言
     */
    private void onBeforeView() {
        // 获取语言
        String language = SharedPreferencesUtil.getString(WelcomeActivity.this, Config.KEY_LANGUAGE);
        // 判断是否为空
        if (TextUtils.isEmpty(language)) {
            language = "zh";
            // 存储语言
            SharedPreferencesUtil.putString(WelcomeActivity.this, Config.KEY_LANGUAGE, language);
        }
        // 设置语言
        LanguageUtil.getInstance().setLanguage(WelcomeActivity.this, language);
    }

    /**
     * 初始化定时器
     */
    private void initCountDownTimer() {
        isEnd = false;
        // 创建定时器
        mCountDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 设置文本
                mTvTime.setText(String.format(getString(R.string.welcome_count_timer), millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // 标记结束
                isEnd = true;
                // 开启登陆页
                startLoginActivity();
            }
        }.start();
    }

    /**
     * 开启登陆页面
     */
    private void startLoginActivity() {
        // 判断定时器是否结束，如果没有结束则取消定时器
        if (!isEnd) {
            mCountDownTimer.cancel();
        }
        // 开启登陆也
        LoginActivity.launch(this, LoginActivity.class);
        // 并关闭当前页
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_tv_time:
                // 跳过按钮，点击开启登陆页
                startLoginActivity();
                break;
            default:
                break;
        }
    }
}
