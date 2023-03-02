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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FeedbackLayout extends AppCompatActivity {
    EditText edt_feedbackId,edt_feedbackHouseId,edt_giveFeedbackId,edt_acknowledgement;
    TextView tv_feedbackDate;
    ImageButton btn_feedbackDate;
    Button btn_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);



        edt_feedbackId = findViewById(R.id.edt_feedbackId);
        edt_feedbackHouseId = findViewById(R.id.edt_feedbackHouseId);
        edt_giveFeedbackId = findViewById(R.id.edt_giveFeedbackId);
        edt_acknowledgement = findViewById(R.id.edt_acknowledgement);


        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        btn_feedbackDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FeedbackLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        CharSequence strDate = null;
                        Time chosenDate = new Time();
                        chosenDate.set(dayOfMonth, month, year);
                        long dtDob = chosenDate.toMillis(true);

                        strDate = DateFormat.format("yyyy/MM/dd", dtDob);

                        //txtDate.setText(strDate);


                        tv_feedbackDate.setText(strDate);
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });




        //add event
        btn_feedback = findViewById(R.id.btn_feedback);


        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               String strFeedbackId = edt_feedbackId.getText().toString();
               String strHouseId = edt_feedbackHouseId.getText().toString();
               String strDate = tv_feedbackDate.getText().toString();
               String strFeedback = edt_giveFeedbackId.getText().toString();
               String strAcknowledgement = edt_acknowledgement.getText().toString();

                Log.e("FeedbackId:",strFeedbackId);
                Log.e("HouseId:",strHouseId);
                Log.e("Date: ", strDate);
                Log.e("Feedback: ",strFeedback);
                Log.e("Acknowledgement: ",strAcknowledgement);



                apiCall(strFeedbackId,strHouseId,strDate,strFeedback,strAcknowledgement);

            }


        });

    }

    private void apiCall(String strFeedbackId, String strHouseId, String strDate, String strFeedback, String strAcknowledgement) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, util.FEEDBACK_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);

                Intent intent = new Intent(FeedbackLayout.this, FeedbackShowActivity.class);

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

                hashMap.put("feedbackId", strFeedbackId);
                hashMap.put("houseID", strHouseId);
                hashMap.put("date", strDate);
                hashMap.put("feedback", strFeedback);
                hashMap.put("acknowledgement",strAcknowledgement);

                return hashMap;

            }
        };
        VolleySingleton.getInstance(FeedbackLayout.this).addToRequestQueue(stringRequest);

    }
}
