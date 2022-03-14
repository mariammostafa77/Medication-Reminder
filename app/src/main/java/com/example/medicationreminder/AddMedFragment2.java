package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;


public class AddMedFragment2 extends Fragment implements MyInterfaceForDays {

    TextView tvNum;
    RecyclerView recycleOfDay;
    RecycleAdapterMedDays MyAdapter;
    MedData medData;
    public static String id;
    DatabaseReference mDatabase;
    FirebaseUser currentFirebaseUser ;
    public static String medId;
    MedDataDay medDataDay;
    List<MedDataDay> medDataDayArray;


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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        tvNum.setText(getArguments().getInt(AddMedFragment1.MedNumTag)+" times per "+
                getArguments().getString(AddMedFragment1.numberTakenTag));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfDay.setLayoutManager(linearLayoutManager);
        medDataDayArray=new ArrayList<>();
        MyAdapter =new RecycleAdapterMedDays(getContext(), this);
        recycleOfDay.setAdapter(MyAdapter);
        id= currentFirebaseUser.getUid();
        Button btnNext2=view.findViewById(R.id.btnNext2);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);

                String medId=id+AddMedFragment1.medName+AddMedFragment1.startDate;
                medData=new MedData(AddMedFragment1.medName.toString(),
                        AddMedFragment1.medUnit.toString(),
                        AddMedFragment1.startDate.toString(),
                        AddMedFragment1.endDate.toString(),id,medId,
                        AddMedFragment1.MedNum);
                addMed(medData);

                medDataDay=new MedDataDay();
                for (int i=0;i<medDataDayArray.size();i++){
                    medDataDayArray.get(i).setId(medId);
                    //Log.i("tag","list item "+medDataDay.getTime());
                   addMedTimesDates(medDataDayArray.get(i));
                }
                if(AddMedFragment1.medUnit=="pill"){
                    NavDirections navDirections=AddMedFragment2Directions.next2();
                    navController.navigate(navDirections);

                }
                else{
                    NavDirections navDirections=AddMedFragment2Directions.next();
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

    public void addMedTimesDates(MedDataDay medDataDay) {
        mDatabase.child("MedicationDataTimeDates").child("perDays").push().setValue(medDataDay).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getContext(), "added successfully ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void getData(MedDataDay medDataDay) {
        medDataDayArray.add(medDataDay);
    }
}