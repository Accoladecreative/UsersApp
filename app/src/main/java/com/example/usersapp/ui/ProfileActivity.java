package com.example.usersapp.ui;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.usersapp.R;
import com.example.usersapp.ui.profile.SignInFragment;

public class ProfileActivity extends AppCompatActivity {


    Button btnSignIn, BtnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        btnSignIn = findViewById(R.id.btn_show_sign_in);
        BtnSignUp = findViewById(R.id.btn_show_sign_up);

        btnSignIn.setOnClickListener(view -> {

//            Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_signInFragment);
            //btnSignIn.setTextSize(18);
            //BtnSignUp.setTextSize(14);
            SignInFragment signIn = new SignInFragment();
            FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.fragment3,signIn);
            fragmentManager.commit();
        });
        BtnSignUp.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_signUpFragment);
           // btnSignIn.setTextSize(14);
            //BtnSignUp.setTextSize(18);
           /* SignUp signUp = new SignUp();
            FragmentTransaction fragmentManager = getSupportFragmentManager().beginTransaction();
            fragmentManager.replace(R.id.fragment3,signUp);
            fragmentManager.commit();*/
        });
    }
}