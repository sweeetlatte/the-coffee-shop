package com.example.testproject.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.testproject.Adapter.ItemCartAdapter;
import com.example.testproject.R;

import java.text.DecimalFormat;

public class CheckoutActivity extends Activity {
    Button btnOrder;
    TextView tvCheckOutTotal;
    TextView tvCheckOutSum;
    TextView tvCheckOutShip;
    ListView lvCheckOut;
    ItemCartAdapter adapter;
    int total = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_checkout);
        InitUI();
        EvenUntil();
        SumTotal();
    }

    private void SumTotal() {
   //     int ship = Integer.parseInt(tvCheckOutShip.getText().toString().trim()) ;
    //    int sum = total + ship;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvCheckOutTotal.setText(decimalFormat.format(total+ 10000) + " VNĐ");
    }

    private void InitUI() {
        btnOrder = (Button) findViewById(R.id.btnCheckOut);
        tvCheckOutShip = (TextView) findViewById(R.id.tv_checkout_ship);
        tvCheckOutSum = (TextView) findViewById(R.id.tv_checkout_sum);
        tvCheckOutTotal = (TextView) findViewById(R.id.tv_checkout_total);
        lvCheckOut = (ListView) findViewById(R.id.lv_checkout);
        adapter = new ItemCartAdapter(getApplicationContext(),R.layout.row_item_cart,MainActivity.itemCartList);
        lvCheckOut.setAdapter(adapter);


    }
    private void EvenUntil() {
        for(int i = 0; i < MainActivity.itemCartList.size(); i++){
            total = total + MainActivity.itemCartList.get(i).total;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvCheckOutSum.setText(decimalFormat.format(total) + " VNĐ");
    }
}
