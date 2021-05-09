package com.nimisha.nytimes.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.nimisha.nytimes.constants.AppConstant.BASE_URL;
import static com.nimisha.nytimes.constants.AppConstant.BASE_URL_Login;
import static com.nimisha.nytimes.constants.AppConstant.TOPSTORY_BASE_URL;

public class RetrofitRequest {
    private static Retrofit retrofitArticle;
    private static Retrofit retrofitLogin;
    private static Retrofit retrofitTopStory;

    public static Retrofit getRetrofitLoginInstance() {
        if (retrofitLogin == null) {
            retrofitLogin = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_Login)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitLogin;
    }

    public static Retrofit getRetrofitArticleInstance() {
        if (retrofitArticle == null) {
            retrofitArticle = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitArticle;
    }
    public static Retrofit getRetrofitTopStoryInstance(){
        if(retrofitTopStory == null) {

            retrofitTopStory = new retrofit2.Retrofit.Builder().baseUrl(TOPSTORY_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofitTopStory;
    }

}
