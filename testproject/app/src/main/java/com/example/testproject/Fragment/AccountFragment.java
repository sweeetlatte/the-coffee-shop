package com.example.testproject.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Activity.LoginActivity;
import com.example.testproject.Activity.MainActivity;
import com.example.testproject.Activity.OrderHistoryActivity;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class AccountFragment extends Fragment {
    View view;
    public String firstName;
    public String lastName;
    TextView profile_name;
    TextView pro_first_name;
    TextView pro_last_name;
    TextView password;
    TextView phone_number;
    TextView tv_account_logout;
    TextView tv_account_order_history;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        InitUI();
        GetInforAccount();
        return view;
    }

    private void InitUI() {
        profile_name = (TextView) view.findViewById(R.id.profile_name);
        pro_first_name = (TextView) view.findViewById(R.id.pro_first_name);
        pro_last_name = (TextView) view.findViewById(R.id.pro_last_name);
        password = (TextView) view.findViewById(R.id.password);
        phone_number = (TextView) view.findViewById(R.id.phone_number);
        tv_account_logout = (TextView) view.findViewById(R.id.tv_account_logout);
        tv_account_order_history = (TextView) view.findViewById(R.id.tv_account_order_history);
        tv_account_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });
        tv_account_order_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderHistory();
            }
        });
    }

    private void OrderHistory() {
        Intent intent = new Intent(getContext(), OrderHistoryActivity.class);
        startActivity(intent);
    }

    private void LogOut() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void GetInforAccount() {

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!= null)  {
                    try {
                        JSONObject obj = new JSONObject(response);
                        firstName = obj.getString("HoKH");
                        lastName = obj.getString("TenKH");
                        MainActivity.idCustomer = Integer.parseInt(obj.getString("MaKH"));
                        ResetUI(firstName, lastName);
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
                hashMap.put("numberPhone",MainActivity.phoneCustomer );
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void ResetUI(String firstName, String lastName) {
        profile_name.setText(firstName + lastName);
        pro_first_name.setText(firstName);
        pro_last_name.setText(lastName);
        phone_number.setText(MainActivity.phoneCustomer);
        password.setText(MainActivity.passWord);

    }
}