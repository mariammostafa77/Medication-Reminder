package com.example.medicationreminder;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class StartFragment extends Fragment {
    public static boolean isGuest=false;
    NavController navController;
    NavDirections navDirections;



    Button btnGetStart;
    TextView txtLogin,txtRegister;
    public StartFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_start, container, false);
        txtLogin=view.findViewById(R.id.txtLogin);
        txtRegister=view.findViewById(R.id.txtRegister);
        btnGetStart=view.findViewById(R.id.btnGetStart);
        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSkipAddMedFragement(v);
            }
        });
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterationFragement(v);
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginFragement(v);
            }
        });

        return view;
    }

    private void goToLoginFragement(View v) {

         if(getActivity() !=null){
              navController= Navigation.findNavController(v);
              navDirections=StartFragmentDirections.startLoginDect();
             navController.navigate(navDirections);
         }




    }

    private void goToRegisterationFragement(View v) {
        if(getActivity() !=null){
             navController= Navigation.findNavController(v);
             navDirections=StartFragmentDirections.startRegisterDect();
            navController.navigate(navDirections);
        }
    }

    private void goToSkipAddMedFragement(View v) {
        if(getActivity() !=null){
             navController= Navigation.findNavController(v);
             navDirections=StartFragmentDirections.startSkipDect();
            navController.navigate(navDirections);
        }
    }
}