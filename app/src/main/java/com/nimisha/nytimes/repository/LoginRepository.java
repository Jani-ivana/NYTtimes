package com.nimisha.nytimes.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.nimisha.nytimes.response.LoginResponse;
import com.nimisha.nytimes.retrofit.ApiRequest;
import com.nimisha.nytimes.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private static final String TAG = LoginRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public LoginRepository() {
        apiRequest = RetrofitRequest.getRetrofitLoginInstance().create(ApiRequest.class);
    }

    public LiveData<LoginResponse> getLogin() {
        final MutableLiveData<LoginResponse> data = new MutableLiveData<>();
        apiRequest.getLogin()
                .enqueue(new Callback<LoginResponse>() {


                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response);



                        if (response.body() != null) {
                            data.setValue(response.body());

                            // Log.d(TAG, "articles total result:: " + response.body().getTotalResults());
                            //Log.d(TAG, "articles size:: " + response.body().getArticles().size());
                            // Log.d(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
