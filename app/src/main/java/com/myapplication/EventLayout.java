package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;


import android.widget.TextView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myapplication.utils.VolleySingleton;
import com.myapplication.utils.util;

import java.text.BreakIterator;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EventLayout extends AppCompatActivity {
    EditText edtEventDetails, edtRent;
    Button btnAddEvent;
    TextView tvDate, tvEndDate;
    ImageButton btnDate, btnEndDate;


    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_layout);


        tvDate = findViewById(R.id.tv_date);
        tvEndDate = findViewById(R.id.tv_eventEndDate);

        edtEventDetails = findViewById(R.id.edt_eventDetail);
        edtRent = findViewById(R.id.edt_rent);


        //date
        btnDate = findViewById(R.id.btn_date);
        btnEndDate = findViewById(R.id.btn_endDate);
        tvDate = findViewById(R.id.tv_date);
        tvEndDate = findViewById(R.id.tv_eventEndDate);

        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(EventLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);

                        strDate = DateFormat.format("yyyy/MM/dd", dtDob);

                        //txtDate.setText(strDate);


                        tvDate.setText(strDate);
                    }
                }, date, month, year);
                datePickerDialog.show();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(EventLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strEndDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);

                        strEndDate = DateFormat.format("yyyy/MM/dd", dtDob);

                        tvEndDate.setText(strEndDate);
                    }
                }, date, year, month);
                datePickerDialog.show();
            }
        });


        //add event
        btnAddEvent = findViewById(R.id.btn_event);


        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String strDate = tvDate.getText().toString();
                String strEndDate = tvEndDate.getText().toString();
                String strEventDetails = edtEventDetails.getText().toString();
                String strRent = edtRent.getText().toString();


                Log.e("Date: ", strDate);
                Log.e("EndDate: ", strEndDate);
                Log.e("EventDetails:", strEventDetails);
                Log.e("Rent", strRent);


                apiCall(strDate, strEndDate, strEventDetails, strRent);

            }


        });

    }

    private void apiCall(String strDate, String strEndDate, String strEventDetails, String strRent) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.EVENT_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);

//                String strHouseId = edtHouseId.getText().toString();
//                String strUserId = edtUserId.getText().toString();
//                String strDate = tvDate.getText().toString();
//                String strEndDate = tvEndDate.getText().toString();
//                String strEventDetails = edtEventDetails.getText().toString();
//                String strRent = edtRent.getText().toString();
//                String strPlaceId = edtPlaceId.getText().toString();


                Intent intent = new Intent(EventLayout.this, EventShowActivity.class);

//                intent.putExtra("houseId",strHouseId);
//                intent.putExtra("userId",strUserId);
//                intent.putExtra("eventDate",strDate);
//                intent.putExtra("eventEndDate",strEndDate);
//                intent.putExtra("details",strEventDetails);
//                intent.putExtra("rent",strRent);
//                intent.putExtra("placeId",strPlaceId);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();

                hashMap.put("eventDate", strDate);
                hashMap.put("eventEndDate", strEndDate);
                hashMap.put("eventDetails", strEventDetails);
                hashMap.put("rent", strRent);


                return hashMap;


            }
        };
        VolleySingleton.getInstance(EventLayout.this).addToRequestQueue(stringRequest);

    }
}




















