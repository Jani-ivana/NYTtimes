package com.nimisha.nytimes.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nimisha.nytimes.R;
import com.nimisha.nytimes.response.KeyValuePair;
import com.nimisha.nytimes.view_model.LoginViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    Button bt_login;
    EditText et_username, et_password;
    Gson gson;
    SharedPreferences sharedPreferences;
    static int mStatusCode = 0;
    public String username, password;
    private Boolean exit = false;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_Login);
        OnClick();

    }
    private void OnClick() {
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();
                if (username.length() >= 1) {
                    if (password.length() >= 1) {
                        loginapi1();

                    } else {
                        et_password.setError("Please enter Password");
                    }
                } else {
                    et_username.setError("Please enter Username");
                }

            }
        });
    }

    private void loginapi1() {
        loginViewModel.getLoginResponseLiveData().observe(this, loginResponse -> {
            if (loginResponse != null) {
                String username = loginResponse.getLogin().getUsername();
                String password = loginResponse.getLogin().getPassword();

                if(username.equals(et_username.getText().toString())&& password.equals(et_password.getText().toString())){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }else{

                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Username and password are wrong!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }


}
