package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
    EditText edtHouseId, edtUserId, edtEventDetails, edtRent, edtPlaceId;
    Button btnAddEvent;
    TextView tvDate, tvEndDate;
    ImageButton btnDate, btnEndDate;


    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_layout);


        edtHouseId = findViewById(R.id.edt_houseId);
        tvDate = findViewById(R.id.tv_date);
        tvEndDate = findViewById(R.id.tv_eventEndDate);
        edtUserId = findViewById(R.id.edt_userId);
        edtEventDetails = findViewById(R.id.edt_eventDetail);
        edtRent = findViewById(R.id.edt_rent);
        edtPlaceId = findViewById(R.id.edt_placeId);

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
                        tvDate.setText(year + "-" + 0 + (month + 1) + "-" + dayOfMonth);
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
                        tvEndDate.setText(year + "-" + 0 + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });


        //add event

        btnAddEvent = findViewById(R.id.btn_event);


        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHouseId = edtHouseId.getText().toString();
                String strUserid = edtUserId.getText().toString();
                String strDate = tvDate.getText().toString();
                String strEndDate = tvEndDate.getText().toString();
                String strEventDetails = edtEventDetails.getText().toString();
                String strRent = edtRent.getText().toString();
                String strPlaceId = edtPlaceId.getText().toString();


                Log.e("HouseId: ", strHouseId);
                Log.e("Userid", strUserid);
                Log.e("Date: ", strDate);
                Log.e("EndDate: ", strEndDate);
                Log.e("EventDetails:", strEventDetails);
                Log.e("Rent", strRent);
                Log.e("PlaceId", strPlaceId);


                apiCall(strHouseId, strUserid, strDate, strEndDate, strEventDetails, strRent, strPlaceId);

            }


        });

    }

    private void apiCall(String strHouseId, String strUserId, String strDate, String strEndDate, String strEventDetails, String strRent, String strPlaceId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.EVENT_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);

                String h = edtHouseId.getText().toString();
                String u = edtUserId.getText().toString();
                String d = tvDate.getText().toString();
                String ed = tvEndDate.getText().toString();
                String de = edtEventDetails.getText().toString();
                String r = edtRent.getText().toString();
                String pi = edtPlaceId.getText().toString();


                Intent intent = new Intent(EventLayout.this, EventShowActivity.class);

                intent.putExtra("houseId",h);
                intent.putExtra("userId",u);
                intent.putExtra("eventDate",d);
                intent.putExtra("eventEndDate",ed);
                intent.putExtra("details",de);
                intent.putExtra("rent",r);
                intent.putExtra("placeId",pi);
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
                hashMap.put("house", strHouseId);
                hashMap.put("user", strUserId);
                hashMap.put("eventDate", strDate);
                hashMap.put("eventEndDate", strEndDate);
                hashMap.put("eventDetails", strEventDetails);
                hashMap.put("rent", strRent);
                hashMap.put("placeId", strPlaceId);

                return hashMap;


            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}




















