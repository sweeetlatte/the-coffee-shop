package com.example.testproject.Presenter;

import android.accounts.Account;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Activity.LoginActivity;
import com.example.testproject.Activity.MainActivity;
import com.example.testproject.Interface.LoginInterface;
import com.example.testproject.Model.User;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter {
    private LoginInterface mLoginInterface;
    EditText edtLoginMobile;
    EditText edtPasssWord;

    public LoginPresenter(LoginInterface mLoginInterface) {
        this.mLoginInterface = mLoginInterface;
    }

    public void Login(User user){
        if(user.IsValidNumberPhone() && user.IsValidPassWord()){
           // mLoginInterface.LoginAccount(user.getNumberPhone(),user.getPassWord());
            mLoginInterface.LogInSuccess();
        } else {
            mLoginInterface.LogInError();
        }
    }


}


