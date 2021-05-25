package com.example.testproject.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.ViewFlipper;

import com.example.testproject.Adapter.Menu_ViewPageAdapter;
import com.example.testproject.R;
import com.google.android.material.tabs.TabLayout;


public class MenuFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Menu_ViewPageAdapter viewPageAdapter;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        InitUI(view);
        return view;
    }
    private void InitUI(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);

        viewPageAdapter = new Menu_ViewPageAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT );
        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }
}