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

public class MemberShowActivity extends AppCompatActivity {
    ListView memberList;
    FloatingActionButton btnMemberAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_show);

        memberList = findViewById(R.id.member_listview);

        btnMemberAdd = findViewById(R.id.btn_memberAdd);
        btnMemberAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberShowActivity.this , memberLayout.class);
                startActivity(intent);
            }
        });
        MemberApi();
    }

    private void MemberApi() {
        ArrayList<MemberLangModel> arrayList=new ArrayList<MemberLangModel>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, util.MEMBER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonObject2 = jsonObject1.getJSONObject("house");
                        String strHouseId=jsonObject2.getString("_id");
                        String strMemberId=jsonObject1.getString("_id");
                        String strName = jsonObject1.getString("memberName");
                        String strDate=jsonObject1.getString("date");
                        String strGender=jsonObject1.getString("gender");
                        String strContact=jsonObject1.getString("contact no.");
                        String strAge = jsonObject1.getString("age");

                      MemberLangModel memberLangModel = new MemberLangModel();
                        memberLangModel.set_id(strMemberId);
                        memberLangModel.setHouseId(strHouseId);
                        memberLangModel.setMemberName(strName);
                        memberLangModel.setDateOfBirth(strDate);
                        memberLangModel.setAge(strAge);
                        memberLangModel.setGender(strGender);
                        memberLangModel.setContactNo(strContact);

                        arrayList.add(memberLangModel);
                    }
                    MemberListAdapter memberListAdapter = new MemberListAdapter(MemberShowActivity.this,arrayList);
                    memberList.setAdapter(memberListAdapter);

                } catch ( JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInstance(MemberShowActivity.this).addToRequestQueue(stringRequest);
    }
}

