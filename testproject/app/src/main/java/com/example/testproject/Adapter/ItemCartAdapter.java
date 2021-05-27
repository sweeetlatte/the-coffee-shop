package com.example.testproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.testproject.Model.ItemCart;
import com.example.testproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemCartAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ItemCart> itemCartList;

    public ItemCartAdapter(Context context, int layout, ArrayList<ItemCart> itemCartList) {
        this.context = context;
        this.layout = layout;
        this.itemCartList = itemCartList;
    }

    public int getCount() {
        return itemCartList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemCartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView tvNameProduct;
        TextView tvPriceProduct;
        TextView  tvTopping;
        TextView tvQuantity;
        TextView tvTotal;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(layout,null);
            viewHolder.imageView = convertView.findViewById(R.id.anhMon);
            viewHolder.tvNameProduct = convertView.findViewById(R.id.tenMon);
            viewHolder.tvPriceProduct = convertView.findViewById(R.id.giaBan);
            viewHolder.tvTopping = convertView.findViewById(R.id.topping);
            viewHolder.tvTotal = convertView.findViewById(R.id.thanhTien);
            viewHolder.tvQuantity = convertView.findViewById(R.id.soLuong);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // gán giá trị
        viewHolder.tvNameProduct.setText(itemCartList.get(position).getNameProduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvPriceProduct.setText(decimalFormat.format(itemCartList.get(position).getPriceProduct())+ " VNĐ");
        Picasso.get().load(String.valueOf(itemCartList.get(position).getSrcImgProduct())).into(viewHolder.imageView);



        viewHolder.tvTopping.setText(itemCartList.get(position).getTopping());
        viewHolder.tvQuantity.setText(itemCartList.get(position).getQuantity()+"");
        viewHolder.tvTotal.setText(decimalFormat.format(itemCartList.get(position).getTotal()));

       // viewHolder.tvQuantity.setText(itemCartList.get(position).getQuantity());
        Log.e("tên",itemCartList.get(position).getTotal() + itemCartList.get(position).getQuantity() + "" );
        return convertView;
    }



}
