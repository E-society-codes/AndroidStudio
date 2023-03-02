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

public class MemberUpdate extends AppCompatActivity {

    EditText edt_houseId,edt_memberName,edt_age,edt_contactNo;
    TextView tv_dateOfBirth;
    ImageButton btn_memberDate;
    Button btn_member;

    private int date;
    private int month;
    private int year;
    private String id;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_layout);

        Intent i = getIntent();

        edt_houseId = findViewById(R.id.edt_houseId);
        edt_memberName = findViewById(R.id.edt_memberName);
        edt_age = findViewById(R.id.edt_age);
        edt_contactNo = findViewById(R.id.edt_contactNo);
        tv_dateOfBirth = findViewById(R.id.tv_dateOfBirth);
        btn_memberDate = findViewById(R.id.btn_memberDate);
        btn_member = findViewById(R.id.btn_member);


        //    Log.e("MAINTENANCE_ID", String.valueOf(maintenanceId));
        String strHouseId = i.getStringExtra("HOUSE ID");
        String strMemberName = i.getStringExtra("MEMBER NAME");
        String strAge = i.getStringExtra("AGE");
        String strContactNo = i.getStringExtra("CONTACT NO.");
        String strDateOfBirth = i.getStringExtra("DATE_OF_BIRTH");




        //set text
        MemberLangModel memberLangModel = new MemberLangModel();
        edt_houseId.setText(strHouseId);
        edt_memberName.setText(strMemberName);
        edt_age.setText(strAge);
        edt_contactNo.setText(strContactNo);
        tv_dateOfBirth.setText(strDateOfBirth);


        btn_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String strHouseId = edt_houseId.getText().toString();
                String strMemberName = edt_memberName.getText().toString();
                String strAge = edt_age.getText().toString();
                String strContactNo = edt_contactNo.getText().toString();
                String strDateOfBirth = tv_dateOfBirth.getText().toString();


                Log.e("House Id",strHouseId );
                Log.e("MemberName ", strMemberName);
                Log.e("Age", strAge);
                Log.e("ContactNo", strContactNo);
                Log.e("DateOfBirth", strDateOfBirth);



                apiCall(strHouseId,strMemberName,strAge,strContactNo,strDateOfBirth );

            }
        });


        //date
        btn_memberDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MemberUpdate.this, new DatePickerDialog.OnDateSetListener() {
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

                        tv_dateOfBirth.setText(strDate);
                    }
                }, date, month, year);
                datePickerDialog.show();
            }
        });




    }



    private void apiCall(String strHouseId, String strMemberName, String strAge ,String strDateOfBirth , String strContactNo ) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, util.MEMBER_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.e("api calling done", response);
                Intent intent = new Intent(MemberUpdate.this, MemberShowActivity.class);
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
                hashMap.put("memberId", strMemberName);
                hashMap.put("age", strAge);
                hashMap.put("dateOfBirth", strDateOfBirth);
                hashMap.put("contact -No", strContactNo);

                return hashMap;


            }
        };
        VolleySingleton.getInstance(MemberUpdate.this).addToRequestQueue(stringRequest);

    }
}

