package com.mazaiting.examsystem.base.module;

import com.mazaiting.examsystem.api.UserApi;
import com.mazaiting.examsystem.api.UserApiService;
import com.mazaiting.examsystem.base.config.Config;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * 提供Api
 * @author mazaiting
 * @date 2018/3/22
 */
@Module
public class ApiModule {

    @Provides
    UserApi providedUserApis(Retrofit.Builder builder) {
        return UserApi.getInstance(
                builder.baseUrl(Config.URL)
                .build().create(UserApiService.class)
        );
    }

//    @Provides
//    UserApi providedUserApis(Retrofit.Builder builder) {
//        return UserApi.getInstance(
//                builder.baseUrl(BuildConfig.URL)
//                .build().create(UserApiService.class)
//        );
//    }
}
