package com.example.testproject.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testproject.Activity.MainActivity;
import com.example.testproject.Activity.RegisterActivity;
import com.example.testproject.R;
import com.example.testproject.Untils.Server;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

public class ReservationFragment extends Fragment {
    View view;
    TextView res_first_name;
    TextView res_last_name;
    TextView res_phone_number;
    TextView res_date;
    TextView res_time;
    EditText edtNumberOfPeople;
    EditText edtMessage;
    Button btnReservation;
    String numberOfPeople;
    String mesage;
    String date;
    String time;
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
        res_date = (TextView) view.findViewById(R.id.res_date);
        res_time = (TextView) view.findViewById(R.id.res_time);
        edtNumberOfPeople = (EditText) view.findViewById(R.id.edtNumberOfPeople);
        edtMessage = (EditText) view.findViewById(R.id.edtMessage);
        btnReservation = (Button) view.findViewById(R.id.btnReservation);
        res_first_name.setText(MainActivity.firtNameCustomer);
        res_last_name.setText(MainActivity.lastNameCustomer);
        res_phone_number.setText(MainActivity.phoneCustomer);
        res_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectDate();
            }
        });
        res_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectTime();
            }
        });
        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDB();
            }
        });
    }

    private void InsertDB() {
        numberOfPeople = edtNumberOfPeople.getText().toString();
        mesage = edtMessage.getText().toString().trim();
        date = res_date.getText().toString().trim();
        time = res_time.getText().toString().trim();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathInsertDatBan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("dmgj", response.toString());
                if (!response.equals("0"))  {
                    Toast.makeText(getContext(),"Reservation Accepted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    //to-do
                    Toast.makeText(getContext(),"Invalid Information!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("idCustomer",MainActivity.MaKH);
                hashMap.put("numberOfPeople",numberOfPeople);
                hashMap.put("mesage",mesage);
                hashMap.put("date",date);
                hashMap.put("time",time);
                return hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void SelectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                calendar.set(0,0,0,hourOfDay,minute);
                res_time.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, hour + 1, minute, true);
        timePickerDialog.show();
    }

    private void SelectDate() {
        Calendar calendar = Calendar.getInstance();
        int daycurrent = calendar.get(Calendar.DATE);
        int monthcurrent = calendar.get(Calendar.MONTH);
        int yearcurrent = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog
                (getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        res_date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },  yearcurrent, monthcurrent, daycurrent);
        calendar.add(Calendar.DATE, 30);
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTime().getTime());
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        datePickerDialog.show();
    }
}