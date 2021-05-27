package com.example.testproject.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.Model.ItemCart;
import com.example.testproject.Model.Product;
import com.example.testproject.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView imageProductDetail;
    TextView nameProductDetail;
    TextView describeProductDetail;
    RadioGroup rgSize;
    RadioGroup rgTopping;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    Button btn_add_product;
    Button btn_add_in;
    Button btn_add_out;
    EditText edtNote;
    TextView edtQuatity;
    public int id;
    public String name;
    public String src;
    public String orderSize;
    public int quantity = 1;

    public String strSize = "M";
    public String strTopping = "";
    public String strNote = "";
    public int price ;
    public int tSize = 0;
    public int tTopping = 0;
    public int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        InitUI();
        GetDataDetailProduct();

    }

    private void Topping() {
        int isChecked =  rgTopping.getCheckedRadioButtonId();
        switch (isChecked){
            case R.id.radio_1:
                tTopping =  5000;
                strTopping = "Ice Cream";
                ChangeButon();
                break;
            case R.id.radio_2:
                tTopping = 10000;
                strTopping = "Sweet syrup";
                ChangeButon();
                break;
            case R.id.radio_3:
                tTopping = 5000;
                strTopping = "vanila";
                ChangeButon();
                break;
            case R.id.radio_4:
                tTopping = 8000;
                strTopping = "Butter";
                ChangeButon();
                break;
            case R.id.radio_5:
                tTopping = 5000;
                strTopping = "Spices";
                ChangeButon();
                break;
            case R.id.radio_6:
                tTopping = 5000;
                strTopping = "Non-diary milk";
                ChangeButon();
                break;
        }
    }
    private void ChangeButon(){
        total = quantity*(price  + tTopping + tSize) ;
        btn_add_product.setText( "ADD " + decimalFormat.format(total)+ " VNĐ");
    }

    private void Size() {
        int isChecked =  rgSize.getCheckedRadioButtonId();
        switch (isChecked){
            case R.id.radio_a:
                tSize = 5000;
                strSize = "L";
                ChangeButon();
                break;
            case R.id.radio_b:
                tSize = 0 ;
                strSize = "M";
                ChangeButon();
                break;
            case R.id.radio_c:
                strSize = "S";
                tSize = - 5000;
                ChangeButon();
                break;
        }
    }

    private void GetDataDetailProduct() {
        Intent intent = new Intent();
        Product product = (Product) getIntent().getSerializableExtra("Product");
        price = product.getPriceProduct();
        name = product.getNameProduct();
        id = product.getIdProduct();
        src = product.getSrcImgProduct();
        nameProductDetail.setText(product.getNameProduct());
        describeProductDetail.setText(product.getDescribe());
        Picasso.get().load(product.getSrcImgProduct()).into(imageProductDetail);
        btn_add_product.setText( "ADD " + decimalFormat.format(product.getPriceProduct())+ " VNĐ");
        total = price;
    }

    @SuppressLint("WrongViewCast")
    private void InitUI() {
        imageProductDetail = (ImageView) findViewById(R.id.imageProductDetail);
        nameProductDetail = (TextView) findViewById(R.id.nameProductDetail);
        describeProductDetail = (TextView) findViewById(R.id.describeProductDetail);
        btn_add_product = (Button) findViewById(R.id.btn_add_product);
        rgSize = (RadioGroup) findViewById(R.id.rgSize);
        rgTopping = (RadioGroup) findViewById(R.id.rgTopping);
        edtNote = (EditText) findViewById(R.id.note);
        edtQuatity = (TextView) findViewById(R.id.edtQuatity);
        btn_add_in = (Button) findViewById(R.id.btn_add_in);
        btn_add_out = (Button) findViewById(R.id.btn_add_out);

        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Size();
            }
        });

        rgTopping.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Topping();
            }
        });
        quantity = Integer.parseInt(edtQuatity.getText().toString().trim());
        btn_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean exists = false;
                strNote = edtNote.getText().toString().trim();
                orderSize = "Size: " + strSize +" "+ strTopping + strNote;
                if(MainActivity.itemCartList.size() > 0 ){
                    for (int i = 0; i < MainActivity.itemCartList.size(); i ++){
                        if(MainActivity.itemCartList.get(i).getIdProduct() == id && MainActivity.itemCartList.get(i).getTopping().equals(orderSize) ){
                            MainActivity.itemCartList.get(i).setQuantity(MainActivity.itemCartList.get(i).getQuantity() + quantity);
                            MainActivity.itemCartList.get(i).setTotal(MainActivity.itemCartList.get(i).getTotal() + total);
                            exists = true;
                        }
                    }
                    if(exists == false){
                        MainActivity.itemCartList.add(new ItemCart(id, name, price, src, orderSize, quantity,total));
                    }
                }
                else MainActivity.itemCartList.add(new ItemCart(id, name, price, src, orderSize, quantity,total));
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
        btn_add_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add_out.setVisibility(View.VISIBLE);
                quantity = quantity + 1;
                edtQuatity.setText(quantity +"");
                ChangeButon();
            }
        });
        btn_add_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = quantity - 1;
                edtQuatity.setText(quantity +"");
                ChangeButon();
                if(quantity <= 1 ){
                    btn_add_out.setVisibility(View.GONE);
                }

            }
        });

    }


}
