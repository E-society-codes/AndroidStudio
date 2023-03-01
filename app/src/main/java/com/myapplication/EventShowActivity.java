package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myapplication.utils.VolleySingleton;
import com.myapplication.utils.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventShowActivity extends AppCompatActivity {

    ListView eventList;

    FloatingActionButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_show);

        eventList = findViewById(R.id.event_listview);

        btnAdd=findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventShowActivity.this, EventLayout.class);
                startActivity(intent);
            }
        });

        EventApi();

    }

    private void EventApi() {
        ArrayList<EventLangModel> arrayList=new ArrayList<EventLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.EVENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                       // JSONObject jsonObject2 = jsonObject1.getJSONObject("house");
                        String strHouseId = jsonObject1.getString("houseId");
                        String strPlaceId = jsonObject1.getString("placeId");
                        String strDate = jsonObject1.getString("date");
                        String strEndDate=jsonObject1.getString("endDate");
                        String strEvent=jsonObject1.getString("event");
                        String strRent=jsonObject1.getString("rent");

                        EventLangModel eventLangModel = new EventLangModel();
                        eventLangModel.setHouse_id(strHouseId);
                        eventLangModel.setPlace_id(strPlaceId);
                        eventLangModel.setDate(strDate);
                        eventLangModel.setEventDate(strEndDate);
                        eventLangModel.setEventDetails(strEvent);
                        eventLangModel.setRent(strRent);

                        arrayList.add(eventLangModel);
                    }
                    EventListAdapter eventListAdapter = new EventListAdapter(EventShowActivity.this,arrayList);
                    eventList.setAdapter(eventListAdapter);

                } catch ( JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(EventShowActivity.this).addToRequestQueue(stringRequest);
    }
}