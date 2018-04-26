package com.mazaiting.examsystem.base.config;

import android.os.Environment;

import com.mazaiting.easy.config.BaseConfig;
import com.mazaiting.examsystem.BuildConfig;

import java.io.File;

/**
 * 配置类
 * @author mazaiting
 * @date 2018/3/21
 */

public class Config extends BaseConfig{
    /**对话框Tag*/
    public static final String DIALOG_LOADING = "dialog_loading";
    /**网络类型存储*/
    public static final String TYPE_NET = "type_net";
    /**图片存储路径*/
    public static final String PIC_PATH = Environment.getExternalStorageDirectory().getPath() +
            File.separator + "ExamSystem";
    /**当前拍照图片存储路径*/
    public static final String CURRENT_PIC_PATH = "current_pic_path";
    /**登陆请求URL*/
    public static final String URL = "https://api-cn.faceplusplus.com/facepp/";
    /**存储身份证的KEY*/
    public static final String KEY_ID_CARD = "value_id_card";
    /**试卷ID*/
    public static final String EXAM_ID = "exam_id";
    /**选项列表*/
    public static final String[] OPTION = {"A", "B", "C", "D"};


    /**
     * 是否处于调试阶段
     * @return true, 调试；false, 发布版
     */
    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     * 是否开启内存检测
     * @return true, 开启；false, 不开启
     */
    @Override
    protected boolean isUseLogger() {
        return true;
    }

    /**
     * 是否开启UI卡顿检测
     * @return true, 开启；false, 不开启
     */
    @Override
    protected boolean isUseBlockCanary() {
        return true;
    }

    /**
     * 是否开启网页调试
     * @return true, 开启；false, 不开启
     */
    @Override
    protected boolean isUseStetho() {
        return true;
    }

}
