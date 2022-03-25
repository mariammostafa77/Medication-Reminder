package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class Dialog extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomDialogFragment customDialogFragment = new CustomDialogFragment("","work", "giza", "mansoura");
        customDialogFragment.show(getSupportFragmentManager(), "CUSTOM");


    }
}
