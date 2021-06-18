package com.example.testproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Adapter.ItemCartAdapter;
import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends Activity {
    Button btnOrder;
    EditText edtAddress;
    TextView tvCheckOutTotal;
    TextView tvCheckOutSum;
    TextView tvCheckOutShip;
    ListView lvCheckOut;
    ItemCartAdapter adapter;
    int total = 0;
    private String address ;
    private String checkOutTotal;
    String mahd = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_checkout);
        InitUI();
        EvenUntil();
        SumTotal();
    }
    private void SumTotal() {
   //     int ship = Integer.parseInt(tvCheckOutShip.getText().toString().trim()) ;
    //    int sum = total + ship;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvCheckOutTotal.setText(decimalFormat.format(total+ 10000) + " VNĐ");
    }

    private void InitUI() {
        edtAddress = (EditText) findViewById(R.id.edtAddress) ;
        btnOrder = (Button) findViewById(R.id.btn_checkout_checkout);
        tvCheckOutShip = (TextView) findViewById(R.id.tv_checkout_ship);
        tvCheckOutSum = (TextView) findViewById(R.id.tv_checkout_sum);
        tvCheckOutTotal = (TextView) findViewById(R.id.tv_checkout_total);
        lvCheckOut = (ListView) findViewById(R.id.lv_checkout);
        adapter = new ItemCartAdapter(getApplicationContext(),R.layout.row_item_cart,MainActivity.itemCartList);
        lvCheckOut.setAdapter(adapter);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckOut();
            }
        });
    }

    private void CheckOut() {
        address = edtAddress.getText().toString();
        if( address != null){
            InsertHoaDon();
        }
    }

    private void InsertHoaDon(){
        RequestQueue requestQueue = Volley.newRequestQueue(CheckoutActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertHoaDon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("0"))  {
                    Toast.makeText(getApplicationContext(),"Địa chỉ không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    mahd = response;
                    InsertCTHD(mahd);
                    Toast.makeText(getApplicationContext(),"Order thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
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
                hashMap.put("makh",MainActivity.MaKH );
                hashMap.put("diachi",address);
 //               hashMap.put("ship",mNumber );
                hashMap.put("tongtien",(total+ 10000) + "");
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void InsertCTHD(String mahd) {
        for(int i = 0; i < MainActivity.itemCartList.size(); i ++){
            //Lấy thuộc tính để lưu vào db
            String mamon = MainActivity.itemCartList.get(i).idProduct + "";
            String soluong = MainActivity.itemCartList.get(i).quantity + "";
            String gia = MainActivity.itemCartList.get(i).priceProduct + "";
            String ghichu = MainActivity.itemCartList.get(i).topping + "";
            String thanhtien = MainActivity.itemCartList.get(i).total + "";

            // truyền xuống php để lưu vào db
            RequestQueue requestQueue = Volley.newRequestQueue(CheckoutActivity.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertCTHD, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("madonhang", response);

                    if (response.equals("0"))  {
                        Toast.makeText(getApplicationContext(),"Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
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
                    hashMap.put("mahd",mahd);
                    hashMap.put("mamon",mamon);
                    hashMap.put("soluong",soluong);
                    hashMap.put("gia",gia);
                    hashMap.put("ghichu",ghichu);
                    hashMap.put("thanhtien",thanhtien);
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
            MainActivity.itemCartList.clear();

        }
    }

    private void EvenUntil() {
        for(int i = 0; i < MainActivity.itemCartList.size(); i++){
            total = total + MainActivity.itemCartList.get(i).total;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvCheckOutSum.setText(decimalFormat.format(total) + " VNĐ");
    }


    
}
