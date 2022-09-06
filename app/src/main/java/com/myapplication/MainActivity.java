package com.myapplication;

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
    EditText edt_userId,edt_complaintHouseId,edt_date,edt_complaint,edt_isResolved;
    Button btn_complaint;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        edt_userId = findViewById(R.id.edt_userId);
        edt_complaintHouseId = findViewById(R.id.edt_complaintHouseId);
        edt_date = findViewById(R.id.edt_date);
        edt_complaint = findViewById(R.id.edt_complaint);
        edt_isResolved  = findViewById(R.id.edt_isResolved);
        btn_complaint = findViewById(R.id.btn_complaint);

        btn_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ui = edt_userId.getText().toString();
                String ci = edt_complaintHouseId.getText().toString();
                String d = edt_date.getText().toString();
                String c = edt_complaint.getText().toString();
                String ir = edt_isResolved.getText().toString();

                Intent intent = new Intent(MainActivity.this,ShowActivity.class);

                intent.putExtra("userId",ui);
                intent.putExtra("complaintHouseId",ci);
                intent.putExtra("date",d);
                intent.putExtra("complaint",c);
                intent.putExtra("isResolved",ir);

                startActivity(intent);
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edt_date.setText(date);
                    }
                },year,month,day);
                        datePickerDialog.show();

            }
        });
    }
}