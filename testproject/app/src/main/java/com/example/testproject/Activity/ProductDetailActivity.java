package com.example.testproject.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.R;

public class ProductDetailActivity extends Activity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
       // txt = (TextView) findViewById(R.id.txt);


    }
}
