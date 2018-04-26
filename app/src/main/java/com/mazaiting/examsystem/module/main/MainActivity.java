package com.mazaiting.examsystem.module.main;

import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mazaiting.easy.app.IApplicationComponent;
import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.activity.BaseRefreshToolbarActivity;
import com.mazaiting.examsystem.base.component.ApplicationComponentImpl;
import com.mazaiting.examsystem.bean.SubjectBean;
import com.mazaiting.examsystem.module.exam.ExamMainActivity;
import com.mazaiting.examsystem.utils.DialogUtil;

import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends BaseRefreshToolbarActivity<MainPresenter> implements MainContract.View {

    @Override
    protected BaseQuickAdapter setAdapter() {
        return new MainAdapter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject(IApplicationComponent applicationComponent) {
        DaggerMainComponent
                .builder()
                .applicationComponentImpl((ApplicationComponentImpl) applicationComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initData() {
        // 下拉刷新
        mSwipeLayout.setOnRefreshListener(() -> mPresenter.loadData());
        mPresenter.loadData();
        // 设置条目点击
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            SubjectBean.Subject exam = (SubjectBean.Subject) adapter.getData().get(position);
            if (exam.getAnswer()) {
                Toast.makeText(MainActivity.this, "可答", Toast.LENGTH_SHORT).show();
                DialogUtil.getInstance().startPnDialog(
                        "友情提示",
                        "此时卷满分100分，60分及格，考试时间120分钟，中途不能退出！！！",
                        (dialog, which) -> ExamMainActivity.launcher(MainActivity.this, ExamMainActivity.class, exam.getId()),
                        getSupportFragmentManager()
                );
            } else {
                Toast.makeText(MainActivity.this, "此时卷已作答，分数为: " + exam.getScore(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLoadData(List<SubjectBean.Subject> list) {
        mAdapter.setNewData(list);
    }
}
