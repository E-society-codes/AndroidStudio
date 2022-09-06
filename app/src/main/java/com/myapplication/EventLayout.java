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

public class EventLayout extends AppCompatActivity {
    EditText edtEventDate,edtEndDate,edtHouseId,edtUserId,edtEventDetials,edtRent,edtIsAvailablle,edtPlaceId;
    Button btnAddEvent;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_layout);

        edtEventDate = findViewById(R.id.edt_eventDate);
        edtEndDate = findViewById(R.id.edt_eventEndDate);
        edtHouseId = findViewById(R.id.edt_houseId);
        edtUserId = findViewById(R.id.edt_userId);
        edtEventDetials = findViewById(R.id.edt_eventDetail);
        edtRent = findViewById(R.id.edt_rent);
        edtIsAvailablle = findViewById(R.id.edt_isAvailable);
        edtPlaceId = findViewById(R.id.edt_placeId);

        btnAddEvent = findViewById(R.id.btn_event);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hi = edtHouseId.getText().toString();
                String ui = edtUserId.getText().toString();
                String d = edtEventDate.getText().toString();
                String end = edtEndDate.getText().toString();
                String ed = edtEventDetials.getText().toString();
                String r = edtRent.getText().toString();
                String ia = edtIsAvailablle.getText().toString();
                String pi = edtPlaceId.getText().toString();

                Intent intent = new Intent(EventLayout.this,EventActivity.class);

                intent.putExtra("houseId",hi);
                intent.putExtra("userId",ui);
                intent.putExtra("eventDetails",ed);
                intent.putExtra("eventDate",d);
                intent.putExtra("eventEndDate",end);
                intent.putExtra("rent",r);
                intent.putExtra("isAvailable",ia);
                intent.putExtra("placeId",pi);

                startActivity(intent);
            }
        });



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edtEventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EventLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edtEventDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
        edtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EventLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edtEndDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
    }
}















