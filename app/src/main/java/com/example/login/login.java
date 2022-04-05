package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    Button signupbutton, loginbtn;

    TextInputLayout username_var,password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.login);

        signupbutton = findViewById(R.id.signup);
        loginbtn = findViewById(R.id.loginbutton);

        username_var = findViewById(R.id.username_text_design);
        password_var = findViewById(R.id.password_input_field);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(),profile.class);
                startActivity(intent);

                String username_ = username_var.getEditText().getText().toString();
                String password_ = password_var.getEditText().getText().toString();

                if (!username_.isEmpty()){
                    username_var.setError(null);
                    username_var.setErrorEnabled(false);
                    if (!password_.isEmpty()){
                        password_var.setError(null);
                        password_var.setErrorEnabled(false);

                        final  String username_data = username_var.getEditText().getText().toString();
                        final  String password_data = password_var.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("datauser");

                        Query check_username = databaseReference.orderByChild("username").equalTo(username_data);


                       check_username.addListenerForSingleValueEvent(new ValueEventListener() {
                           @Override
                           public void onDataChange( @NonNull DataSnapshot snapshot) {

                               if (snapshot.exists()){
                                   username_var.setError(null);
                                   username_var.setErrorEnabled(false);
                                   String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);
                                   if (passwordcheck.equals(password_data)){
                                       password_var.setError(null);
                                       password_var.setErrorEnabled(false);
                                       Toast.makeText(getApplicationContext(),"login succesfully",Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                       startActivity(intent);
                                       finish();
                                   }else{
                                       password_var.setError("wrong password");
                                   }
                               }else {
                                   username_var.setError("user does not exist ");
                               }


                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });

                    }else {
                        password_var.setError("please enter the password");
                    }
                }else {
                    username_var.setError("please enter the username");
                }

            }

         });


        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });



    }
}