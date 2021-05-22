package com.example.testproject.Presenter;

import com.example.testproject.Interface.LoginInterface;
import com.example.testproject.Model.User;


public class LoginPresenter {
    private LoginInterface mLoginInterface;


    public LoginPresenter(LoginInterface mLoginInterface) {
        this.mLoginInterface = mLoginInterface;
    }

    public void Login(User user){
        if(user.IsValidNumberPhone() && user.IsValidPassWord()){
            mLoginInterface.LoginAccount(user.getNumberPhone(),user.getPassWord());
        } else {
            mLoginInterface.LogInError();
        }
    }


}


