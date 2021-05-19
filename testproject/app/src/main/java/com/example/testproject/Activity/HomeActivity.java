package com.example.testproject.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        viewPager2 = findViewById(R.id.slider);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.account));
        sliderItems.add(new SliderItem(R.drawable.account));
        sliderItems.add(new SliderItem(R.drawable.account));
        sliderItems.add(new SliderItem(R.drawable.account));
        sliderItems.add(new SliderItem(R.drawable.account));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
    }
}