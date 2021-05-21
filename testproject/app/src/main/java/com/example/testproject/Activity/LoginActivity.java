package com.example.testproject.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Interface.LoginInterface;
import com.example.testproject.Model.User;
import com.example.testproject.Presenter.LoginPresenter;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends Activity implements LoginInterface {
//    LoginFragment loginFragment;
    EditText edtLoginMobile;
    EditText edtPasssWord;
    TextView tvMessage;
    Button btnLogIn;
    View view;
    String numberPhone = "";
    String passWord = "";
    ArrayList<User> userArrayList;

    private LoginPresenter mLoginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        InitUI();

    }

    private void InitUI() {
        edtLoginMobile = (EditText) findViewById(R.id.login_mobile);
        edtPasssWord = (EditText) findViewById(R.id.login_password);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnLogIn = (Button) findViewById(R.id.loginBtn);
        mLoginPresenter = new LoginPresenter(this);
        userArrayList = new ArrayList<>();
        /// xử lý sự kiện đăng nhập
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickLogIn();
            }
        });
    }
    private void ClickLogIn() {
         numberPhone = edtLoginMobile.getText().toString().trim();
         passWord = edtPasssWord.getText().toString().trim();
         User user =new User(numberPhone,passWord);
         mLoginPresenter.Login(user);
    }
    @SuppressLint("ResourceAsColor")
    public void LogInSuccess() {
         tvMessage.setVisibility(view.VISIBLE);
         tvMessage.setText("Login success");
         tvMessage.setTextColor(R.color.success);
         Intent intent = new Intent(LoginActivity.this, MainActivity.class);
         startActivity(intent);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void LogInError() {
        tvMessage.setVisibility(view.VISIBLE);
        tvMessage.setTextColor(R.color.red);
        tvMessage.setText("Login failed");

    }
    @Override
    public void LoginAccount(final String numberPhone, final String passWord) {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathLogin, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                   String userName = "";
                   String pass = "";

                    if (numberPhone .equals("0123456") && passWord.equals("pass1234") ){
                        Log.e("Đăng nhập","loginsuccess");
                        LogInSuccess();
                    }

                   // cmt dưới fix sau
//                    for(int i = 0; i< response.length(); i ++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            userName = jsonObject.getString("sdt");
//                            pass = jsonObject.getString("pass");
//    //                        userArrayList.add(new User(userName,pass));
//                            if(numberPhone == userName && passWord == pass){
//                                ///LogInSuccess();
//                                Log.e("Đăng nhập","có tài khoản");
//                            }else
//                            if (numberPhone .equals("0123456") && passWord.equals("pass1234") ){
//                                Log.e("Đăng nhập","loginsuccess");
//                                LogInSuccess();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        }



}
