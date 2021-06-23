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
import com.example.testproject.Model.ItemCart;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailOrderHistoryActivity extends Activity {
    TextView tv_detail_address;
    TextView tv_detail_orderhistory_total;
    ListView listView;
    ItemCartAdapter adapter;
    ArrayList<ItemCart> list;
    public static String nameProduct, srcImgProduct;
//    public static String name;
//    public static String src;
    public static String priceProduct;
    public static String total;
    public static String topping;
    public static String quantity;
    public static String productID;
    private String orderid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_history);
        InitUI();
        Intent intent = new Intent();
        orderid= (String) getIntent().getSerializableExtra("orderid");
        LoadDetailHistoryOrder(orderid);
        LoadInforOrder(orderid);
    }

    private void LoadInforOrder(String orderid) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Server.pathGetHDDetailOrderHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String address;
                String total;
                if (response!= null)  {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        address = jsonObject.getString("address");
                        total =jsonObject.getString("total");
                        tv_detail_address.setText("Địa chỉ giao: " + address);
                        tv_detail_orderhistory_total.setText(decimalFormat.format(Integer.parseInt(total))+ " VNĐ");
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

    private void LoadDetailHistoryOrder(String orderid) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,  Server.pathGetDetailOrderHistory, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                            getInforProduct(productID);
//                            name = DetailOrderHistoryActivity.nameProduct;
//                            src = DetailOrderHistoryActivity.srcImgProduct;

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

    private void getInforProduct(String productID) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetDetailOrderHistoryProduct, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!= null)  {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        DetailOrderHistoryActivity.nameProduct = jsonObject.getString("tensanpham");
                        DetailOrderHistoryActivity.srcImgProduct = jsonObject.getString("srcImg");
                        list.add(new ItemCart(Integer.parseInt(productID),DetailOrderHistoryActivity.nameProduct , Integer.parseInt(priceProduct), DetailOrderHistoryActivity.srcImgProduct , topping, Integer.parseInt(quantity), Integer.parseInt(total)));
                        adapter.notifyDataSetChanged();
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
        tv_detail_address = (TextView) findViewById(R.id.tv_detail_address);
        tv_detail_orderhistory_total = (TextView) findViewById(R.id.tv_detail_orderhistory_total);
        adapter = new ItemCartAdapter(getApplicationContext(),R.layout.row_item_cart,list);
        listView.setAdapter(adapter);
    }
}
