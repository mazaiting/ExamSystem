package com.mazaiting.examsystem.module.camera;

import android.content.Context;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * 照相机预览类
 * Created by mazaiting on 18/4/23.
 */

class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
    /**照相机*/
    private Camera mCamera;
    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.mCamera = camera;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // 设置显示
            mCamera.setPreviewDisplay(holder);
            // 开始预览
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (null == holder.getSurface()) {
            return;
        }
        mCamera.stopPreview();
        try {
            // 获取照相机参数
            Camera.Parameters parameters = mCamera.getParameters();
            // 获取所支持的照相机尺寸
            List<Camera.Size> sizeList = parameters.getSupportedPictureSizes();
            Camera.Size optionSize = getOptionPreviewSize(sizeList, getWidth(), getHeight());
            parameters.setPreviewSize(optionSize.width, optionSize.height);
            mCamera.setParameters(parameters);
        } catch (Exception e){
            e.printStackTrace();
        }

        mCamera.startPreview();
    }

    /**
     * 获取相机尺寸
     * @param sizeList 尺寸列表
     * @param width 宽度
     * @param height 高度
     * @return 相机尺寸
     */
    private Camera.Size getOptionPreviewSize(List<Camera.Size> sizeList, int width, int height) {
        // 容差
        final double aspectTolerance = 0.1;
        // 目标比例
        double targetRatio = width / (double)height;
        Camera.Size optionSize = null;
        double minDiff = Double.MAX_VALUE;
        // Try to find a size match aspect ratio and size
        for (Camera.Size size : sizeList) {
            double ratio = size.width / (double) size.height;
            if (Math.abs(ratio - targetRatio) > aspectTolerance) {
                continue;
            }
            if (Math.abs(size.height - height) < minDiff) {
                optionSize = size;
                minDiff = Math.abs(size.height - height);
            }
        }
        // Cannot find the one match the aspect ratio, ignore the requirement
        if (null == optionSize) {
            minDiff = Double.MAX_VALUE;
            for (Camera.Size size : sizeList) {
                if (Math.abs(size.height - height) < minDiff) {
                    optionSize = size;
                    minDiff = Math.abs(size.height - height);
                }
            }
        }

        return optionSize;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }
}
