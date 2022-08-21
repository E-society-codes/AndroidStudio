package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ComplaintShow extends AppCompatActivity {

    TextView et_placeId,et_userId,et_complaintHouseId,et_date,et_complaint,et_isResolved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_show);

        et_placeId = findViewById(R.id.et_placeId);
        et_userId = findViewById(R.id.et_userId);
        et_complaintHouseId = findViewById(R.id.et_complaintHouseId);
        et_date = findViewById(R.id.et_date);
        et_complaint = findViewById(R.id.et_complaint);
        et_isResolved  = findViewById(R.id.et_isResolved);

        String placeId = getIntent().getStringExtra("placeId");
        String userId = getIntent().getStringExtra("userId");
        String complaintHouseId = getIntent().getStringExtra("complaintHouseId");
        String date = getIntent().getStringExtra("date");
        String complaint = getIntent().getStringExtra("complaint");
        String isResolved = getIntent().getStringExtra("isResolved");

        et_placeId.setText(placeId);
        et_userId.setText(userId);
        et_complaintHouseId.setText(complaintHouseId);
        et_date.setText(date);
        et_complaint.setText(complaint);
        et_isResolved.setText(isResolved);
    }
}