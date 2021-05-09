package com.nimisha.nytimes.retrofit;

import com.nimisha.nytimes.response.ArticleResponse;
import com.nimisha.nytimes.response.LoginResponse;
import com.nimisha.nytimes.response.TopStoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {
    @GET("articlesearch.json")
    Call<ArticleResponse> getMovieArticles(
            @Query("q") String query,
            @Query("api-key") String apiKey

    );
    @GET("arts.json")
    Call<TopStoryResponse> getTopstorylist(

            @Query("api-key") String apiKey

    );
    @GET("login")
    Call<LoginResponse> getLogin(

    );

}
