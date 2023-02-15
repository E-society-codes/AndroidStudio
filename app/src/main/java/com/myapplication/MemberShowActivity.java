package com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemberShowActivity extends AppCompatActivity {
    ListView memberList;



    FloatingActionButton btnMemberAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_show);
    }
}

//2.lang model[node varaibles and _id]
//3.list adapter
//4.display ma code
//5.update/delete