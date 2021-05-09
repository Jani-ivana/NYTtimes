package com.nimisha.nytimes.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nimisha.nytimes.response.TopStoryResponse;
import com.nimisha.nytimes.retrofit.ApiRequest;
import com.nimisha.nytimes.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoryRepository {

    private static final String TAG = TopStoryRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public TopStoryRepository() {
        apiRequest = RetrofitRequest.getRetrofitTopStoryInstance().create(ApiRequest.class);
    }

    public LiveData<TopStoryResponse> getTopStories(String Key) {
        final MutableLiveData<TopStoryResponse> data = new MutableLiveData<>();
        apiRequest.getTopstorylist(Key)
                .enqueue(new Callback<TopStoryResponse>() {


                    @Override
                    public void onResponse(Call<TopStoryResponse> call, Response<TopStoryResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            // Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            //Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            // Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<TopStoryResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
