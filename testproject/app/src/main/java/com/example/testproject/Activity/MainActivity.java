package com.example.testproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Fragment.AccountFragment;
import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.Fragment.MenuFragment;
import com.example.testproject.Fragment.ReservationFragment;
import com.example.testproject.Model.ItemCart;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public static ArrayList<ItemCart> itemCartList;
    public static String phoneCustomer;
    public static int idCustomer;
    public static String passWord;
    public static String MaKH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        if(itemCartList == null ){
            itemCartList = new ArrayList<>();
        }
        GetMaKH();

    }




    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.menu:
                        fragment = new MenuFragment();
                        break;
                    case R.id.reservation:
                        fragment = new ReservationFragment();
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                return true;
            }
        };
    public void GetMaKH() {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathGetProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response!= null)  {
                    try {
                        JSONObject obj = new JSONObject(response);
                        MainActivity.MaKH = obj.getString("MaKH");
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
                hashMap.put("numberPhone",phoneCustomer );
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);

    }
}
