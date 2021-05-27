package com.example.testproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.testproject.Fragment.AccountFragment;
import com.example.testproject.Fragment.HomeFragment;
import com.example.testproject.Fragment.MenuFragment;
import com.example.testproject.Fragment.ReservationFragment;
import com.example.testproject.Model.ItemCart;
import com.example.testproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public static ArrayList<ItemCart> itemCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
        if(itemCartList == null ){
            itemCartList = new ArrayList<>();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;
                        case R.id.menu:
                            fragment = new MenuFragment();
                            break;
                        case R.id.reservation:
                            fragment = new ReservationFragment();
                            break;
                        case R.id.account:
                            fragment = new AccountFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();

                    return true;
                }
            };

}
