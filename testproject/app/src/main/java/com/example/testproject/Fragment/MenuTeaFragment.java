package com.example.testproject.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.testproject.R;

public class MenuTeaFragment extends Fragment {
    View view;

    public MenuTeaFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        view = inflater.inflate(R.layout.menu_tea_fragment, container, false);
        InitUI(view);
        return view;
    }

    private void InitUI(View view) {
    }
}
