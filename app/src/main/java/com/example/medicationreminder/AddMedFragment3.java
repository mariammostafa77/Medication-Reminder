package com.example.medicationreminder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMedFragment3 extends Fragment {

    TextView tvUnitReminder,tvUnitReminder2;
    EditText edtLeftNum,edtNumRefill;
    Button btnSave;
    String time;
    TimePicker timePicker1;
    DatabaseReference mDatabase;
    RefillMed refillMed;

    public AddMedFragment3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med3, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        refillMed=new RefillMed();

        edtLeftNum=view.findViewById(R.id.edtLeftNum);
        edtNumRefill=view.findViewById(R.id.edtNumRefill);
        timePicker1=view.findViewById(R.id.timePicker1);
        tvUnitReminder=view.findViewById(R.id.tvUnitReminder);
        tvUnitReminder2=view.findViewById(R.id.tvUnitReminder2);
        btnSave=view.findViewById(R.id.btnSave);
        tvUnitReminder.setText(AddMedFragment1.medUnit.toString());
        tvUnitReminder2.setText(AddMedFragment1.medUnit.toString());

        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = String.valueOf(hourOfDay).toString() + ":" + String.valueOf(minute).toString();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refillMed.setMedId(AddMedFragment2.medId);
                refillMed.setUserId(AddMedFragment2.id);
                refillMed.setNumOfRemind(Integer.parseInt(edtNumRefill.getText().toString()));
                refillMed.setPillLeftMun(Integer.parseInt(edtLeftNum.getText().toString()));
                refillMed.setRemindTime(time);
                mDatabase.child("MedicationRefillData").push().setValue(refillMed).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "added successfully ", Toast.LENGTH_SHORT).show();
                        NavController navController= Navigation.findNavController(v);
                        NavDirections navDirections=AddMedFragment3Directions.next();
                        navController.navigate(navDirections);
                    }
                });

            }
        });
        return view;
    }
}