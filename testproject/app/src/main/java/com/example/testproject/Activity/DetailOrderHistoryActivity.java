package com.example.testproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Adapter.ItemCartAdapter;
import com.example.testproject.Model.InforProduct;
import com.example.testproject.Model.ItemCart;
import com.example.testproject.Model.ItemOrderHistory;
import com.example.testproject.Model.Product;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailOrderHistoryActivity extends Activity {
    TextView tv;
    ListView listView;
    ItemCartAdapter adapter;
    ArrayList<ItemCart> list;
    public ArrayList<InforProduct> listInforProduct;

  //  public InforProduct mInforProduct;
    private String orderid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_history);
        InitUI();
        LoadDetailHistoryOrder();
    }

    private void LoadDetailHistoryOrder() {
        Intent intent = new Intent();
        orderid= (String) getIntent().getSerializableExtra("orderid");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Server.pathGetDetailOrderHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 String priceProduct;
                 String total;
                 String topping;
                 String quantity;
                 String productID;
                 String nameProduct = "";
                 String srcImgProduct = "";
                 InforProduct inforProduct;
                if (response!= null)  {
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for(int i = 0 ; i <jsonarray.length(); i ++){
                            JSONObject jsonObject = jsonarray.getJSONObject(i);
                            priceProduct = jsonObject.getString("giaban");
                            total =jsonObject.getString("thanhtien");
                            topping = jsonObject.getString("topping");
                            quantity = jsonObject.getString("soluong");
                            productID = jsonObject.getString("mamon");
                            getInforProduct(productID, nameProduct, srcImgProduct );
                            list.add(new ItemCart(Integer.parseInt(productID), nameProduct, Integer.parseInt(priceProduct), srcImgProduct, topping, Integer.parseInt(quantity), Integer.parseInt(total)));
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        Log.d("Error", e.toString());
                    }

                } else {
                    //to-do
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
                hashMap.put("orderid",orderid);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getInforProduct(String productID,String nameProduct, String srcImgProduct) {

        Toast.makeText(getApplicationContext(),"Vô lấy ảnh r", Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetDetailOrderHistoryProduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                InforProduct a;

                if (response!= null)  {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
//                        nameProduct = jsonObject.getString("tensanpham");
//                        srcImgProduct = jsonObject.getString("srcImg");
//                        a = new InforProduct(nameProduct,srcImgProduct);
//                        Log.e("111", a.toString());
                    } catch (JSONException e) {
                        Log.d("Error", e.toString());
                    }

                } else {
                    //to-do
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("masp",productID);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void InitUI() {
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv_detail_order);
        adapter = new ItemCartAdapter(getApplicationContext(),R.layout.row_item_cart,list);
        listView.setAdapter(adapter);
    }
}
