package com.example.testproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etNumber, etPassword, etConfirmPassword;
    TextView tv_sign_in ;
    Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);

        //Assign Variable
        etFirstName = findViewById(R.id.first_name);
        etLastName = findViewById(R.id.last_name);
        etNumber = findViewById(R.id.phone_number);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirm_password);
        btnRegister = findViewById(R.id.signupBtn);
        tv_sign_in = (TextView) findViewById(R.id.tv_sign_in);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validation();
            }
        });
        tv_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickSingIn();
            }
        });

    }


    private void ClickSingIn() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void validation(){

        final String mFirstName = etFirstName.getText().toString().trim();
        final String mLastName = etLastName.getText().toString().trim();
        final String mNumber = etNumber.getText().toString().trim();
        final String mPassword = etPassword.getText().toString().trim();
        final String mConfirmPassword = etConfirmPassword.getText().toString().trim();

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
        if(mFirstName.length() > 0 && mLastName.length() > 0 && mNumber.length() >0 && mPassword.length() > 0 && mConfirmPassword.length() > 0){

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertUser, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("madonhang", response);
                    if (response.equals("1"))  {
                        Toast.makeText(getApplicationContext(),"Sign Up Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        //to-do
                        Toast.makeText(getApplicationContext(),"Invalid Information!", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("last_name",mLastName );
                    hashMap.put("first_name",mFirstName );
                    hashMap.put("sdt",mNumber );
                    hashMap.put("password",mPassword );
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);

        }

    }


}