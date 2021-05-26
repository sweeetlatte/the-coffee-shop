package com.example.testproject.Activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.testproject.Adapter.ItemCartAdapter;
import com.example.testproject.Model.ItemCart;
import com.example.testproject.R;

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
        CheckData();
    }

    private void InitUI() {
        listView = (ListView) findViewById(R.id.listViewCart);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        tvEmptyCart = (TextView) findViewById(R.id.tvEmptyCart);
        itemCartAdapter = new ItemCartAdapter(getApplicationContext(),MainActivity.itemCartList);
    }

    private void CheckData() {
        if(MainActivity.itemCartList.size() <0 ) {
            itemCartAdapter.notifyDataSetChanged();
            tvEmptyCart.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        } else {
            itemCartAdapter.notifyDataSetChanged();
            tvEmptyCart.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
    }
}

