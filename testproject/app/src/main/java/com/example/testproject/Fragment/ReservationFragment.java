package com.example.testproject.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testproject.Activity.MainActivity;
import com.example.testproject.R;

import org.jetbrains.annotations.NotNull;

public class ReservationFragment extends Fragment {
    View view;
    TextView res_first_name;
    TextView res_last_name;
    TextView res_phone_number;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);

        view = inflater.inflate(R.layout.fragment_reservation, container, false);
        InitUI(view);
        return view;
    }

    private void InitUI(View view) {
        res_first_name = (TextView) view.findViewById(R.id.res_first_name);
        res_last_name = (TextView) view.findViewById(R.id.res_last_name);
        res_phone_number = (TextView) view.findViewById(R.id.res_phone_number);
        res_first_name.setText(MainActivity.firtNameCustomer);
        res_last_name.setText(MainActivity.lastNameCustomer);
        res_phone_number.setText(MainActivity.phoneCustomer);
    }
}
