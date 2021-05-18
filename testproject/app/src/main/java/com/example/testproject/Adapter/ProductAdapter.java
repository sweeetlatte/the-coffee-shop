package com.example.testproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testproject.Model.Product;
import com.example.testproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ItemHolder>{

    Context context;
    ArrayList <Product> productArrayList;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
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
        Product product = productArrayList.get(position);
        holder.tvNameProduct.setText(product.getNameProduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvPriceProduct.setText("Giá: "+ decimalFormat.format(product.getPriceProduct())+ " VNĐ");
        Picasso.get().load(product.priceProduct)
//                     .placeholder(R.drawable.noimage)
//                     .error(R.drawable.error)
                     .into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView tvNameProduct;
        public TextView tvPriceProduct;
        public ImageView imgProduct;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNameProduct = (TextView) itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = (TextView) itemView.findViewById(R.id.tvPriceProduct);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
        }
    }
}
