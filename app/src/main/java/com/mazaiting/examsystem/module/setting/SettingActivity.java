package com.mazaiting.examsystem.module.setting;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.module.exam.ExamMainActivity;
import com.mazaiting.examsystem.module.login.LoginActivity;
import com.mazaiting.examsystem.utils.LanguageUtil;
import com.mazaiting.examsystem.utils.SharedPreferencesUtil;
import com.mazaiting.widget.view.ItiView;

public class SettingActivity extends AppCompatActivity {
    /**设置语言*/
    private ItiView mItiLanguage;

    /**
     * 开启当前设置页面
     * @param context 上一个页面上下文对象
     * @param cls 当前类对象
     */
    public static void newInstance(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeView();
        setContentView(R.layout.activity_setting);
        initView();
        setListener();
    }

    /**
     * 设置View之前的操作
     */
    private void onBeforeView() {
        // 获取语言
        String language = SharedPreferencesUtil.getString(SettingActivity.this, Config.KEY_LANGUAGE);
        // 设置语言
        LanguageUtil.getInstance().setLanguage(SettingActivity.this, language);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        mItiLanguage = (ItiView) this.findViewById(R.id.setting_iti_language);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mItiLanguage.setOnClickListener(v -> {
            final String[] items = {"简体中文", "维吾尔语"};
            final String[] languageItems = {"zh", "ug"};
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
            // 设置标题
            builder.setTitle("语言列表");
            // 设置图标
            builder.setIcon(R.mipmap.ic_launcher);
            // 设置列表显示
            builder.setItems(items, (dialog, which) -> {
//                Toast.makeText(SettingActivity.this, which+"", Toast.LENGTH_SHORT).show();
                LanguageUtil util = LanguageUtil.getInstance();
                util.setLanguage(SettingActivity.this, languageItems[which]);
                // 存储语言
                SharedPreferencesUtil.putString(SettingActivity.this, Config.KEY_LANGUAGE, languageItems[which]);
                util.restartActivity(SettingActivity.this, LoginActivity.class);
            });
            builder.create().show();
        });
    }

}
