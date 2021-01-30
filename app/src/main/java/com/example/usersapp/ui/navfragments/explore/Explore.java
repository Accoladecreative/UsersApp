package com.example.usersapp.ui.navfragments.explore;

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

public class Explore extends Fragment {
    private TextView textView;

    private ExploreViewModel mViewModel;

    public static Explore newInstance() {
        return new Explore();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, container, false);
        textView = view.findViewById(R.id.notification_txt);

        textView.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_explore_to_profile);
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
        // TODO: Use the ViewModel
    }

}