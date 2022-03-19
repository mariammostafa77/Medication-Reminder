package com.example.medicationreminder.AddMed.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Model.MedDataMonth;
import com.example.medicationreminder.AddMed.Model.MedDataWeek;
import com.example.medicationreminder.AddMed.Presenter.AddMedPresenter;
import com.example.medicationreminder.AddMed.Presenter.PresenterInterface;
import com.example.medicationreminder.HomeActivity;
import com.example.medicationreminder.R;
import com.example.medicationreminder.AddMed.Model.RefillMed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddMedFragment3 extends Fragment {

    TextView tvUnitReminder,tvUnitReminder2,tvSkip;
    EditText edtLeftNum,edtNumRefill;
    Button btnSave;
    String time;
    TimePicker timePicker1;
    DatabaseReference mDatabase;
    public static String id;
    FirebaseUser currentFirebaseUser ;
    PresenterInterface presenter=new AddMedPresenter();
    static List<MedDataDay> medDataDayArray;
    static List<MedDataMonth> medDataMonthList;
    static List<MedDataWeek> medDataWeekList;
    static String medName;
    static String medUnit;
    static String startDate;
    static String endDate;
    static int numOfMed;
    static String timeUnitChoice;
    static String medId;

    public AddMedFragment3() {
    }
    public AddMedFragment3(String medName, List<MedDataDay> medDataDayArray,String medUnit, String startDate,
            String endDate, int numOfMed, String timeUnitChoice, String medId) {
        this.medDataDayArray=medDataDayArray;
        this.medName=medName;
        this.medUnit=medUnit;
        this.startDate=startDate;
        this.endDate=endDate;
        this.numOfMed=numOfMed;
        this.timeUnitChoice=timeUnitChoice;
        this.medId=medId;
    }
    public AddMedFragment3(List<MedDataMonth> medDataDayArray, String medName, String medUnit, String startDate,
                           String endDate, int numOfMed, String timeUnitChoice, String medId) {
        this.medDataMonthList=medDataDayArray;
        this.medName=medName;
        this.medUnit=medUnit;
        this.startDate=startDate;
        this.endDate=endDate;
        this.numOfMed=numOfMed;
        this.timeUnitChoice=timeUnitChoice;
        this.medId=medId;
    }
    public AddMedFragment3( String medName, String medUnit, String startDate,
                           String endDate, int numOfMed, String timeUnitChoice, String medId,List<MedDataWeek> medDataWeekList) {
        this.medDataWeekList=medDataWeekList;
        this.medName=medName;
        this.medUnit=medUnit;
        this.startDate=startDate;
        this.endDate=endDate;
        this.numOfMed=numOfMed;
        this.timeUnitChoice=timeUnitChoice;
        this.medId=medId;
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
        currentFirebaseUser= FirebaseAuth.getInstance().getCurrentUser() ;


        tvSkip=view.findViewById(R.id.tvSkip);
        edtLeftNum=view.findViewById(R.id.edtLeftNum);
        edtNumRefill=view.findViewById(R.id.edtNumRefill);
        timePicker1=view.findViewById(R.id.timePicker1);
        tvUnitReminder=view.findViewById(R.id.tvUnitReminder);
        tvUnitReminder2=view.findViewById(R.id.tvUnitReminder2);
        btnSave=view.findViewById(R.id.btnSave);
        tvUnitReminder.setText(medUnit);
        tvUnitReminder2.setText(medUnit);

        timePicker1.setIs24HourView(true);
        time = String.valueOf(timePicker1.getHour()).toString() + ":" + String.valueOf(timePicker1.getMinute()).toString();
        timePicker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                time = String.valueOf(hourOfDay).toString() + ":" + String.valueOf(minute).toString();
            }
        });
        id= currentFirebaseUser.getUid();


        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status="";
                if(timeUnitChoice.equals("Day")){
                     status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithOutRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataDayArray));

                }
                else if(timeUnitChoice.equals("Week")){
                    status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithOutRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataWeekList));
                }
                else if(timeUnitChoice.equals("Month")){
                    status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithOutRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataMonthList));

                }
                Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();

                //NavController navController= Navigation.findNavController(v);
                //NavDirections navDirections=AddMedFragment3Directions.next();
                //navController.navigate(navDirections);
                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status="";
                if(timeUnitChoice.equals("Day")){
                    status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataDayArray,
                            presenter.setRefiledData(time,
                                    Integer.parseInt(edtLeftNum.getText().toString()),
                                    Integer.parseInt(edtNumRefill.getText().toString()))));

                }
                else if(timeUnitChoice.equals("Week")){
                    status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataWeekList,
                            presenter.setRefiledData(time,
                                    Integer.parseInt(edtLeftNum.getText().toString()),
                                    Integer.parseInt(edtNumRefill.getText().toString()))));
                }
                else if(timeUnitChoice.equals("Month")){
                    status=presenter.SetDadaIntoDatabase(presenter.setMedDataWithRefillReminder(medName,medUnit,startDate,endDate,id,medId,
                            numOfMed,timeUnitChoice, medDataMonthList,
                            presenter.setRefiledData(time,
                                    Integer.parseInt(edtLeftNum.getText().toString()),
                                    Integer.parseInt(edtNumRefill.getText().toString()))));
                }
                Toast.makeText(getContext(), status, Toast.LENGTH_SHORT).show();

                //NavController navController= Navigation.findNavController(v);
                //NavDirections navDirections=AddMedFragment3Directions.next();
                //navController.navigate(navDirections);
                Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });
        return view;
    }


}