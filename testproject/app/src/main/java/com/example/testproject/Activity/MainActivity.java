package com.example.testproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Adapter.ProductAdapter;
import com.example.testproject.Model.Product;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;
import com.example.testproject.reservationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    RecyclerView recyclerViewNewProducts;
    ViewFlipper viewFlipper;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Map();
//        AcctionViewFlipper();
//        GetDataNewProduct();

        bottomNavigationView=findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;

                    switch (item.getItemId())
                    {
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
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

                    return true;
                }
            };

//    //chạy quảng cáo
//    private void AcctionViewFlipper() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add("https://i.pinimg.com/originals/f8/81/ad/f881ad2778cc7d7c88791e14c9419b52.jpg");
//        list.add("https://images.foody.vn/res/g78/776667/prof/s576x330/foody-upload-api-foody-mobile-cafe-vuon-jpg-180911152838.jpg");
//        list.add("https://res.klook.com/image/upload/fl_lossy.progressive/q_65/c_fill,w_1360/blogvn/2018/09/quan-cafe-dep-seoul.jpg");
//        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToiDBkD64FYWFWXdHnetVfkkFVmdpFzjo6nw&usqp=CAU");
//        for (int i = 0; i < list.size(); i++){
//            ImageView imageView = new ImageView(getApplicationContext());
//            Picasso.get().load(list.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipper.addView(imageView);
//        }
//        viewFlipper.setFlipInterval(3000);
//        viewFlipper.setAutoStart(true);

        //tạo animation
//        Annotation annotationSlideIn = (Annotation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
//        Annotation annotationSlideOut = (Annotation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
//        viewFlipper.setInAnimation((Animation) annotationSlideIn);
//        viewFlipper.setOutAnimation((Animation)  annotationSlideOut);
    }

//    private void Map(){
//        recyclerViewNewProducts = (RecyclerView) findViewById(R.id.recyclerViewNewProducts);
//        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
//        productArrayList = new ArrayList<>();
////        productArrayList.add(new Product(1,"nameProduct",2000,"https://img.thuthuatphanmem.vn/uploads/2018/10/04/anh-dep-ben-ly-cafe-den_110730392.jpg"));
////        productArrayList.add(new Product(2,"nameProduct",2000,"https://img.thuthuatphanmem.vn/uploads/2018/10/04/anh-dep-ben-ly-cafe-den_110730392.jpg"));
//
//        productAdapter = new ProductAdapter(getApplicationContext(),productArrayList);
//        recyclerViewNewProducts.setHasFixedSize(true);
//        recyclerViewNewProducts.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//        recyclerViewNewProducts.setAdapter(productAdapter);
//    }
//    private void GetDataNewProduct() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
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
//
//                    }
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }

//}