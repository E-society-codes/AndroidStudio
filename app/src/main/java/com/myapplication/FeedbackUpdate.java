package com.myapplication;

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

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myapplication.utils.VolleySingleton;
import com.myapplication.utils.util;

import java.util.HashMap;
import java.util.Map;

public class FeedbackUpdate extends AppCompatActivity {

    EditText edt_feedbackId,edt_feedbackHouseId,edt_giveFeedbackId,edt_acknowledgement;
    TextView tv_feedbackDate;
    ImageButton btn_feedbackDate;
    Button btn_feedback;

    private int date;
    private int month;
    private int year;
    private String id;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        Intent i = getIntent();

        edt_feedbackId = findViewById(R.id.edt_feedbackId);
        edt_feedbackHouseId = findViewById(R.id.edt_feedbackHouseId);
        edt_giveFeedbackId = findViewById(R.id.edt_giveFeedbackId);
        edt_acknowledgement = findViewById(R.id.edt_acknowledgement);
        tv_feedbackDate = findViewById(R.id.tv_feedbackDate);
        btn_feedbackDate= findViewById(R.id.btn_feedbackDate);
        btn_feedback = findViewById(R.id.btn_feedback);


        //    Log.e("MAINTENANCE_ID", String.valueOf(maintenanceId));
        String strFeedbackId = i.getStringExtra("FEEDBACK ID");
        String strHouseId = i.getStringExtra("HOUSE NAME");
        String strFeedback = i.getStringExtra("FEEDBACK");
        String strDate = i.getStringExtra("DATE");
        String strAcknowledgement = i.getStringExtra("ACKNOWLEDGEMENT");




        //set text
        MemberLangModel memberLangModel = new MemberLangModel();
        edt_feedbackHouseId.setText(strHouseId);
        edt_feedbackId.setText(strFeedbackId);
        edt_giveFeedbackId.setText(strFeedback);
        tv_feedbackDate.setText(strDate);
        edt_acknowledgement.setText(strAcknowledgement);


        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strHouseId = edt_feedbackHouseId.getText().toString();
                String strFeedbackId = edt_feedbackId.getText().toString();
                String strFeedback = edt_giveFeedbackId.getText().toString();
                String strDate = tv_feedbackDate.getText().toString();
                String strAcknowledgement = edt_acknowledgement.getText().toString();


                Log.e("House Id",strHouseId );
                Log.e("Feedback Id ", strFeedbackId);
                Log.e("Feedback", strFeedback);
                Log.e("Date", strDate);
                Log.e("Acknowledgement", strAcknowledgement);



                apiCall(strHouseId,strFeedbackId,strFeedback,strDate,strAcknowledgement );

            }
        });


        //date
        btn_feedbackDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(FeedbackUpdate.this, new DatePickerDialog.OnDateSetListener() {
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

                        tv_feedbackDate.setText(strDate);
                    }
                }, date, month, year);
                datePickerDialog.show();
            }
        });




    }



    private void apiCall(String strHouseId, String strFeedbackId, String strFeedback ,String strDate , String strAcknowledgement ) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, util.MEMBER_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(FeedbackUpdate.this, MemberShowActivity.class);
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
                Log.e("id in update map:",strHouseId);
                hashMap.put("feedbackId", strFeedbackId);
                hashMap.put("feedback", strFeedback);
                hashMap.put("date", strDate);
                hashMap.put("acknowledgement", strAcknowledgement);

                return hashMap;


            }
        };
        VolleySingleton.getInstance(FeedbackUpdate.this).addToRequestQueue(stringRequest);

    }
}
