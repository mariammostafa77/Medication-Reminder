package com.example.medicationreminder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AddMedFragment2 extends Fragment {

    TextView tvNum;
    RecyclerView recycleOfDay;

    public AddMedFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med2, container, false);
        tvNum=view.findViewById(R.id.tvNum);
        recycleOfDay=view.findViewById(R.id.recycleOfDay);

        tvNum.setText(getArguments().getInt(AddMedFragment1.MedNumTag)+" times per "+
                getArguments().getString(AddMedFragment1.numberTakenTag));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfDay.setLayoutManager(linearLayoutManager);
        RecycleAdapterMedDays MyAdapter =new RecycleAdapterMedDays(getContext());
        recycleOfDay.setAdapter(MyAdapter);

        Button btnNext2=view.findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(btnNext2);
                NavDirections navDirections=AddMedFragment2Directions.next2();
                navController.navigate(navDirections);
            }
        });

        return view;
    }
}