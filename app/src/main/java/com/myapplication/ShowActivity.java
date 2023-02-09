//package com.myapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ArrayAdapter;
//import android.widget.EditText;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.myapplication.utils.VolleySingleton;
//import com.myapplication.utils.util;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class ShowActivity extends AppCompatActivity {
//
//    ListView listView;
//    FloatingActionButton btnAdd;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show);
//
//       listView = findViewById(R.id.ls_complaint_listview);
//
//       //update button
//        btnAdd = findViewById(R.id.btn_complaintAdd);
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ShowActivity.this , MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        ComplaintApi();
//
//    }
//
//    private void ComplaintApi() {
//        ArrayList<ComplaintLangeModel> arrayList = new ArrayList<ComplaintLangeModel>();
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.MAIN_URL, new Response.Listener<String>() {
//            @Override
//            public String toString() {
//                return "$classname{}";
//            }
//
//            @Override
//            public void onResponse(String response) {
//                Log.e("TAG","onResponse:"+response);
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    for (int i = 0;i < jsonArray.length();i++){
//                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//
//                        String strComplaint = jsonObject1.getString("complaint");
//                        String strDate = jsonObject1.getString("date");
//
//
//
//                        ComplaintLangeModel complaintLangeModel = new ComplaintLangeModel();
//                        complaintLangeModel.setComplaintDate(strDate);
//                        complaintLangeModel.setComplaint(strComplaint);
//                        arrayList.add(complaintLangeModel);
//
//                    }
//                    ComplainListAdapter complainListAdapter = new ComplainListAdapter(ShowActivity.this,ArrayList);
//                    listView.setAdapter((ListAdapter) complainListAdapter);
//                    complainListAdapter.notify();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        VolleySingleton.getInstance(ShowActivity.this).addToRequestQueue(stringRequest);
//    }
//}