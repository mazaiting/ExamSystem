package com.mazaiting.examsystem.module.exam.title;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mazaiting.easy.app.IApplicationComponent;
import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.component.ApplicationComponentImpl;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.base.fragment.BaseRefreshFragment;
import com.mazaiting.examsystem.bean.ExamBean;
import com.mazaiting.examsystem.module.exam.event.IEventProtocol;
import com.mazaiting.examsystem.utils.DateUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 标题Fragment
 * Created by mazaiting on 2018/4/24.
 */

public class ExamTitleFragment extends BaseRefreshFragment<ExamTitlePresenter> implements ExamTitleContract.View {
    /**
     * 试卷ID
     */
    private int mExamId;
    /**
     * 当前题目位置
     */
    private int mPosition = 0;
    /**
     * 已用时间
     */
    @BindView(R.id.exam_title_tv_time)
    TextView mTvTime;
    /***/
    @BindView(R.id.exam_title_tv_analyze)
    TextView mTvAnalyze;
    /**
     * 定时器
     */
    private CountDownTimer mCountDownTimer;
    /**
     * 保存答案数组
     */
    private Map<String, String> mAnswerMap = new HashMap<>();

    /**
     * 创建当前Fragment
     *
     * @param examId 试卷ID
     * @return ExamTitleFragment页面
     */
    public static ExamTitleFragment newInstance(int examId) {
        ExamTitleFragment fragment = new ExamTitleFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Config.EXAM_ID, examId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取试卷ID
        mExamId = getArguments().getInt(Config.EXAM_ID);
    }

    /**
     * 获取总共的问题数目
     *
     * @return 问题数目总数
     */
    public int getTotalQuestion() {
        return mAdapter.getItemCount();
    }

    /**
     * 获取已回答的题目总数
     *
     * @return 已回答的题目总数
     */
    public int getAnswerQuestion() {
        // 用于计算已答的题数
        int count = 0;
        // 获取题目列表
        List<ExamBean.QuestionListBean> list = mAdapter.getData();
        // 遍历计算多少题已答
        for (ExamBean.QuestionListBean question : list) {
            // 如果答案不为空，则已答
            if (!TextUtils.isEmpty(question.getTitleOption())) {
                // 进行已答题目的数据增加
                count++;
            }
        }
        return count;
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.loadExamById(mExamId);
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_title;
    }

    @Override
    public void inject(IApplicationComponent applicationComponent) {
        DaggerExamTitleComponent
                .builder()
                .applicationComponentImpl((ApplicationComponentImpl) applicationComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        super.bindView(view, savedInstanceState);
        // 设置下拉加载禁用
        mSwipeLayout.setEnabled(false);
        // 设置条目点击事件
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            // 设置当前位置
            this.mPosition = position;
            setContentFragment();
        });
    }

    @Override
    protected BaseQuickAdapter setAdapter() {
        return new ExamTitleAdapter();
    }

    @Override
    public void onLoadData(List<ExamBean.QuestionListBean> questionList) {
        mAdapter.setNewData(questionList);
        // 正式联网时启用此段代码
//        setContentFragment();
        startTimer();
    }

    /**
     * 创建定时器
     */
    private void startTimer() {
        // 时间到， 交卷
        mCountDownTimer = new CountDownTimer(7200 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mTvTime.setText(DateUtil.countTimer(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                // 时间到， 交卷
                uploadExam();
            }
        };
        mCountDownTimer.start();
    }

    /**
     * 关闭定时器
     */
    public void stopTimer() {
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer.onFinish();
        }
    }

    /**
     * 交卷
     */
    private void uploadExam() {
        // 获取题目列表
        List<ExamBean.QuestionListBean> list = mAdapter.getData();
        // 遍历计算多少题已答
        for (ExamBean.QuestionListBean question : list) {
            // 如果答案不为空，则已答
            if (!TextUtils.isEmpty(question.getTitleOption())) {
                // 进行已答题目的数据增加
                mAnswerMap.put(question.getTitleId(), question.getTitleOption());
            }
        }

        JSONArray jsonArray = new JSONArray();
        try {
            JSONObject jsonObject;
            for (Map.Entry<String, String> entry : mAnswerMap.entrySet()) {
                jsonObject = new JSONObject();
                jsonObject.put("titleId", entry.getKey());
                jsonObject.put("titleOption", entry.getValue());
                jsonArray.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Logger.e(jsonArray.toString());
        // 上传数据
    }

    /**
     * 设置内容页
     */
    private void setContentFragment() {
        // 滑动到可见的位置
        mRecyclerView.scrollToPosition(this.mPosition);
        // 设置选择的条目
        ((ExamTitleAdapter) this.mAdapter).setSelectedPosition(this.mPosition);
        // 设置内容Fragment页面
        if (0 == this.mPosition) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        EventBus.getDefault().post(new IEventProtocol.MessageContent((ExamBean.QuestionListBean) mAdapter.getData().get(this.mPosition)));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        // 此处为测试时使用，正式联网使用时，此处不执行该方法，该方法应在onLoadData方法中执行，
        // 由于测试时onLoadData在onCreate方法中执行，故在发送Event时，EventBus还未注册。
        setContentFragment();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 更新条目项目
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateItem(IEventProtocol.MessageUpdateItem event) {
        ExamBean.QuestionListBean titleList = (ExamBean.QuestionListBean) mAdapter.getData().get(mPosition);
        // 设置当前选项
        titleList.setTitleOption(event.getItemId());
        // 更新当前条目
        mAdapter.notifyItemChanged(mPosition);
        updateAnswerTotal();
    }

    /**
     * 更新已答题数
     */
    private void updateAnswerTotal() {
        // 设置已答数目与总条数
        mTvAnalyze.setText(getAnswerQuestion() + " / " + getTotalQuestion());
    }

    /**
     * 上一题或者下一题
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLastOrNextAnswer(IEventProtocol.MessageLastOrNextAnswer event) {
        switch (event.getDirect()) {
            case UP:
                lastAnswer();
                break;
            case DOWN:
                nextAnswer();
                break;
            default:
                break;
        }
    }

    /**
     * 上一题
     */
    private void lastAnswer() {
        if ((this.mPosition - 1) >= 0) {
            this.mPosition -= 1;
            setContentFragment();
        } else {
            Toast.makeText(mContext, "已到第一题", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 下一题
     */
    private void nextAnswer() {
        if ((this.mPosition + 1) < mAdapter.getData().size()) {
            this.mPosition += 1;
            setContentFragment();
        } else {
            Toast.makeText(mContext, "已到最后一题", Toast.LENGTH_SHORT).show();
        }
    }
}
