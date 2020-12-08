package com.example.usersapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.usersapp.R;

public class UserDetailsActivity extends AppCompatActivity {
    String firstName, lastName, email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        Intent intent = getIntent();
        if(intent != null){
            firstName = intent.getStringExtra("firstName");
            lastName = intent.getStringExtra("lastName");
            email = intent.getStringExtra("email");
            phone = intent.getStringExtra("phone");

            setTitle(firstName + " " + lastName);
        }
        else{
            Toast.makeText(this, "No User Availbale", Toast.LENGTH_LONG).show();
            finish();
        }


    }





}