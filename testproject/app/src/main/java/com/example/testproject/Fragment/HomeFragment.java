package com.example.testproject.Fragment;

import android.content.Context;
        import android.os.Bundle;

        import androidx.annotation.DrawableRes;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.viewpager2.widget.ViewPager2;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;
        import android.widget.Toast;
        import android.widget.ViewFlipper;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;
        import com.example.testproject.Adapter.ProductAdapter;
        import com.example.testproject.Model.Product;
        import com.example.testproject.R;
        import com.example.testproject.Untils.Server;
        import com.squareup.picasso.Picasso;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.lang.annotation.Annotation;
        import java.util.ArrayList;
        import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView recyclerViewNewProducts;
    ViewFlipper viewFlipper;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    private ViewPager2 viewPager2;
    View view;
    int id = 0;
    String nameProduct = "";
    Integer priceProdut = 0;
    String srcImg = "";

    public HomeFragment() {
        // Required empty public constructor
    }



/////////////////////////////////    New Products

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home, container, false);
        InitUI(view);
        AcctionViewFlipper(view);
        GetDataNewProduct();
        return view;
    }
    private void InitUI(View view){
        recyclerViewNewProducts = (RecyclerView) view.findViewById(R.id.recyclerViewNewProducts);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        productArrayList = new ArrayList<>();
        productArrayList.add(new Product(1,"nameProduct",2000,"https://img.thuthuatphanmem.vn/uploads/2018/10/04/anh-dep-ben-ly-cafe-den_110730392.jpg"));
        productArrayList.add(new Product(2,"nameProduct",2000,"https://img.thuthuatphanmem.vn/uploads/2018/10/04/anh-dep-ben-ly-cafe-den_110730392.jpg"));

        productAdapter = new ProductAdapter(getContext(),productArrayList);
        recyclerViewNewProducts.setHasFixedSize(true);
        recyclerViewNewProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewNewProducts.setAdapter(productAdapter);
    }
    private void GetDataNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(Request.Method.GET, Server.pathGetNewProduct, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i< response.length(); i ++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            nameProduct = jsonObject.getString("name");
                            priceProdut = jsonObject.getInt(" price");
                            srcImg = jsonObject.getString("srcImg");
                            productArrayList.add(new Product(id,nameProduct,priceProdut,srcImg));

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
    private void AcctionViewFlipper( View view) {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://images.unsplash.com/photo-1543573852-1a71a6ce19bc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80");
        list.add("https://images.unsplash.com/photo-1496318447583-f524534e9ce1?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1105&q=80");
        list.add("https://images.unsplash.com/photo-1558160074-4d7d8bdf4256?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        list.add("https://images.unsplash.com/photo-1496582490020-60c1344c64aa?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1059&q=80");
        for (int i = 0; i < list.size(); i++){
            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(list.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        //tạo animation
//       Annotation annotationSlideIn = (Annotation) AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
//       Annotation annotationSlideOut = (Annotation) AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
//       viewFlipper.setInAnimation((Animation) annotationSlideIn);
//       viewFlipper.setOutAnimation((Animation)  annotationSlideOut);
    }

}