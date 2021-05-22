package com.example.testproject.Presenter;

import android.widget.EditText;

import com.example.testproject.Interface.LoginInterface;
import com.example.testproject.Model.User;

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

