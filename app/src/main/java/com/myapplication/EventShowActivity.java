package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EventShowActivity extends AppCompatActivity {

    TextView tvHouseId,tvUserId,tvDate,tvEndDate,tvDetails,tvRent,tvPlaceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_show);

        tvHouseId = findViewById(R.id.tv_houseId);
        tvUserId = findViewById(R.id.tv_userId);
        tvDate =  findViewById(R.id.tv_date);
        tvEndDate = findViewById(R.id.tv_eventEndDate);
        tvDetails = findViewById(R.id.tv_details);
        tvRent = findViewById(R.id.tv_rent);
        tvPlaceId = findViewById(R.id.tv_placeId);

        String HouseId = getIntent().getStringExtra("houseId");
        String UserId = getIntent().getStringExtra("userId");
        String date = getIntent().getStringExtra("eventDate");
        String endDate = getIntent().getStringExtra("eventEndDate");
        String details = getIntent().getStringExtra("details");
        String rent = getIntent().getStringExtra("rent");
        String placeId = getIntent().getStringExtra("placeId");


        tvHouseId.setText(HouseId);
        tvUserId.setText(UserId);
        tvDate.setText(date);
        tvEndDate.setText(endDate);
        tvDetails.setText(details);
        tvRent.setText(rent);
        tvPlaceId.setText(placeId);
    }

}