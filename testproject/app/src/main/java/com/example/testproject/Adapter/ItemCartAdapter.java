package com.example.testproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testproject.Model.ItemCart;
import com.example.testproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemCartAdapter extends BaseAdapter {
    Context context;
    ArrayList<ItemCart> itemCartList;

    public ItemCartAdapter(Context context, ArrayList<ItemCart> itemCartList) {
        this.context = context;
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
        return 0;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView tvNameProduct;
        TextView tvPriceProduct;
        TextView  tvTopping;
        TextView tvTotal;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(viewHolder == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView =inflater.inflate(R.layout.row_item_cart,null);
            viewHolder.imageView = convertView.findViewById(R.id.anhMon);
            viewHolder.tvNameProduct = convertView.findViewById(R.id.tenMon);
            viewHolder.tvPriceProduct = convertView.findViewById(R.id.giaBan);
            viewHolder.tvTopping = convertView.findViewById(R.id.topping);
            viewHolder.tvTotal = convertView.findViewById(R.id.topping);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // gán giá trị
        viewHolder.tvNameProduct.setText(itemCartList.get(position).getNameProduct());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.tvPriceProduct.setText(decimalFormat.format(itemCartList.get(position).getPriceProduct())+ "VNĐ");
        Picasso.get().load(String.valueOf(itemCartList.get(position))).into(viewHolder.imageView);
        viewHolder.tvTopping.setText(itemCartList.get(position).getTopping());
        viewHolder.tvTotal.setText(itemCartList.get(position).getTotal());
        return convertView;
    }

}
