package com.example.usersapp.ui.profile;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.usersapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A sign up fragment created by kolade Oluwadare
 * on 28 January, 2021
 */
public class SignUpFragment extends Fragment {
    private String firstName, lastName, email, phone, dob, dateAdded, pass,cPass;
    private EditText mFirstName, mLastName, mEmail, mPhone, mDob,mPass, mCPass;
    private Button mSubmit;
    private Calendar calendar;
    private TextView txtgotosignin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);



        mFirstName = view.findViewById(R.id.edt_first_name);
        mLastName = view.findViewById(R.id.edt_last_name);
        mEmail = view.findViewById(R.id.edt_email);
        mPhone = view.findViewById(R.id.edt_phone);
        mDob = view.findViewById(R.id.edt_dob);
        //date added is generated automatically
        mSubmit = view.findViewById(R.id.btn_submit);

        calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DatePickerDialog.OnDateSetListener listener = (datePicker, year, month, dayOfTheMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfTheMonth);

            mDob.setText(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));

        };
        mDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });




        txtgotosignin = view.findViewById(R.id.gotosignin);
        txtgotosignin.setOnClickListener(view1 ->             Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment));
        return view;
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


        if (firstName.isEmpty()) {
            mFirstName.setError("FirstName is Needed");
        } else if (lastName.isEmpty()) {
            mLastName.setError("LastName is Needed");
        } else if (email.isEmpty()) {
            mEmail.setError("Email is Needed");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Invalid Email");
        } else if (phone.isEmpty()) {
            mPhone.setError("Phone number is needed");
        } else if(phone.toString().length()<10){
            mPhone.setError("Invalid Phone number");
        } else if (pass.isEmpty()) {
            mPass.setError("Enter Password");
        } else if (cPass.isEmpty()) {
            mCPass.setError("date of Birth is needed");
        } else if (pass.length()< 8) {
            mDob.setError("Password too Short");
        } else if (cPass == pass) {
            mCPass.setError("Password Mismatch!!");
        } else {
            //get the day he registered
            Date myDate = Calendar.getInstance().getTime();
            dateAdded = new SimpleDateFormat("dd-MM-yyyy : HH:mm").format(myDate);

            //todo: send data to where neceesary
            /*Intent addUser = new Intent(this, UserDetailsActivity.class)
                    .putExtra(EXTRA_FIRSTNAME, firstName)
                    .putExtra(EXTRA_LASTNAME, lastName)
                    .putExtra(EXTRA_EMAIL, email)
                    .putExtra(EXTRA_PHONE, phone)
                    .putExtra(EXTRA_DOB, dob)
                    .putExtra(EXTRA_DATEADDED, dateAdded);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id != -1) {
                addUser.putExtra(EXTRA_ID, id);
            }
            setResult(RESULT_OK, addUser);
            finish();*/
        }


    }
}