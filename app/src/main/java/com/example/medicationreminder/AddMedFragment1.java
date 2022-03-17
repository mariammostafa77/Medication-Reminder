package com.example.medicationreminder;


import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddMedFragment1 extends Fragment {

    Spinner spinnerUnit,numberTakenSpinner;
    ImageView dropDownMedUnit,dropDownChoice,btnCalender1,btnCalender2;
    TextView tvStartDate,tvEndDate;
    Button btnNext;
    EditText edtMedNum,edtMedName;
    public static MedData medData;
    public static String medName;
    public static String medUnit;
    public static String startDate;
    public static String endDate;
    public static int MedNum;
    public static String numberTaken;
    public static final String MedNumTag="numOfMed";
    public static final String numberTakenTag="timeChoice";

    public AddMedFragment1() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_med1, container, false);
      //  ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        spinnerUnit = view.findViewById(R.id.spinnerUnit);
        numberTakenSpinner = view.findViewById(R.id.numberTakenSpinner);
        dropDownMedUnit = view.findViewById(R.id.dropDownMedUnit);
        dropDownChoice = view.findViewById(R.id.dropDownChoice);
        btnCalender1=view.findViewById(R.id.btnTimeOfDay);
        btnCalender2=view.findViewById(R.id.btnCalender2);
        tvStartDate=view.findViewById(R.id.EdtTimeEveryDay);
        tvEndDate=view.findViewById(R.id.tvEndDate);
        btnNext=view.findViewById(R.id.btnSave);
        edtMedNum=view.findViewById(R.id.edtMedNum);
        edtMedName=view.findViewById(R.id.edtMedName);

        String[] arraySpinner = new String[]{"pill", "IU", "mcg", "mg", "ml", "mg/ml", "others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(adapter);
        dropDownMedUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerUnit.performClick();
            }
        });


        String[] numberTakenArray = new String[]{"Day", "Week", "Month"};
        ArrayAdapter<String> numberTakenAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, numberTakenArray);
        numberTakenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numberTakenSpinner.setAdapter(numberTakenAdapter);
        dropDownChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTakenSpinner.performClick();
            }
        });
        btnCalender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate(tvStartDate);
            }
        });
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(tvStartDate);
            }
        });
        btnCalender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate(tvEndDate);
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(tvEndDate);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController= Navigation.findNavController(v);
                Bundle bundle = new Bundle();
                medName=edtMedName.getText().toString();
                medUnit=spinnerUnit.getSelectedItem().toString();
                startDate=tvStartDate.getText().toString();
                endDate=tvEndDate.getText().toString();
                MedNum=Integer.parseInt(edtMedNum.getText().toString());
                numberTaken=numberTakenSpinner.getSelectedItem().toString();
                bundle.putInt(MedNumTag,Integer.parseInt(edtMedNum.getText().toString()));
                bundle.putString(numberTakenTag,numberTakenSpinner.getSelectedItem().toString());

                if(numberTakenSpinner.getSelectedItem().toString()=="Day"){
                    navController.navigate(R.id.fragmentAddMed2,bundle);
                }
                else if(numberTakenSpinner.getSelectedItem().toString()=="Week"){
                    navController.navigate(R.id.addMedFragmentWeek,bundle);
                }
                else if(numberTakenSpinner.getSelectedItem().toString()=="Month"){
                    navController.navigate(R.id.addMedFragmentMonth,bundle);
                }



            }
        });

        return view;
    }
    public void getDate(TextView textView){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                textView.setText(date);
            }
        }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}