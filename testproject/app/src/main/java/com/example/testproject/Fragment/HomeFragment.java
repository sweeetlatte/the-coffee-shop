package com.example.testproject.Fragment;

        import android.content.Context;
        import android.os.Bundle;

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

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof MainActivity)
//            this.listener = (DemoFragmentInterface) context;
//        else
//            throw new RuntimeException(context.toString() + "must implement onViewSelected!");
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        //Inflate the layout for this fragment

        //  AcctionViewFlipper();
//        GetDataNewProduct();
        view = inflater.inflate(R.layout.fragment_home, container, false);
//        homeActivity = (HomeActivity) getActivity();
        InitUI(view);
        AcctionViewFlipper(view);
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
    private void AcctionViewFlipper( View view) {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://images.unsplash.com/photo-1561630439-2ebfb3f7bcef?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");
        list.add("https://images.unsplash.com/photo-1617181669950-e0602ed26dbd?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=970&q=80");
        list.add("https://images.unsplash.com/photo-1471922597728-92f81bfe2445?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1052&q=80");
        list.add("https://images.unsplash.com/photo-1566156672382-81d2656bb838?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80");
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