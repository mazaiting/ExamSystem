package com.mazaiting.examsystem.utils;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.mazaiting.examsystem.R;

/**
 * Glide加载图片工具类
 * Created by mazaiting on 2018/4/24.
 */

public class GlideUtil {
    private static GlideUtil sGlideUtil = null;
    private GlideUtil(){}

    /**
     * 单例
     * @return 当前类对象
     */
    public static GlideUtil getInstance(){
        if (null == sGlideUtil) {
            synchronized (GlideUtil.class) {
                if (null == sGlideUtil) {
                    sGlideUtil = new GlideUtil();
                }
            }
        }
        return sGlideUtil;
    }

    /**
     * 加载图片
     * @param activity 显示图片的Activity
     * @param imgPath 图片路径
     * @param imageView 图片显示控件
     */
    public void loadImage(AppCompatActivity activity, String imgPath, ImageView imageView, OnLoadListener listener){
        Glide.with(activity)
                .load(imgPath)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(new GlideDrawableImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        if (null != listener) {
                            listener.onSuccess();
                        }

                    }
                });
    }

    /**
     * 加载监听
     */
    public interface OnLoadListener{
        /**
         * 加载成功
         */
        void onSuccess();
    }
}
