package com.example.testproject.Fragment;

        import android.content.Intent;
        import android.os.Bundle;

        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.widget.ViewFlipper;

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
        import com.squareup.picasso.Picasso;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnItemClickListener {
    RecyclerView recyclerViewNewProducts;
    ViewFlipper viewFlipper;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    View view;
    int id = 0;
    String nameProduct = "";
    Integer priceProdut = 0;
    String srcImg = "";
    String describe = "";
    OnItemClickListener onItemClickListener;

    MenuItem menuItem;
    TextView badgeCounter;
    int pendingItems = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);

        menuItem = menu.findItem(R.id.cart);

        if (pendingItems == 0) {
            menuItem.setActionView(null);
        } else {
            menuItem.setActionView(R.layout.fragment_home);
            View view = menuItem.getActionView();
            badgeCounter = view.findViewById(R.id.badge);
            badgeCounter.setText(String.valueOf(pendingItems));
        }

    }

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
//        productArrayList.add(new Product(1,"nameProduct",2000,"https://img.thuthuatphanmem.vn/uploads/2018/10/04/anh-dep-ben-ly-cafe-den_110730392.jpg"));
//        productArrayList.add(new Product(2,"nameProduct",2000,"https://img.thuthuatphanmem.vn/uploads/2018/10/04/anh-dep-ben-ly-cafe-den_110730392.jpg"));

        productAdapter = new ProductAdapter(getContext(),productArrayList, this);
        recyclerViewNewProducts.setHasFixedSize(true);
        recyclerViewNewProducts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewNewProducts.setAdapter(productAdapter);
    }
    public void GetDataNewProduct() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Server.pathGetNewProduct, null,
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
                                    describe = jsonObject.getString("describe");
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
    };

    private void AcctionViewFlipper( View view) {
        ArrayList<String> list = new ArrayList<>();
        list.add("https://i.pinimg.com/originals/f8/81/ad/f881ad2778cc7d7c88791e14c9419b52.jpg");
        list.add("https://images.foody.vn/res/g78/776667/prof/s576x330/foody-upload-api-foody-mobile-cafe-vuon-jpg-180911152838.jpg");
        list.add("https://res.klook.com/image/upload/fl_lossy.progressive/q_65/c_fill,w_1360/blogvn/2018/09/quan-cafe-dep-seoul.jpg");
        list.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcToiDBkD64FYWFWXdHnetVfkkFVmdpFzjo6nw&usqp=CAU");

        for (int i = 0; i < list.size(); i++){
            ImageView imageView = new ImageView(getContext());
            Picasso.get().load(list.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);

//       tạo animation
//       Annotation annotationSlideIn = (Annotation) AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_right);
//       Annotation annotationSlideOut = (Annotation) AnimationUtils.loadAnimation(getContext(),R.anim.slide_out_right);
//       viewFlipper.setInAnimation((Animation) annotationSlideIn);
//       viewFlipper.setOutAnimation((Animation) annotationSlideOut);
    }

    @Override
    public void onItemClickListener(Product product){
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
        startActivity(intent);
    }
}