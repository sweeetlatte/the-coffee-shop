package com.example.testproject.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testproject.Model.Product;
import com.example.testproject.R;

public class DetailOrderHistoryActivity extends Activity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_history);
        InitUI();
  //      LoadDataHistoryOrder();
    }

    private void InitUI() {
        tv = (TextView) findViewById(R.id.tv);
        Intent intent = new Intent();
        String product = (String) getIntent().getSerializableExtra("orderid");
        tv.setText(product);
    }
}
