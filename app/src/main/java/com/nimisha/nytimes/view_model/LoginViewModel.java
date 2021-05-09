package com.nimisha.nytimes.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nimisha.nytimes.repository.LoginRepository;
import com.nimisha.nytimes.response.LoginResponse;

public class LoginViewModel extends AndroidViewModel {
    private LoginRepository loginRepository;
    private LiveData<LoginResponse> loginResponseLiveData;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        loginRepository = new LoginRepository();
        this.loginResponseLiveData = loginRepository.getLogin();
    }

    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
    }
}
