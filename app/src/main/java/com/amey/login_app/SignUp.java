package com.amey.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    TextInputLayout name, username, email, phoneNo, password;
    Button signUp, login;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phoneNo = findViewById(R.id.phoneNo);
        password = findViewById(R.id.password);
        signUp = findViewById(R.id.signUp);
        login = findViewById(R.id.login);

       /* signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");
            }
        });*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Login.class));
                finish();
            }
        });
    }

    private Boolean validateName() {
        String val = name.getEditText().getText().toString();
        if (val.isEmpty()) {
            name.setError("Field cannot be empty...");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            username.setError("Field cannot be empty...");
            return false;
        } else if (val.length() >= 15) {
            username.setError("Username too long...");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            username.setError("White Space are not allowed...");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            email.setError("Field cannot be empty...");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid Email Address...");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = phoneNo.getEditText().getText().toString();
        if (val.isEmpty()) {
            phoneNo.setError("Field cannot be empty...");
            return false;
        } else {
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    //This function will execute when user click on Register Button

    public void registerUser(View view) {
        if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()) {
            return;
        }
            String regname = name.getEditText().getText().toString();
            String regusername = username.getEditText().getText().toString();
            String regemail = email.getEditText().getText().toString();
            String regphoneNo = phoneNo.getEditText().getText().toString();
            String regpassword = password.getEditText().getText().toString();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
            UserHelperClass helperClass = new UserHelperClass(regname, regusername, regemail, regphoneNo, regpassword);
            reference.child(regphoneNo).setValue(helperClass);

            startActivity(new Intent(SignUp.this,UserProfile.class));
            finish();
    }
}
