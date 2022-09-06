package com.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText et_userId,et_complaintHouseId,et_date,et_complaint,et_isResolved;
    Button btn_complaint;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        et_userId = findViewById(R.id.et_userId);
        et_complaintHouseId = findViewById(R.id.et_complaintHouseId);
        et_date = findViewById(R.id.et_date);
        et_complaint = findViewById(R.id.et_complaint);
        et_isResolved  = findViewById(R.id.et_isResolved);
        btn_complaint = findViewById(R.id.btn_complaint);

//        btn_complaint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String pi = et_placeId.getText().toString();
//                String ui = et_userId.getText().toString();
//                String ci = et_complaintHouseId.getText().toString();
//                String d = et_date.getText().toString();
//                String c = et_complaint.getText().toString();
//                String ir = et_isResolved.getText().toString();
//
//                Intent intent = new Intent(MainActivity.this,EventLayout.class);
//                intent.putExtra("placeId",pi);
//                intent.putExtra("userId",ui);
//                intent.putExtra("complaintHouseId",ci);
//                intent.putExtra("date",d);
//                intent.putExtra("complaint",c);
//                intent.putExtra("isResolved",ir);
//
//                startActivity(intent);
//            }
//        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        et_date.setText(date);
                    }
                },year,month,day);
                        datePickerDialog.show();

            }
        });
    }
}