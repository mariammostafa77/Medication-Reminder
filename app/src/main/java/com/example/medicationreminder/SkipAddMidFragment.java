package com.example.medicationreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicationreminder.AddMed.View.AddMedFragment1;


public class SkipAddMidFragment extends Fragment {



    public SkipAddMidFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_skip_add_mid, container, false);


        TextView txtSkip=v.findViewById(R.id.txtSkip);
        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);
                StartFragment.isGuest=true;
            }
        });
        Button addYourMedBtn=v.findViewById(R.id.addYourMedBtn);
        addYourMedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartFragment.isGuest=true;
                NavController navController= Navigation.findNavController(addYourMedBtn);
                NavDirections navDirections=SkipAddMidFragmentDirections.addMed();
                navController.navigate(navDirections);
            }
        });
        return v;
    }
}