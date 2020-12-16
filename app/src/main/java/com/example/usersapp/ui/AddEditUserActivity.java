package com.example.usersapp.ui;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usersapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEditUserActivity extends AppCompatActivity {
    //intents
    public static final String EXTRA_ID = "com.example.usersapp.ui.AddEditUserActivity.id";
    public static final String EXTRA_FIRSTNAME = "com.example.usersapp.ui.AddEditUserActivity.firstName";
    public static final String EXTRA_LASTNAME = "com.example.usersapp.ui.AddEditUserActivity.lastName";
    public static final String EXTRA_EMAIL = "com.example.usersapp.ui.AddEditUserActivity.email";
    public static final String EXTRA_PHONE = "com.example.usersapp.ui.AddEditUserActivity.phone";
    public static final String EXTRA_DOB = "com.example.usersapp.ui.AddEditUserActivity.dob";
    public static final String EXTRA_DATEADDED = "com.example.usersapp.ui.AddEditUserActivity.dateAdded";

    //ui
    private String firstName, lastName, email,phone, dob,dateAdded;
    private EditText mFirstName, mLastName, mEmail,mPhone, mDob;
    private Button mSubmit;
    private Calendar calendar;


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
        mDob = findViewById(R.id.edt_dob);
        //date added is generated automatically
        mSubmit = findViewById(R.id.btn_submit);

        calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DatePickerDialog.OnDateSetListener listener = (datePicker, year, month, dayOfTheMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfTheMonth);

            mDob.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));

        };
        mDob.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener,  calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                                                                calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });




        // getSupportActionBar().setHomeAsUpIndicator();
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {

            mSubmit.setText("Update User");
            mFirstName.setText(intent.getStringExtra(EXTRA_FIRSTNAME));
            mLastName.setText(intent.getStringExtra(EXTRA_LASTNAME));
            mEmail.setText(intent.getStringExtra(EXTRA_EMAIL));
            mPhone.setText(intent.getStringExtra(EXTRA_PHONE));
            mDob.setText(intent.getStringExtra(EXTRA_DOB));


            setTitle("Edit "+lastName+" Info");
        }else{ setTitle("Add new user");}



        //methods
        mSubmit.setOnClickListener(view -> addUser());
    }


    @SuppressLint("SimpleDateFormat")
    private void addUser() {
        //getting ui
        firstName = mFirstName.getText().toString();
        lastName = mLastName.getText().toString();
        email = mEmail.getText().toString();
        phone = mPhone.getText().toString();
        dob = mDob.getText().toString();
        //date added can neva be null



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
        else if(dob.isEmpty() ){
            mDob.setError("date of Birth is needed");
        }
         else {
             //get the day he registered
            Date myDate = Calendar.getInstance().getTime();
                    dateAdded = new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(myDate);
             Intent addUser = new Intent(this,UserDetailsActivity.class)
                 .putExtra(EXTRA_FIRSTNAME, firstName)
                .putExtra(EXTRA_LASTNAME, lastName)
                .putExtra(EXTRA_EMAIL, email)
                .putExtra(EXTRA_PHONE, phone)
                     .putExtra(EXTRA_DOB,dob)
                     .putExtra(EXTRA_DATEADDED,dateAdded)

                     ;

             int id = getIntent().getIntExtra(EXTRA_ID,-1);
             if(id != -1){
                 addUser.putExtra(EXTRA_ID,id);
             }
             setResult(RESULT_OK,addUser);
             finish();
        }


    }
}