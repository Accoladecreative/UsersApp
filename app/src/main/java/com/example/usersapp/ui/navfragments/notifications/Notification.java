 package com.example.usersapp.ui.navfragments.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.usersapp.R;

public class Notification extends Fragment {

    private NotificationViewModel mViewModel;

    public static Notification newInstance() {
        return new Notification();
    }
    private TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.notification_fragment, container, false);
       textView = view.findViewById(R.id.notification_txt);

       textView.setOnClickListener(view1 -> {
           Navigation.findNavController(view).navigate(R.id.action_notification_to_profile);
       });
       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
        // TODO: Use the ViewModel


    }

}