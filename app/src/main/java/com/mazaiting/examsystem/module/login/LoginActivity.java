package com.mazaiting.examsystem.module.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mazaiting.easy.app.IApplicationComponent;
import com.mazaiting.examsystem.module.main.MainActivity;
import com.mazaiting.examsystem.R;
import com.mazaiting.examsystem.base.activity.BaseLoadingActivity;
import com.mazaiting.examsystem.base.component.ApplicationComponentImpl;
import com.mazaiting.examsystem.base.config.Config;
import com.mazaiting.examsystem.module.camera.CameraActivity;
import com.mazaiting.examsystem.module.setting.SettingActivity;
import com.mazaiting.examsystem.utils.GlideUtil;
import com.mazaiting.examsystem.utils.LanguageUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseLoadingActivity<LoginPresenter> implements LoginContract.View {
    /**拍照请求码*/
    private final int CODE_TAKE_PHOTO = 0x01;
    /**用户名*/
    @BindView(R.id.login_et_username)
    EditText mEtUsername;
    /**身份证号*/
    @BindView(R.id.login_et_id_card)
    EditText mEtIdCard;
    /**照片*/
    @BindView(R.id.login_iv_picture)
    ImageView mIvPicture;
    /**标记图片是否加载成功*/
    private Boolean isLoadSuccess = false;

    /**
     * 开启当前界面
     *
     * @param activity 要开启当前界面的activity
     * @param clazz    当前类
     */
    public static void launch(AppCompatActivity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject(IApplicationComponent applicationComponent) {
        DaggerLoginComponent
                .builder()
                .applicationComponentImpl((ApplicationComponentImpl) applicationComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.login_rl_take_photo, R.id.login_btn_login, R.id.login_tv_language})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_rl_take_photo:
                // 拍照按钮
                takePhoto();
                break;
            case R.id.login_btn_login:
                // 登陆按钮
                login();
                break;
            case R.id.login_tv_language:
                setLanguage();
                break;
            default:
                break;
        }
    }

    /**
     * 设置语言
     */
    private void setLanguage() {
        SettingActivity.newInstance(this, SettingActivity.class);
    }

    /**
     * 登陆
     */
    private void login() {
        // 获取用户名
        String userName = mEtUsername.getText().toString().trim();
        // 用户身份证
        String idCard = mEtIdCard.getText().toString().trim();
        /**
        // 判断用户名是否为空
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, getString(R.string.login_user_name_null), Toast.LENGTH_SHORT).show();
            return;
        }
        // 判断身份证号是否为空
        if (TextUtils.isEmpty(idCard)) {
            Toast.makeText(this, getString(R.string.login_id_card_null), Toast.LENGTH_SHORT).show();
            return;
        }
        // 判断图片是否加载成功
        if (!isLoadSuccess) {
            Toast.makeText(this, getString(R.string.login_picture_null), Toast.LENGTH_SHORT).show();
            return;
        }
*/
        // 登陆
//        mPresenter.login(userName, idCard, mImagePath);
        nextActivity(MainActivity.class, null);
        finish();
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        // 设置图片文件名，当前时间+".jpg"
//        String picName = System.currentTimeMillis() + ".jpg";
        String picName = String.format(getString(R.string.login_photo_name), System.currentTimeMillis());
        // 开启拍照页面进行拍照
        CameraActivity.startActivityForResult(this, picName, CODE_TAKE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case CODE_TAKE_PHOTO:
                    // 设置图片
                    setPicture(data);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 设置照片
     * @param data 意图数据
     */
    private void setPicture(Intent data) {
        // 设置路径
        // 图片路径
        String imagePath = data.getStringExtra(Config.CURRENT_PIC_PATH);
        // 加载图片
        GlideUtil.getInstance().loadImage(LoginActivity.this, imagePath, mIvPicture, () -> isLoadSuccess = true);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, getString(R.string.login_login_success), Toast.LENGTH_SHORT).show();
        // 跳转到主页面
        nextActivity(MainActivity.class, null);
    }
}
