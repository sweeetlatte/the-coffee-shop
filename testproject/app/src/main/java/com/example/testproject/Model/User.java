package com.example.testproject.Model;

import android.text.TextUtils;
import android.widget.TextView;

import java.util.regex.Pattern;

public class User {
    private String numberPhone;
    private String passWord;

//    String MobilePattern = "[0-9]{10}";
//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public User(String numberPhone, String passWord) {
        this.numberPhone = numberPhone;
        this.passWord = passWord;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public boolean IsValidNumberPhone(){
        return !TextUtils.isEmpty(numberPhone) && android.util.Patterns.PHONE.matcher(numberPhone).matches();
    }
    public boolean IsValidPassWord(){
        return !TextUtils.isEmpty(passWord) && passWord.length() >= 6;
    }
}

