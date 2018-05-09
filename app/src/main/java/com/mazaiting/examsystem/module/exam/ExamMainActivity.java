package com.mazaiting.examsystem.module.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.module.exam.event.IEventProtocol;
import com.mazaiting.examsystem.module.exam.title.ExamTitleFragment;
import com.mazaiting.examsystem.module.welcome.WelcomeActivity;
import com.mazaiting.examsystem.utils.DialogUtil;
import com.mazaiting.examsystem.utils.LanguageUtil;
import com.mazaiting.examsystem.utils.SharedPreferencesUtil;

public class ExamMainActivity extends AppCompatActivity implements IEventProtocol {
    /**标题Fragment*/
    private ExamTitleFragment mTitleFragment;
    /**
     * 开启当前界面
     * @param activity 界面
     * @param clazz 当前类字节码
     * @param id 试卷id
     */
    public static void launcher(AppCompatActivity activity, Class clazz, int id) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(Config.EXAM_ID, id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeView();
        setContentView(R.layout.activity_exam_main);

        int examId = getIntent().getIntExtra(Config.EXAM_ID, 0);
        initView();
        mTitleFragment = ExamTitleFragment.newInstance(examId);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.exam_main_left_fragment, mTitleFragment)
                .commit();
    }

    /**
     * 设置View之前的操作
     */
    private void onBeforeView() {
        // 获取语言
        String language = SharedPreferencesUtil.getString(ExamMainActivity.this, Config.KEY_LANGUAGE);
        // 设置语言
        LanguageUtil.getInstance().setLanguage(ExamMainActivity.this, language);
    }

    /**
     * 初始化View
     */
    private void initView() {
        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.exam_main_fab_upload);
        fab.setOnClickListener(v -> uploadExam());
    }

    @Override
    public void onBackPressed() {
        uploadExam();
    }

    /**
     * 交卷
     */
    private void uploadExam() {
        // 获取试卷总题数
        int total = mTitleFragment.getTotalQuestion();
        // 获取试卷已回答题数
        int answer = mTitleFragment.getAnswerQuestion();
        // 判断是否符合要去
        if (answer <= total) {
            DialogUtil.getInstance().startPnDialog(
                    getString(R.string.exam_main_friendly_reminder),
                    String.format(getString(R.string.exam_main_friendly_reminder_message), total, answer),
                    (dialog, which) -> {
                        // 执行交卷
                        mTitleFragment.stopTimer();
                        // 退出Activity
                        finish();
                    },
                    getSupportFragmentManager()
            );
        }
    }
}
