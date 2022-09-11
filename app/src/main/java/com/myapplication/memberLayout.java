package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Calendar;

public class memberLayout extends AppCompatActivity {
    EditText edtHouseId,edtMemberName,edtAge,edtGender,edtContactNo;
    TextView tvDateOfBirth,tvGender;
    RadioButton radioMale,radioFemale;
    ImageButton btnDate;
    Button addMember;


    private int date;
    private int month;
    private int year;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_layout);

        edtHouseId = findViewById(R.id.edt_houseId);
        edtMemberName = findViewById(R.id.edt_memberName);
        edtAge = findViewById(R.id.edt_age);

        tvDateOfBirth = findViewById(R.id.tv_dateOfBirth);
        tvGender = findViewById(R.id.tv_gender);
        edtContactNo = findViewById(R.id.edt_contactNo);
        radioMale = findViewById(R.id.radio_male);
        radioFemale = findViewById(R.id.radio_female);
        addMember = findViewById(R.id.btn_member);
        btnDate = findViewById(R.id.btn_Mdate);



        Calendar calendar = Calendar.getInstance();
        date=calendar.get(Calendar.DAY_OF_MONTH);
        month=calendar.get(Calendar.MONTH);
        year=calendar.get(Calendar.YEAR);


        //date
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(memberLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvDateOfBirth.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                    }
                },date,month,year);
                datePickerDialog.show();

            }
        });

        //add button
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hi = edtHouseId.getText().toString();
                String mn = edtMemberName.getText().toString();
                String ag = edtAge.getText().toString();
                String gn = edtGender.getText().toString();
                String cn = edtContactNo.getText().toString();
                String dob = tvDateOfBirth.getText().toString();


                Intent intent = new Intent(memberLayout.this,MemberShowActivity.class);

                intent.putExtra("houseId",hi);
                intent.putExtra("memberName",mn);
                intent.putExtra("age",ag);
                intent.putExtra("gender",gn);
                intent.putExtra("contactNo.",cn);
                intent.putExtra("dateOfBirth",dob);


                startActivity(intent);
            }
        });



    }


}
