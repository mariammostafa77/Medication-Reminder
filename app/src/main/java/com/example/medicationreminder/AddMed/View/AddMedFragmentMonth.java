package com.example.medicationreminder.AddMed.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import com.example.medicationreminder.AddMed.Model.MedData;
import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Model.MedDataMonth;
import com.example.medicationreminder.AddMed.Presenter.AddMedPresenter;
import com.example.medicationreminder.AddMed.Presenter.PresenterInterface;
import com.example.medicationreminder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AddMedFragmentMonth extends Fragment implements MyInterfaceForMonth {


    RecyclerView recycleOfMonth;
    TextView tvNum;
    RecycleAdapterMedMonth MyAdapter;
    String userId;
    DatabaseReference mDatabase;
    FirebaseUser currentFirebaseUser ;
    public static String medId;
    MedDataMonth medDataMonth;
    List<MedDataMonth> medDataMonthsArray;
    Button btnNextMonth;

    PresenterInterface presenter=new AddMedPresenter();
    AddMedFragment3 addMedFragment3;
    String medName;
    String medUnit;
    String startDate;
    String endDate;
    int numOfMed;
    String timeUnitChoice;

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


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfMonth.setLayoutManager(linearLayoutManager);
        RecycleAdapterMedMonth MyAdapter =new RecycleAdapterMedMonth(getContext(),this,AddMedFragmentMonthArgs.fromBundle(getArguments()).getNumOfMed(),AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyMedUnit());
        recycleOfMonth.setAdapter(MyAdapter);
        medName=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyMedName().toString();
        medUnit=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyMedUnit().toString();
        startDate=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyStartDate().toString();
        endDate=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyEndDate().toString();
        numOfMed=AddMedFragmentMonthArgs.fromBundle(getArguments()).getNumOfMed();
        timeUnitChoice=AddMedFragmentMonthArgs.fromBundle(getArguments()).getTimeChoice().toString();
        medId=userId+medName +startDate;
        tvNum.setText(numOfMed+" times per "+timeUnitChoice);

        addMedFragment3=new AddMedFragment3(medDataMonthsArray,medName,
                medUnit,startDate,endDate,numOfMed ,timeUnitChoice,medId);

        if(medUnit=="pill"){
            btnNextMonth.setText("Next");
        }
        else{
            btnNextMonth.setText("Save");
        }

        btnNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(btnNextMonth);



                if(medUnit=="pill"){
                    NavDirections navDirections=AddMedFragmentMonthDirections.next();
                    navController.navigate(navDirections);

                }
                else{
                    String status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithOutRefillReminder(medName,medUnit,startDate,endDate,userId,medId,
                            numOfMed,timeUnitChoice, medDataMonthsArray));
                    Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
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



    @Override
    public void getData(MedDataMonth medDataMonth) {
        medDataMonthsArray.add(medDataMonth);
    }
}