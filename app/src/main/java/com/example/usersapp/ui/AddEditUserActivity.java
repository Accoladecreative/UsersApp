package com.example.usersapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.usersapp.R;

public class AddEditUserActivity extends AppCompatActivity {
    //intents
    public static final String EXTRA_ID = "com.example.usersapp.ui.AddEditUserActivity.id";
    public static final String EXTRA_FIRSTNAME = "com.example.usersapp.ui.AddEditUserActivity.firstName";
    public static final String EXTRA_LASTNAME = "com.example.usersapp.ui.AddEditUserActivity.lastName";
    public static final String EXTRA_EMAIL = "com.example.usersapp.ui.AddEditUserActivity.email";
    public static final String EXTRA_PHONE = "com.example.usersapp.ui.AddEditUserActivity.phone";

    //ui
    private String firstName, lastName, email,phone;
    private EditText mFirstName, mLastName, mEmail,mPhone;
    private Button mSubmit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        //ui
        mFirstName = findViewById(R.id.edt_first_name);
        mLastName = findViewById(R.id.edt_last_name);
        mEmail = findViewById(R.id.edt_email);
        mPhone = findViewById(R.id.edt_phone);
        mSubmit = findViewById(R.id.btn_submit);


        // getSupportActionBar().setHomeAsUpIndicator();
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {

            mSubmit.setText("Update User");
            mFirstName.setText(intent.getStringExtra(EXTRA_FIRSTNAME));
            mLastName.setText(intent.getStringExtra(EXTRA_LASTNAME));
            mEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
            mPhone.setText(intent.getStringExtra(EXTRA_PHONE));
            setTitle("Edit "+lastName+" Info");
        }else{ setTitle("Add new user");}



        //methods
        mSubmit.setOnClickListener(view -> addUser());
    }

    private void addUser() {
        //getting ui
        firstName = mFirstName.getText().toString();
        lastName = mLastName.getText().toString();
        email = mEmail.getText().toString();
        phone = mPhone.getText().toString();


        if(firstName.isEmpty()){
            mFirstName.setError("FirstName is Needed");
        }
        else if(lastName.isEmpty() ){
            mLastName.setError("LastName is Needed");
        }
        else if(email.isEmpty() ){
            mLastName.setError("Email is Needed");
        }
         else if(phone.isEmpty() ){
             mPhone.setError("Phone number is needed");
        }
         else {
             Intent addUser = new Intent(this,UserDetailsActivity.class)
             .putExtra(EXTRA_FIRSTNAME, firstName)
            .putExtra(EXTRA_LASTNAME, lastName)
            .putExtra(EXTRA_EMAIL, email)
            .putExtra(EXTRA_PHONE, phone);

             int id = getIntent().getIntExtra(EXTRA_ID,-1);
             if(id != -1){
                 addUser.putExtra(EXTRA_ID,id);
             }
             setResult(RESULT_OK,addUser);
             finish();
        }


    }
}