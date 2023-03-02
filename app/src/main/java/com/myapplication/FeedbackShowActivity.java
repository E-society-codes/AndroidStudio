package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

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



public class FeedbackShowActivity extends AppCompatActivity {

    ListView feedbackList;
    FloatingActionButton btnFeedbackAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_show);

        feedbackList = findViewById(R.id.feedback_listview);

        btnFeedbackAdd = findViewById(R.id.btn_feedbackAdd);
        btnFeedbackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackShowActivity.this , FeedbackLayout.class);
                startActivity(intent);
            }
        });
        FeedbackApi();
    }



    private void FeedbackApi() {
        ArrayList<FeedbackLangModel> arrayList=new ArrayList<FeedbackLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.FEEDBACK_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        //JSONObject jsonObject2 = jsonObject1.getJSONObject("house");
                        String strFeedbackId = jsonObject1.getString("feedbackId");
                        String strHouseID=jsonObject1.getString("houseId");
                        String strFeedback=jsonObject1.getString("feedback");
                        String strDate=jsonObject1.getString("date");
                        String strAcknowledgement = jsonObject1.getString("acknowledgement");

                        FeedbackLangModel feedbackLangModel = new FeedbackLangModel();

                        feedbackLangModel.setFeedbackId(strFeedbackId);
                        feedbackLangModel.setDate(strDate);
                        feedbackLangModel.setHouseId(strHouseID);
                        feedbackLangModel.setFeedback(strFeedback);
                        feedbackLangModel.setAcknowledgement(strAcknowledgement);

                        arrayList.add(feedbackLangModel);
                    }
                    FeedbackListAdapter feedbackListAdapter = new FeedbackListAdapter(FeedbackShowActivity.this,arrayList);
                    feedbackList.setAdapter(feedbackListAdapter);

                } catch ( JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(FeedbackShowActivity.this).addToRequestQueue(stringRequest);
    }
}