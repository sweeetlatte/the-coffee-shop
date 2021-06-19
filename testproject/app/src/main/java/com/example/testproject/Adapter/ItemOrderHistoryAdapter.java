package com.example.testproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testproject.Model.ItemOrderHistory;
import com.example.testproject.R;

import java.text.DecimalFormat;
import java.util.List;

public class ItemOrderHistoryAdapter  extends BaseAdapter {
    Context context;
    int layout;
    List<ItemOrderHistory> itemList;

    public ItemOrderHistoryAdapter(Context context, int layout, List<ItemOrderHistory> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }


    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return  position;
    }

    private class ViewHolder{
        TextView txtTime, txtTotal;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if( convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            // ánh xạ

            viewHolder.txtTime = (TextView) convertView.findViewById(R.id.row_item_orderhistory_time);
            viewHolder.txtTotal = (TextView) convertView.findViewById(R.id.row_item_orderhistory_total);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        // gán giá trị
        viewHolder.txtTime.setText(itemList.get(position).getDate());
        viewHolder.txtTotal.setText(decimalFormat.format(Integer.parseInt(itemList.get(position).getTotal())) + " VNĐ");
        return convertView;
    }
}
