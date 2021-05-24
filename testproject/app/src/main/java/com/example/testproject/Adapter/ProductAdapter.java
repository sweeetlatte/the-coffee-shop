package com.example.testproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.Interface.OnItemClickListener;
import com.example.testproject.Model.Product;
import com.example.testproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder>{

    Context context;
    ArrayList <Product> productArrayList;
    private OnItemClickListener itemClickListener;
    Product product;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList, OnItemClickListener itemClickListener) {
        this.context = context;
        this.productArrayList = productArrayList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_new_product, null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        product = productArrayList.get(position);
        holder.tvNameProduct.setText(product.getNameProduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPriceProduct.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+ " VNĐ");
        Picasso.get().load(product.getSrcImgProduct()).into(holder.imgProduct);
        String src = product.getSrcImgProduct();
        Log.e("src",src);
//                     .placeholder(R.drawable.noimage)
//                     .error(R.drawable.error)
//                     .into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */


    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tvNameProduct;
        public TextView tvPriceProduct;
        public ImageView imgProduct;
        Product product;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = (TextView) itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = (TextView) itemView.findViewById(R.id.tvPriceProduct);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClickListener(product);
                }
            });
        }
    }
}
