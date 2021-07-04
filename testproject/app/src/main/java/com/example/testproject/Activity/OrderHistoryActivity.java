package com.example.testproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Adapter.ItemOrderHistoryAdapter;
import com.example.testproject.Model.ItemOrderHistory;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderHistoryActivity extends Activity {
    ListView lv_order_history;
    ArrayList<ItemOrderHistory> arrayListItem;
    ItemOrderHistoryAdapter adapter;
    private String date;
    private String total;
    private String orderid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_order_history);
        InitUI();
        LoadDataHistoryOrder();
    }

    private void LoadDataHistoryOrder() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetHD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!= null)  {
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for(int i = 0 ; i <jsonarray.length(); i ++){
                            JSONObject jsonObject = jsonarray.getJSONObject(i);
                            date = jsonObject.getString("date");
                            total =jsonObject.getString("total");
                            orderid = jsonObject.getString("orderid");
                            arrayListItem.add(new ItemOrderHistory(date,total,orderid));
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
                hashMap.put("maKH",MainActivity.MaKH );
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void InitUI() {
        lv_order_history = (ListView) findViewById(R.id.lv_order_history);
        arrayListItem = new ArrayList<>();
        adapter = new ItemOrderHistoryAdapter( getApplicationContext(), R.layout.row_item_order_history, arrayListItem);
        lv_order_history.setAdapter(adapter);
        lv_order_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailOrderHistoryActivity.class);
                intent.putExtra("orderid",arrayListItem.get(position).getOrderid());
                startActivity(intent);
            }
        });
    }
}
