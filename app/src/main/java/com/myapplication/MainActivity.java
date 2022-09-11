package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myapplication.utils.VolleySingleton;
import com.myapplication.utils.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edt_complaint;
    Button btn_complaint;
    TextView tv_date;
    ImageButton imgDate;
    private int date, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_date = findViewById(R.id.tv_date);
        edt_complaint = findViewById(R.id.edt_complaint);




        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DAY_OF_MONTH);


        imgDate = findViewById(R.id.btn_date);


        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tv_date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        //next page print


        btn_complaint = findViewById(R.id.btn_complaint);
        btn_complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strDate = tv_date.getText().toString();
                String strComplaint = edt_complaint.getText().toString();


                Log.e("Date: ", strDate);
                Log.e("Complaint", strComplaint);


                apiCall(strComplaint, strDate);

            }


        });

    }

    private void apiCall(String strDate, String strComplaint) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.COMPLAINT_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);

                String date = tv_date.getText().toString();
                String complain = edt_complaint.getText().toString();


                Intent intent = new Intent(MainActivity.this, ShowActivity.class);

                intent.putExtra("date", date);
                intent.putExtra("complaint", complain);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("date", strDate);
                hashMap.put("complaint", strComplaint);


                return hashMap;


            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}


