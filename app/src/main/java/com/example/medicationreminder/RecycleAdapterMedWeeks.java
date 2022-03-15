package com.example.medicationreminder;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class RecycleAdapterMedWeeks extends RecyclerView.Adapter<RecycleAdapterMedWeeks.ViewHolder>  {
        int count=AddMedFragment1.MedNum;
        Context context;
        MyInterfaceForWeek myInterfaceForWeek;

public RecycleAdapterMedWeeks(Context context,MyInterfaceForWeek myInterfaceForWeek) {
        this.context = context;
        this.myInterfaceForWeek=myInterfaceForWeek;
        }

class ViewHolder extends RecyclerView.ViewHolder{

    View row;
    Spinner spinnerDays;
    TextView tvTimeWeek,tvUnitOfWeek;
    ImageView imgTimeWeek;
    Spinner spinnerDoseOfWeek;
    ImageView dropDownListDoseOfWeek;
    MedDataWeek medDataWeek;

    public ViewHolder(@NonNull View convertView) {
        super(convertView);
        row=convertView;
        spinnerDays=row.findViewById(R.id.spinnerDays);
        tvTimeWeek=row.findViewById(R.id.tvTimeWeek);
        tvUnitOfWeek=row.findViewById(R.id.tvUnitOfWeek);
        imgTimeWeek=row.findViewById(R.id.imgTimeWeek);
        dropDownListDoseOfWeek=row.findViewById(R.id.dropDownListDoseOfWeek);
        spinnerDoseOfWeek=row.findViewById(R.id.spinnerDoseOfWeek);
        medDataWeek=new MedDataWeek();
        myInterfaceForWeek.getData(medDataWeek);

    }


    public void bind(){

        for(int i=0;i<count;i++){
            getTvUnitOfWeek().setText(AddMedFragment1.medUnit);
        }

        getImgTimeWeek().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(tvTimeWeek);
                medDataWeek.setTime(tvTimeWeek.getText().toString());

            }
        });

        getTvTimeWeek().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(tvTimeWeek);
            }
        });
        String[] arraySpinner = new String[]{"Dose","0.25", "0.5", "0.75", "1", "1.25", "1.5", "1.75","2","Others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.getApplicationContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDoseOfWeek.setAdapter(adapter);
        dropDownListDoseOfWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDoseOfWeek.performClick();
            }
        });
        spinnerDoseOfWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                medDataWeek.setDose(selectedItem);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        String[] daysOfWeeks = new String[]{"Saturday", "Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> numberTakenAdapter = new ArrayAdapter<String>(context.getApplicationContext(),
                android.R.layout.simple_spinner_item, daysOfWeeks);
        numberTakenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSpinnerDays().setAdapter(numberTakenAdapter);
        dropDownListDoseOfWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerDoseOfWeek.performClick();
            }
        });
        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                medDataWeek.setDayOfWeek(selectedItem);
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }

    public View getRow() {
        return row;
    }

    public Spinner getSpinnerDays() {
        return spinnerDays;
    }

    public TextView getTvTimeWeek() {
        return tvTimeWeek;
    }

    public TextView getTvUnitOfWeek() {
        return tvUnitOfWeek;
    }

    public ImageView getImgTimeWeek() {
        return imgTimeWeek;
    }

    public Spinner getSpinnerDoseOfWeek() {
        return spinnerDoseOfWeek;
    }

    public ImageView getDropDownListDoseOfWeek() {
        return dropDownListDoseOfWeek;
    }

    public void getTime(TextView textView){
        Calendar calendar = Calendar.getInstance();
        final int hour = calendar.get(calendar.HOUR_OF_DAY);
        final int minute = calendar.get(calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(textView.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String time = hour + ":" + minute;
                textView.setText(time);
                medDataWeek.setTime(textView.getText().toString());
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }
}



    @NonNull
    @Override
    public RecycleAdapterMedWeeks.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_row_week,parent,false);
        RecycleAdapterMedWeeks.ViewHolder viewHolder=new RecycleAdapterMedWeeks.ViewHolder(view);
        Log.i("TAG","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         holder.bind();

    }


    @Override
    public int getItemCount() {
        return count;
    }



}
