package com.mazaiting.examsystem.module.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.config.Config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 照相机页面
 */
public class CameraActivity extends AppCompatActivity {
    /**照片请求码KEY*/
    private static final String KEY_REQUEST_CODE = "key_request_code";
    /**照片名称KEY*/
    private static final String KEY_PIC_NAME = "key_pic_name";
    /**照片名称*/
    private String mPicName;
    /**照相机对象*/
    private Camera mCamera;
    /**照相机预览界面*/
    private CameraPreview mPreview;
    /**提示*/
    private TextView mTvTips;
    /**图片路径*/
    private String mImagePath;
    /**预览布局*/
    private FrameLayout mPreviewLayout;
    /**
     * 开启照相页面并返回结果
     * @param activity 开启当前页面的activity
     * @param picName 相片名称
     * @param requestCode 请求码
     */
    public static void startActivityForResult(AppCompatActivity activity, String picName, int requestCode) {
        Intent intent = new Intent(activity, CameraActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_REQUEST_CODE, requestCode);
        bundle.putString(KEY_PIC_NAME, picName);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtras();
        setContentView(R.layout.activity_camera);
        initView();
        waitTime();
    }

    /**
     * 初始化照相机
     */
    private void initCamera() {
        if (null == mCamera) {
            Toast.makeText(this, "相机不支持", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            mPreview = new CameraPreview(this, mCamera);
            mPreviewLayout.addView(mPreview);
        }
    }

    /**
     * 等待几秒钟，显示提示信息
     */
    private void waitTime() {
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                mTvTips.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    /**
     * 初始化View
     */
    private void initView() {
        Button btnCapture = (Button) this.findViewById(R.id.button_capture);
        btnCapture.setOnClickListener(v -> mCamera.takePicture(null, null, mPictureCallback));
        mTvTips = (TextView) this.findViewById(R.id.tv_tips);
        mPreviewLayout = (FrameLayout) this.findViewById(R.id.camera_preview);
    }

    /**
     * 创建照相机
     * @return 照相机对象
     */
    private Camera getCameraInstance() {
        return Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    /**
     * 获取请求码
     */
    private void getExtras() {
        mPicName = getIntent().getExtras().getString(KEY_PIC_NAME);
    }

    /**
     * 拍照回调
     */
    private Camera.PictureCallback mPictureCallback = (data, camera) -> {
        // 创建图片夹
        final File fileDir = new File(Config.PIC_PATH);
        // 如果不存在则创建
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        // 图片路径
        mImagePath = fileDir + File.separator + mPicName;
        // 创建图片
        File file = new File(mImagePath);
        try{
            // 获取当前旋转角度，并旋转图片
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 设置编码格式
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            // 设置压缩比例,越大图片越清晰
            options.inSampleSize = 3;
            // 解析二进制数据为Bitmap图像
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0 , data.length, options);
            // 修正图片角度
            bitmap = reviewPicRotate(bitmap);
            // 输出图片
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100, fos);
            fos.flush();
            fos.close();

            // 设置返回结果
            Intent intent = new Intent();
            intent.putExtra(Config.CURRENT_PIC_PATH, mImagePath);
            this.setResult(RESULT_OK, intent);
            // 关闭当前界面
            this.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    /**
     * 修正图片角度
     * @param bitmap 图片
     * @return 图片
     */
    private Bitmap reviewPicRotate(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        // 获取宽高
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 旋转角度
        matrix.setRotate(-90f);
        // 生成新图片
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return bitmap;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCamera = getCameraInstance();
        initCamera();
        setCameraDisplayOrientation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseCamera();
    }

    /**
     * 释放照相机
     */
    private void releaseCamera() {
        if (null != mCamera) {
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除预览视图
        mPreviewLayout.removeView(mPreview);
        mPreview = null;
    }

    /**
     * 设置相机显示方向
     */
    private void setCameraDisplayOrientation() {
        // 获取相机信息
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_FRONT, cameraInfo);
        // 获取手机显示角度
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        // 旋转角度
        int degree = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 0;
                break;
            case Surface.ROTATION_90:
                degree = 90;
                break;
            case Surface.ROTATION_180:
                degree = 180;
                break;
            case Surface.ROTATION_270:
                degree = 270;
                break;
            default:
                break;
        }
        // 结果
        int result;
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (cameraInfo.orientation + degree) % 360;
            result = 360 - result;
        } else {
            result = (cameraInfo.orientation - degree + 360) % 360;
        }
        // 设置显示角度
        mCamera.setDisplayOrientation(result);
    }
}
