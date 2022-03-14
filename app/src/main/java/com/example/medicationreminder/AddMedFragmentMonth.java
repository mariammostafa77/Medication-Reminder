package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AddMedFragmentMonth extends Fragment implements MyInterfaceForMonth{


    RecyclerView recycleOfMonth;
    TextView tvNum;
    RecycleAdapterMedMonth MyAdapter;
    MedData medData;
    String userId;
    DatabaseReference mDatabase;
    FirebaseUser currentFirebaseUser ;
    public static String medId;
    MedDataMonth medDataMonth;
    List<MedDataMonth> medDataMonthsArray;
    Button btnNextMonth;

    public AddMedFragmentMonth() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med_month, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        userId= currentFirebaseUser.getUid();
        medDataMonthsArray=new ArrayList<>();
        recycleOfMonth=view.findViewById(R.id.recycleOfMonth);
        btnNextMonth=view.findViewById(R.id.btnNextMonth);
        tvNum=view.findViewById(R.id.tvNum);

        tvNum.setText(getArguments().getInt(AddMedFragment1.MedNumTag)+" times per "+
                getArguments().getString(AddMedFragment1.numberTakenTag));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfMonth.setLayoutManager(linearLayoutManager);
        RecycleAdapterMedMonth MyAdapter =new RecycleAdapterMedMonth(getContext(),this);
        recycleOfMonth.setAdapter(MyAdapter);


        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(btnNextMonth);

                String medId=userId+AddMedFragment1.medName+AddMedFragment1.startDate;
                medData=new MedData(AddMedFragment1.medName.toString(),
                        AddMedFragment1.medUnit.toString(),
                        AddMedFragment1.startDate.toString(),
                        AddMedFragment1.endDate.toString(),userId,medId,
                        AddMedFragment1.MedNum);
                addMed(medData);

                for (int i=0;i<medDataMonthsArray.size();i++){
                    medDataMonthsArray.get(i).setId(medId);
                    //Log.i("tag","list item "+medDataDay.getTime());
                    addMedTimesDates(medDataMonthsArray.get(i));
                }

                if(AddMedFragment1.medUnit=="pill"){
                    NavDirections navDirections=AddMedFragmentMonthDirections.next();
                    navController.navigate(navDirections);

                }
                else{
                    NavDirections navDirections=AddMedFragmentMonthDirections.next2();
                    navController.navigate(navDirections);

                }


            }
        });

        return view;
    }

    public void addMed(MedData medData){
        medId=null;
        mDatabase.child("MedicationData").push().setValue(medData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "added successfully ", Toast.LENGTH_SHORT).show();
                medId =medData.getMedId();

            }
        });
    }

    public void addMedTimesDates(MedDataMonth medDataMonth) {
        mDatabase.child("MedicationDataTimeDates").child("perMonth").push().setValue(medDataMonth).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "added successfully ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void getData(MedDataMonth medDataMonth) {
        medDataMonthsArray.add(medDataMonth);
    }
}