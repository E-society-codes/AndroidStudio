package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.QuickContactBadge;

import java.util.Calendar;

public class memberLayout extends AppCompatActivity {
    EditText edtHouseId,edtMemberName,edtAge,edtGender,edtContactNo,edtDateOfBirth;
    Button addMember;

    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_layout);

        edtDateOfBirth = findViewById(R.id.edt_dob);
        edtHouseId = findViewById(R.id.edt_houseId);
        edtMemberName = findViewById(R.id.edt_memberName);
        edtAge = findViewById(R.id.edt_age);
        edtGender = findViewById(R.id.edt_gender);
        edtContactNo = findViewById(R.id.edt_contactNo);
        addMember = findViewById(R.id.btn_member);

        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hi = edtHouseId.getText().toString();
                String mn = edtMemberName.getText().toString();
                String ag = edtAge.getText().toString();
                String gn = edtGender.getText().toString();
                String cn = edtContactNo.getText().toString();
                String dob = edtDateOfBirth.getText().toString();


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


        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        memberLayout.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edtDateOfBirth.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();

            }
        });
    }


}
