package com.example.medicationreminder.AddMed.View;

import android.app.Activity;
import android.content.Intent;
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

import com.example.medicationreminder.AddMed.Model.MedData;
import com.example.medicationreminder.AddMed.Model.MedDataWeek;
import com.example.medicationreminder.AddMed.Presenter.AddMedPresenter;
import com.example.medicationreminder.AddMed.Presenter.PresenterInterface;
import com.example.medicationreminder.HomeActivity;
import com.example.medicationreminder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMedWeekFragment extends Fragment implements MyInterfaceForWeek {


    RecyclerView recycleOfWeek;
    TextView tvNumTaken;
    RecycleAdapterMedWeeks MyAdapter;
    String userId;
    DatabaseReference mDatabase;
    FirebaseUser currentFirebaseUser;
    public static String medId;
    MedDataWeek medDataWeek;
    List<MedDataWeek> medDataWeekArray;
    Button btnNext3;

    PresenterInterface presenter=new AddMedPresenter();
    AddMedFragment3 addMedFragment3;
   public static String medName;
    public static String medUnit;
   public static String startDate;
    public static String endDate;
    int numOfMed;
    String timeUnitChoice;

    public AddMedWeekFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_med_week, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        btnNext3=view.findViewById(R.id.btnNext3);
        tvNumTaken=view.findViewById(R.id.tvNumTaken);
        recycleOfWeek=view.findViewById(R.id.recycleOfWeek);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleOfWeek.setLayoutManager(linearLayoutManager);
        RecycleAdapterMedWeeks MyAdapter =new RecycleAdapterMedWeeks(getContext(),this,AddMedWeekFragmentArgs.fromBundle(getArguments()).getNumOfMed(),AddMedWeekFragmentArgs.fromBundle(getArguments()).getMyMedUnit());
        recycleOfWeek.setAdapter(MyAdapter);
        medDataWeekArray=new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        userId= currentFirebaseUser.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;


        medName=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyMedName().toString();
        medUnit=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyMedUnit().toString();
        startDate=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyStartDate().toString();
        endDate=AddMedFragmentMonthArgs.fromBundle(getArguments()).getMyEndDate().toString();
        numOfMed=AddMedFragmentMonthArgs.fromBundle(getArguments()).getNumOfMed();
        timeUnitChoice=AddMedFragmentMonthArgs.fromBundle(getArguments()).getTimeChoice().toString();
        Date currentTime = Calendar.getInstance().getTime();
        medId=userId+medName +currentTime;
        tvNumTaken.setText(numOfMed+" times per "+timeUnitChoice);


        addMedFragment3=new AddMedFragment3(medName,
                medUnit,startDate,endDate,numOfMed ,timeUnitChoice,medId,medDataWeekArray);

        if(medUnit=="pill"){
            btnNext3.setText("Next");
        }
        else{
            btnNext3.setText("Save");
        }

        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(btnNext3);
                for (int i=0;i<medDataWeekArray.size();i++){
                    medDataWeekArray.get(i).setDoseId(medId+medDataWeekArray.get(i).getDayOfWeek()+medDataWeekArray.get(i).getTime()+medDataWeekArray.get(i).getDose());
                }
                if(medUnit=="pill"){
                    Bundle bundle = new Bundle();
                    bundle.putString("userId",userId);
                    navController.navigate(R.id.next3,bundle);

                }
                else{
                    String status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithOutRefillReminder(medName,medUnit,startDate,endDate,userId,medId,
                            numOfMed,timeUnitChoice, medDataWeekArray));
                    Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();
                    //NavDirections navDirections=AddMedWeekFragmentDirections.next();
                    //navController.navigate(navDirections);
                    Intent i = new Intent(getActivity(), HomeActivity.class);
                    startActivity(i);
                    ((Activity) getActivity()).overridePendingTransition(0, 0);

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
    public void getData(MedDataWeek medDataWeek) {
        medDataWeekArray.add(medDataWeek);
    }
}