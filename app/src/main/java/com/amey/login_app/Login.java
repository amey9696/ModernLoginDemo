package com.amey.login_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
Button signup,login,forgot;
ImageView image;
TextView logoText,sloganText;
TextInputLayout username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        signup=findViewById(R.id.signup);
        image=findViewById(R.id.imageView);
        logoText=findViewById(R.id.logo_name);
        sloganText=findViewById(R.id.slogan_name);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        forgot=findViewById(R.id.forgot);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,SignUp.class);

               /* Pair[] pairs=new Pair[7];

                pairs[0]=new Pair<View,String>(image,"logo_image");
                pairs[1]=new Pair<View,String>(logoText,"logo_text");
                pairs[2]=new Pair<View,String>(sloganText,"logo_desc");
                pairs[3]=new Pair<View,String>(username,"username_tran");
                pairs[4]=new Pair<View,String>(password,"password_tran");
                pairs[5]=new Pair<View,String>(login,"button_tran");
                pairs[6]=new Pair<View,String>(signup,"login_signup_tran");
//                pairs[7]=new Pair<View,String>(forgot,"forgot_tran");

                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());*/
               startActivity(intent);
                finish();
            }
        });
    }

    private Boolean validateUsername(){
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty...");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        if (!validateUsername() | !validatePassword()){
            return;
        }
        else {
            isUser();
        }
    }

    private void isUser() {

        final String userEnteredUsername=username.getEditText().getText().toString().trim();
        final String userEnteredPassword=password.getEditText().getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");

        Query checkUser=reference.orderByChild("regusername").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String regpasswordFromDB=dataSnapshot.child(userEnteredUsername).child("regpassword").getValue(String.class);

                    if (regpasswordFromDB.equals(userEnteredPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String regnameFromDB=dataSnapshot.child(userEnteredUsername).child("regname").getValue(String.class);
                        String regusernameFromDB=dataSnapshot.child(userEnteredUsername).child("regusername").getValue(String.class);
                        String regphoneNoFromDB=dataSnapshot.child(userEnteredUsername).child("regphoneNo").getValue(String.class);
                        String regemailFromDB=dataSnapshot.child(userEnteredUsername).child("regemail").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),UserProfile.class);

                        intent.putExtra("regname",regnameFromDB);
                        intent.putExtra("regusername",regusernameFromDB);
                        intent.putExtra("regemail",regemailFromDB);
                        intent.putExtra("regphoneNo",regphoneNoFromDB);
                        intent.putExtra("regpassword",regpasswordFromDB);

                        startActivity(intent);
                    }
                    else {
                        password.setError("wrong password");
                        password.requestFocus();
                    }
                }
                else {
                    username.setError("user does not exist...");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

