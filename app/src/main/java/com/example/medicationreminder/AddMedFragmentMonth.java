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


public class AddMedFragmentMonth extends Fragment {


    RecyclerView recycleOfMonth;

    public AddMedFragmentMonth() {
        // Required empty public constructor
    }

    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med_month, container, false);
        recycleOfMonth=view.findViewById(R.id.recycleOfMonth);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfMonth.setLayoutManager(linearLayoutManager);
        RecycleAdapterMedMonth MyAdapter =new RecycleAdapterMedMonth(getContext());
        recycleOfMonth.setAdapter(MyAdapter);

        Button btnNextMonth=view.findViewById(R.id.btnNextMonth);
        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(btnNextMonth);
                NavDirections navDirections=AddMedFragmentMonthDirections.next();
                navController.navigate(navDirections);
            }
        });

        return view;
    }
}