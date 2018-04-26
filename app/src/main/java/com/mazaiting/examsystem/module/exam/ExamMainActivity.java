package com.mazaiting.examsystem.module.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.module.exam.event.IEventProtocol;
import com.mazaiting.examsystem.module.exam.title.ExamTitleFragment;
import com.mazaiting.examsystem.utils.DialogUtil;

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
            String message = "本试卷共有 " + total + " 道题，您已回答 " + answer + " 道题，您确定要交卷么？";
            DialogUtil.getInstance().startPnDialog(
                    "友情提示",
                    message,
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
