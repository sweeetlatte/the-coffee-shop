package com.example.testproject.Fragment;

        import android.content.Intent;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Parcelable;
        import android.util.Log;
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
        import com.example.testproject.Activity.CartActivity;
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
    ImageView cart_icon;
    int pendingItems = 0;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        view = inflater.inflate(R.layout.fragment_home, container, false);
        InitUI(view);
        AcctionViewFlipper(view);
        GetDataNewProduct();
        cart_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A();
            }
        });
        return view;
    }

    private void A() {
         Intent intent = new Intent(getContext(),CartActivity.class);
         startActivity(intent);
    }

    private void InitUI(View view){
        recyclerViewNewProducts = (RecyclerView) view.findViewById(R.id.recyclerViewNewProducts);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        cart_icon = (ImageView) view.findViewById(R.id.cart_icon);
        productArrayList = new ArrayList<>();
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
        list.add("https://images.unsplash.com/photo-1617796110169-c4ebdb3eeaf8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80");
        list.add("https://images.unsplash.com/photo-1621221814007-1d5bc6a30c0a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80");
        list.add("https://images.unsplash.com/photo-1547825407-2d060104b7f8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1050&q=80");
        list.add("https://images.unsplash.com/photo-1473115209096-e0375dd6b3b3?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80");

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
        Log.e("product trong home", product+"");
        Intent intent = new Intent(getContext(), ProductDetailActivity.class);
        intent.putExtra("Product",product);
        startActivity(intent);
    }
}