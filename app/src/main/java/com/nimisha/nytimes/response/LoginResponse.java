package com.nimisha.nytimes.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nimisha.nytimes.model.Login;

public class LoginResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private Login login = null;
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
