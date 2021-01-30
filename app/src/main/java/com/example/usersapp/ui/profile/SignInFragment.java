package com.example.usersapp.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.usersapp.R;

/****
 * A sign in fragmnet created by kolade Oluwadare
 * on 28 January, 2021
 */

public class SignInFragment extends Fragment {
    TextView forgetPassword;


    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment SignInFragment.
     */



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        if (getArguments() != null) {
        }
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
    forgetPassword = view.findViewById(R.id.txt_forget_pass);
    forgetPassword.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_signInFragment_to_forgetPasswordFragment));
        return view;
    }
}