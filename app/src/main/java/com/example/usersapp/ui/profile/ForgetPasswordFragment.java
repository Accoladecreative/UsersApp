package com.example.usersapp.ui.profile;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.usersapp.R;

/**
 **
 * A forget password fragmnet created by kolade Oluwadare
 * on 28 January, 2021
 */
public class ForgetPasswordFragment extends Fragment {
    TextView txtGoBack;
    LinearLayout receivecode;
    Button btnSendCode,btnSubmitCode;
    EditText EdtEmail,EdtSentCode;


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
        View view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        txtGoBack = view.findViewById(R.id.go_back);
        receivecode = view.findViewById(R.id.container_receive_code);
        btnSendCode = view.findViewById(R.id.btn_send_code);
        EdtEmail =view.findViewById(R.id.forget_email);
        EdtSentCode =view.findViewById(R.id.forget_code);
        btnSubmitCode=view.findViewById(R.id.btn_submit_code);


        //when email is submitted
        btnSendCode.setOnClickListener(view1 ->{
           if (EdtEmail.getText().toString().isEmpty()) {
                EdtEmail.setError("Email is Needed");
            } else if (!Patterns.EMAIL_ADDRESS.matcher(EdtEmail.getText().toString()).matches()) {
                EdtEmail.setError("Invalid Email");
            } else {
            receivecode.setVisibility(View.VISIBLE);
        }});


        //when user submit the code
        btnSubmitCode.setOnClickListener(view1 -> {
            if(EdtSentCode.getText().toString().length() !=5){
                EdtSentCode.setError("COde Error");
            }
        });




        //when user go back to sign in
        txtGoBack.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_forgetPasswordFragment_to_signInFragment));
        return view;
    }
}