package com.example.testproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Activity.ProductDetailActivity;
import com.example.testproject.Adapter.ProductAdapter;
import com.example.testproject.Interface.OnItemClickListener;
import com.example.testproject.Model.Product;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MenuCoffeeFragment extends Fragment implements OnItemClickListener {
    View view;
    RecyclerView recyclerViewMenuCoffee;
    ViewFlipper viewFlipper;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    int id = 0;
    String nameProduct = "";
    Integer priceProdut = 0;
    String srcImg = "";
    String describe = "";

    public MenuCoffeeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view = inflater.inflate(R.layout.menu_coffee_fragment, container, false);
        InitUI(view);
        GetDataMenuCoffee();
        return view;
    }

    private void GetDataMenuCoffee() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.pathGetCoffeeProduct, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response != null) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    id = jsonObject.getInt("id");
                                    nameProduct = jsonObject.getString("name");
                                    priceProdut = Integer.parseInt( jsonObject.getString("price"));
                                    srcImg = jsonObject.getString("srcImg");
//                                    describe = jsonObject.getString("describe");
                                    productArrayList.add(new Product(id,nameProduct,priceProdut,srcImg, describe));
                                    productAdapter.notifyDataSetChanged();
                                    productAdapter.notifyDataSetChanged();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void InitUI(View view) {
        recyclerViewMenuCoffee = (RecyclerView) view.findViewById(R.id.recyclerViewMenuCoffee);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        productArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext(),productArrayList, this);
        recyclerViewMenuCoffee.setHasFixedSize(true);
        recyclerViewMenuCoffee.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewMenuCoffee.setAdapter(productAdapter);
    }

    @Override
    public void onItemClickListener(Product product){
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
        intent.putExtra("Product",product);
        startActivity(intent);
    }
}
