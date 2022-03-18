package com.example.medicationreminder.AddMed.View;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicationreminder.R;

import java.util.Calendar;

public class AddMedFragment1 extends Fragment {

    Spinner spinnerUnit,numberTakenSpinner;
    ImageView dropDownMedUnit,dropDownChoice,btnCalender1,btnCalender2;
    TextView tvStartDate,tvEndDate;
    Button btnNext;
    EditText edtMedNum,edtMedName;
    String newUnit;

    public AddMedFragment1() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.S)
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
        tvStartDate=view.findViewById(R.id.tvStartDate);
        tvEndDate=view.findViewById(R.id.tvEndDate);
        btnNext=view.findViewById(R.id.btnSave);
        edtMedNum=view.findViewById(R.id.edtMedNum);
        edtMedName=view.findViewById(R.id.edtMedName);
        TextView tvPer=view.findViewById(R.id.tvPer);

        tvPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });

        String[] unitSpinnerArray = new String[]{"pill", "IU", "mcg", "mg", "ml", "mg/ml", "others"};
        ArrayAdapter<String> unitSpinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, unitSpinnerArray);
        unitSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUnit.setAdapter(unitSpinnerAdapter);
        spinnerUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinnerUnit.getSelectedItem().equals("others")){
                    showCustomDialog();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                if(CheckAllFields()){
                    NavController navController= Navigation.findNavController(v);
                    Bundle bundle = new Bundle();
                    bundle.putString("myMedName",edtMedName.getText().toString());
                    bundle.putString("MyMedUnit",spinnerUnit.getSelectedItem().toString());
                    bundle.putString("MyStartDate",tvStartDate.getText().toString());
                    bundle.putString("MyEndDate",tvEndDate.getText().toString());
                    bundle.putInt("numOfMed",Integer.parseInt(edtMedNum.getText().toString()));
                    bundle.putString("timeChoice",numberTakenSpinner.getSelectedItem().toString());


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
    private boolean CheckAllFields() {
        if (edtMedName.length() == 0) {
            edtMedName.setError("This field is required");
            return false;
        }
        if (tvStartDate.getText().equals("Start Date")) {
            tvStartDate.setError("This field is required");
            return false;
        }
        if (tvEndDate.getText().equals("End Date")) {
            tvEndDate.setError("This field is required");
            return false;
        }
        if (edtMedNum.equals("End Date")) {
            edtMedNum.setError("This field is required");
            return false;
        } else if (Integer.parseInt(edtMedNum.getText().toString())<=0) {
            edtMedNum.setError("must be greater than 0");
            return false;
        }
        return true;
    }
    public void showCustomDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_other_unit_dialog, null);
        builder.setView(customLayout);
        builder.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(
                    DialogInterface dialog, int which){
                EditText editText = customLayout.findViewById(R.id.edtNewUnit);
                sendDialogDataToActivity(editText.getText().toString());
                            }
                        });
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

    private void sendDialogDataToActivity(String data) {
        newUnit=data;
        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
    }


}