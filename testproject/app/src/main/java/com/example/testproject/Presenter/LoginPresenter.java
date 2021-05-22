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
            mLoginInterface.LoginAccount(user.getNumberPhone(),user.getPassWord());
        } else {
            mLoginInterface.LogInError();
        }
    }


}
//    private void GetDataNewProduct() {
//        RequestQueue requestQueue = Volley.newRequestQueue(homeActivity);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathGetNewProduct, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if(response != null){
//                    int id = 0;
//                    String nameProduct = "";
//                    Integer priceProdut = 0;
//                    String srcImg = "";
//                    for(int i = 0; i< response.length(); i ++){
//                        try {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            id = jsonObject.getInt("id");
//                            nameProduct = jsonObject.getString("name");
//                            priceProdut = jsonObject.getInt(" price");
//                            srcImg = jsonObject.getString("srcImg");
//                            productArrayList.add(new Product(id,nameProduct,priceProdut,srcImg));
//
//                            productAdapter.notifyDataSetChanged();
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(homeActivity, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }

