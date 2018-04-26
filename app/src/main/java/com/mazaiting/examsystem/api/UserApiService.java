package com.mazaiting.examsystem.api;

import com.mazaiting.examsystem.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 用户 操作接口
 * Created by mazaiting on 18/4/2.
 */

public interface UserApiService {
    /**
     * 登陆
     * @param api_key 密钥
     * @param api_secret 加密
     * @param image_file 图片文件
     * @return
     */
    @Multipart
    @POST("v3/detect")
    Observable<UserBean> login(
            @Part MultipartBody.Part api_key,
            @Part MultipartBody.Part api_secret,
            @Part MultipartBody.Part image_file
    );
}
