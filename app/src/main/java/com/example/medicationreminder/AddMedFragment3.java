package com.example.medicationreminder;

import android.content.Intent;
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

public class AddMedFragment3 extends Fragment {

    TextView tvUnitReminder,tvUnitReminder2;

    public AddMedFragment3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med3, container, false);
        tvUnitReminder=view.findViewById(R.id.tvUnitReminder);
        tvUnitReminder2=view.findViewById(R.id.tvUnitReminder2);
        tvUnitReminder.setText(AddMedFragment1.medUnit.toString());
        tvUnitReminder2.setText(AddMedFragment1.medUnit.toString());
        Button btnSave=view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),HomeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}