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
import com.example.medicationreminder.AddMed.Model.MedDataWeek;
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


public class AddMedFragment2 extends Fragment implements MyInterfaceForDays,MyInterfaceForMonth,MyInterfaceForWeek {

    TextView tvNum;
    RecyclerView recycleOfDay;
    RecycleAdapterMedDays MyAdapter;
    String id;
    DatabaseReference mDatabase;
    FirebaseUser currentFirebaseUser ;
    static List<MedDataDay> medDataDayArray;
    static List<MedDataMonth>medDataMonthList;
    static List<MedDataWeek>medDataWeekList;
    String medId;
    PresenterInterface presenter=new AddMedPresenter();
    AddMedFragment3 addMedFragment3;
    String medName;
    String medUnit;
    String startDate;
    String endDate;
    int numOfMed;
    String timeUnitChoice;
    String doseId;

    public AddMedFragment2() {
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

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfDay.setLayoutManager(linearLayoutManager);
        medDataDayArray=new ArrayList<>();
        MyAdapter =new RecycleAdapterMedDays(getContext(), this,AddMedFragment2Args.fromBundle(getArguments()).getNumOfMed(),AddMedFragment2Args.fromBundle(getArguments()).getMyMedUnit());
        recycleOfDay.setAdapter(MyAdapter);
        id= currentFirebaseUser.getUid();
        Button btnNext2=view.findViewById(R.id.btnNext2);
        medName=AddMedFragment2Args.fromBundle(getArguments()).getMyMedName().toString();
        medUnit=AddMedFragment2Args.fromBundle(getArguments()).getMyMedUnit().toString();
        startDate=AddMedFragment2Args.fromBundle(getArguments()).getMyStartDate().toString();
        endDate=AddMedFragment2Args.fromBundle(getArguments()).getMyEndDate().toString();
        numOfMed=AddMedFragment2Args.fromBundle(getArguments()).getNumOfMed();
        timeUnitChoice=AddMedFragment2Args.fromBundle(getArguments()).getTimeChoice().toString();
        medId=id+medName +startDate;
        tvNum.setText(numOfMed+" times per "+timeUnitChoice);
        addMedFragment3=new AddMedFragment3(medName,medDataDayArray,
                medUnit,startDate,endDate,numOfMed ,timeUnitChoice,medId);

        if(medUnit=="pill"){
            btnNext2.setText("Next");
        }
        else{
            btnNext2.setText("Save");
        }

        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);
                for (int i=0;i<medDataDayArray.size();i++){
                    medDataDayArray.get(i).setDoseId(medId+medDataDayArray.get(i).getTime()+medDataDayArray.get(i).getDose());
                }
                if(medUnit=="pill"){
                    NavDirections navDirections=AddMedFragment2Directions.next2();
                    navController.navigate(navDirections);
                }
                else{
                    String status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithOutRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataDayArray));
                    Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
                    NavDirections navDirections=AddMedFragment2Directions.next();
                    navController.navigate(navDirections);
                }


            }
        });

        return view;
    }
    @Override
    public void getData(MedDataDay medDataDay) {
        medDataDayArray.add(medDataDay);
    }

    @Override
    public void getData(MedDataMonth medDataMonth) {
        medDataMonthList.add(medDataMonth);
    }

    @Override
    public void getData(MedDataWeek medDataWeek) {
        medDataWeekList.add(medDataWeek);
    }
}