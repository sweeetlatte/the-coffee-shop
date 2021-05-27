package com.example.testproject.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.testproject.Adapter.ItemCartAdapter;
import com.example.testproject.Model.ItemCart;
import com.example.testproject.Model.Product;
import com.example.testproject.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartActivity extends Activity {
    ListView listView;
    TextView tvTotal;
    TextView tvEmptyCart;
    Button btnCheckOut;
    ItemCartAdapter itemCartAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        InitUI();
        EvenUntil();
//        CheckData();
    }

    private void EvenUntil() {
        long total = 0;
        for(int i = 0; i < MainActivity.itemCartList.size(); i++){
            total = total + MainActivity.itemCartList.get(i).total;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTotal.setText(decimalFormat.format(total) + " VNĐ");
    }

    private void InitUI() {
        listView = (ListView) findViewById(R.id.listViewCart);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        itemCartAdapter = new ItemCartAdapter(getApplicationContext(),R.layout.row_item_cart, MainActivity.itemCartList );
        listView.setAdapter(itemCartAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Delete Order");
                builder.setMessage("Do you really want to delete this order ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.itemCartList.remove(position);
                        itemCartAdapter.notifyDataSetChanged();
                        EvenUntil();
                    }
                });
                builder.setNegativeButton("Cancer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        itemCartAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();
                return true;
            }
        });

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.itemCartList.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                    startActivity(intent);

                } else Toast.makeText(getApplicationContext(), "empty cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void CheckData() {
//        if(MainActivity.itemCartList.size() <0 ) {
//            itemCartAdapter.notifyDataSetChanged();
//            tvEmptyCart.setVisibility(View.VISIBLE);
//            listView.setVisibility(View.INVISIBLE);
//        } else {
//            itemCartAdapter.notifyDataSetChanged();
//            tvEmptyCart.setVisibility(View.INVISIBLE);
//            listView.setVisibility(View.VISIBLE);
//        }
//    }
}

