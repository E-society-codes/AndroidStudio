package com.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.text.format.Time;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myapplication.utils.VolleySingleton;
import com.myapplication.utils.util;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;


public class eventUpdateActivity extends AppCompatActivity {

    EditText edt_eventDetail, edt_rent, edt_HouseId;
    Button btn_event;
    TextView tvDate, tvEndDate;
    ImageButton btnDate, btnEndDate;

    private int date;
    private int month;
    private int year;
    private String id;

    public eventUpdateActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_layout);

        Intent i = getIntent();

        edt_eventDetail = findViewById(R.id.edt_eventDetail);
        edt_rent = findViewById(R.id.edt_rent);
        btn_event = findViewById(R.id.btn_event);
        btnDate = findViewById(R.id.btn_date);
        btnEndDate = findViewById(R.id.btn_endDate);


        //    Log.e("MAINTENANCE_ID", String.valueOf(maintenanceId));
        String strEventDate = i.getStringExtra("EVENT_DATE");
        String strEventEndDate = i.getStringExtra("EVENT_END_DATE");
        String strEventDetails = i.getStringExtra("EVENT_DETAILS");
        String strRent = i.getStringExtra("RENT");
        String strEventId = i.getStringExtra("EVENT_ID");
        String strEventHouse = i.getStringExtra("EVENT_HOUSE");


        edt_HouseId.setText(strEventHouse);


        //set text
        EventLangModel eventLangModel = new EventLangModel();
        edt_eventDetail.setText(strEventDetails);
        edt_rent.setText(strRent);
        tvDate.setText(strEventDate);
        tvEndDate.setText(strEventEndDate);


        btn_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strEventDate = tvEndDate.getText().toString();
                String strEventEndDate = tvDate.getText().toString();
                String strEventDetails = edt_eventDetail.getText().toString();
                String strRent = edt_rent.getText().toString();


                Log.e("Date", strEventDate);
                Log.e("EndDate ", strEventEndDate);
                Log.e("EventDetails", strEventDetails);


                apiCall(strEventId, strEventDate, strEventEndDate, strEventDetails, strRent);

            }
        });


        //date
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(eventUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        Log.e("year: ", String.valueOf(year));
                        Log.e("month: ", String.valueOf(month));
                        Log.e("day: ", String.valueOf(dayOfMonth));

                        long dtDob = chosenDate.toMillis(true);

                        strDate = DateFormat.format("yyyy/MM/dd", dtDob);

                        tvDate.setText(strDate);
                    }
                }, date, month, year);
                datePickerDialog.show();
            }
        });

        btnEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(eventUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);

                        strDate = DateFormat.format("yyyy/MM/dd", dtDob);

                        tvEndDate.setText(strDate);

                    }
                }, date, month, year);
                datePickerDialog.show();
            }
        });


    }



    private void apiCall(String strEventID, String strEventDate, String strEventEndDate ,String strEventDetails , String strRent ) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, util.EVENT_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(eventUpdateActivity.this, EventShowActivity.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error: ", String.valueOf(error));
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                Log.e("id in update map:",strEventID);
                hashMap.put("eventId", strEventID);
                hashMap.put("eventDate", strEventDate);
                hashMap.put("eventEndDate", strEventEndDate);
                hashMap.put("eventDetails", strEventDetails);
                hashMap.put("rent",strRent);

                return hashMap;


            }
        };
        VolleySingleton.getInstance(eventUpdateActivity.this).addToRequestQueue(stringRequest);

    }
}
