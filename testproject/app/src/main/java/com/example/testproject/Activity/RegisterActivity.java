package com.example.testproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testproject.R;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etNumber, etPassword, etConfirmPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //Assign Variable
        etFirstName = findViewById(R.id.first_name);
        etLastName = findViewById(R.id.last_name);
        etNumber = findViewById(R.id.phone_number);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);
        btnRegister = findViewById(R.id.signupBtn);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validation();
            }
        });

    }

    public void validation(){

        String mFirstName = etFirstName.getText().toString();
        String mLastName = etLastName.getText().toString();
        String mNumber = etNumber.getText().toString();
        String mPassword = etPassword.getText().toString();
        String mConfirmPassword = etConfirmPassword.getText().toString();

        //Validate for the empty Name field
        if (mFirstName.matches("")) {
            Toast.makeText(RegisterActivity.this,"Please Enter First Name",Toast.LENGTH_LONG).show();
            return;
        }

        //Validate Entered Name Contains Numbers
        if (!mFirstName.matches("[a-zA-Z]+")) {
            Toast.makeText(RegisterActivity.this,R.string.invalid_fname,Toast.LENGTH_LONG).show();
            return;
        }

        //Validate for the empty Name field
        if (mLastName.matches("")) {
            Toast.makeText(RegisterActivity.this,"Please Enter Last Name",Toast.LENGTH_LONG).show();
            return;
        }

        //Validate Entered Name Contains Numbers
        if (!mLastName.matches("[a-zA-Z]+")) {
            Toast.makeText(RegisterActivity.this,R.string.invalid_lname,Toast.LENGTH_LONG).show();
            return;
        }

        //Validate for the empty Phone Number field
        if (mNumber.matches("")) {
            Toast.makeText(RegisterActivity.this,"Please Enter Your Phone Number",Toast.LENGTH_LONG).show();
            return;
        }

        //Validate wrong phone number
        if (!Patterns.PHONE.matcher(mNumber).matches() || mNumber.length()!=10) {
            Toast.makeText(RegisterActivity.this,R.string.invalid_phone,Toast.LENGTH_LONG).show();
            return;
        }

        //Validate for the empty Password field
        if (mPassword.matches("")) {
            Toast.makeText(RegisterActivity.this,"Please Enter a Password",Toast.LENGTH_LONG).show();
            return;
        }

        //Validate for the empty Confirm Password field
        if (mConfirmPassword.matches("")) {
            Toast.makeText(RegisterActivity.this,"Please Confirm Your Password",Toast.LENGTH_LONG).show();
            return;
        }

        //Validate wrong confirm password
        if(!mPassword.matches(mConfirmPassword)) {
            Toast.makeText(RegisterActivity.this,R.string.invalid_confirm_password,Toast.LENGTH_LONG).show();
            return;
        }

    }

}