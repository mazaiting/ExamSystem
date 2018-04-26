package com.mazaiting.examsystem.module.welcome;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.module.login.LoginActivity;

import java.util.Locale;

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
        setContentView(R.layout.activity_welcome);
        mTvTime = (TextView) this.findViewById(R.id.welcome_tv_time);
        mTvTime.setOnClickListener(this);
        initCountDownTimer();
    }
    /**
     * 初始化计数器
     */
    private void initCountDownTimer() {
        isEnd = false;
        mCountDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTvTime.setText(String.format(Locale.CHINA,getResources().getString(R.string.count_timer), millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                isEnd = true;
                startLoginActivity();
            }
        }.start();
    }

    /**
     * 开启登陆页面
     */
    private void startLoginActivity() {
        if (!isEnd) {
            mCountDownTimer.cancel();
        }
        LoginActivity.launch(this, LoginActivity.class);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.welcome_tv_time:
                startLoginActivity();
                break;
            default:
                break;
        }
    }
}
