package com.amey.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullname,email,phoneNo;
    TextView fullNameLabel,usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullname=findViewById(R.id.fullname);
        email=findViewById(R.id.email);
        phoneNo=findViewById(R.id.mno);
        fullNameLabel=findViewById(R.id.full_name);
        usernameLabel=findViewById(R.id.username_field);

        showAllUserData();
    }

    private void showAllUserData() {

        Intent intent=getIntent();
        String user_username=intent.getStringExtra("username");
        String user_name=intent.getStringExtra("regname");
        String user_email=intent.getStringExtra("regemail");
        String user_phoneNo=intent.getStringExtra("regphoneNo");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        fullname.getEditText().setText(user_name);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneNo);


    }
}
