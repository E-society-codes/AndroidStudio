package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class societyLayout extends AppCompatActivity {
    EditText edtSocietyName,edtAddress,edtPincode,edtSocietyCity,edtSocietyNoOfHouse;
    Button btnAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_layout);

        edtSocietyName = findViewById(R.id.edt_societyName);
        edtAddress = findViewById(R.id.edt_address);
        edtPincode = findViewById(R.id.edt_pincode);
        edtSocietyName = findViewById(R.id.edt_societyName);
        edtSocietyCity = findViewById(R.id.edt_city);
        edtSocietyNoOfHouse = findViewById(R.id.edt_noOfHouse);
        btnAddButton = findViewById(R.id.btn_addSociety);

        btnAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sn = edtSocietyName.getText().toString();
                String add = edtAddress.getText().toString();
                String pn = edtPincode.getText().toString();
                String sc = edtSocietyCity.getText().toString();
                String snh = edtSocietyNoOfHouse.getText().toString();



                Intent intent = new Intent(societyLayout.this,SocietyShowActivity.class);

                intent.putExtra("societyName",sn);
                intent.putExtra("address",add);
                intent.putExtra("pincode",pn);
                intent.putExtra("city",sc);
                intent.putExtra("noOfHouse",snh);



                startActivity(intent);

            }
        });



    }
}