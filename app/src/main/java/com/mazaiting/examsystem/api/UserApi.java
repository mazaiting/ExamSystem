package com.mazaiting.examsystem.api;

import com.mazaiting.examsystem.bean.UserBean;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

/**
 * 用户操作 api
 * Created by mazaiting on 18/4/2.
 */

public class UserApi {
    private static UserApi sApi;
    private UserApiService mService;
    private UserApi(UserApiService service){
        this.mService = service;
    }

    /**
     * 单例
     * @param service 服务
     * @return api对象
     */
    public static UserApi getInstance(UserApiService service) {
        if (null == sApi) {
            synchronized (UserApi.class) {
                if (null == sApi) {
                    sApi = new UserApi(service);
                }
            }
        }
        return sApi;
    }

    /**
     * 登陆
     * @param userName 密钥
     * @param idCard 加密
     * @param imgPath 图片文件
     * @return
     */
    public Observable<UserBean> login(String userName, String idCard, String imgPath){
        String key = "rv5Pab4EsmqWFft6tBKK5GqvsBGerHeb";
        MultipartBody.Part api_key = MultipartBody.Part.createFormData("api_key", key);
        String secret = "OVNhOVgsxOhq4wsYzSj7-88ZD1BO3vHP";
        MultipartBody.Part api_secret = MultipartBody.Part.createFormData("api_secret", secret);
        File file = new File(imgPath);
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image_file = MultipartBody.Part.createFormData("image_file", file.getName(), body);
        return mService.login(api_key, api_secret, image_file);
    }
}
