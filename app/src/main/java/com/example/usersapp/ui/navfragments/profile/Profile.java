package com.example.usersapp.ui.navfragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.usersapp.R;

public class Profile extends Fragment {
    private TextView textView;
    private TextView textView2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        textView = view.findViewById(R.id.profile_txt);
        textView2 = view.findViewById(R.id.profile_txt2);


        textView.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_profile_to_explore);
        });
        textView2.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_profile_to_notification);
        });

        return view;
    }
}
