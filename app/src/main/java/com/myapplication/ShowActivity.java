package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {

    TextView tvComplaintDate,tvComplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        tvComplaintDate = findViewById(R.id.tv_complaintDate);
        tvComplaint = findViewById(R.id.tv_complaint);

        String date = getIntent().getStringExtra("date");
        String complaint = getIntent().getStringExtra("complaint");


       tvComplaintDate.setText(date);
       tvComplaint.setText(complaint);




    }
}