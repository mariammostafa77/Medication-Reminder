package com.example.medicationreminder.AddMed.View;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Presenter.AddMedPresenter;
import com.example.medicationreminder.AddMed.Presenter.PresenterInterface;
import com.example.medicationreminder.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecycleAdapterMedDays extends RecyclerView.Adapter<RecycleAdapterMedDays.ViewHolder>  {
    int count;
    String unit;
    Context context;
    MyInterfaceForDays myInterfaceForDays;
    PresenterInterface presenter=new AddMedPresenter();
    String medTime,dose;



    public RecycleAdapterMedDays(Context context,MyInterfaceForDays myInterfaceForDays,int count,String unit) {
        this.context = context;
        this.myInterfaceForDays=myInterfaceForDays;
        this.count=count;
        this.unit=unit;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        View row;
        TextView EdtTimeEveryDay,tvUnitOfDay;
        ImageView btnTimeOfDay,dropDownListDose;
        Spinner spinnerDose;
        MedDataDay medDataDay;

        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row=convertView;
            EdtTimeEveryDay=row.findViewById(R.id.tvStartDate);
            tvUnitOfDay=row.findViewById(R.id.tvUnitOfDay);
            btnTimeOfDay=row.findViewById(R.id.btnTimeOfDay);
            spinnerDose= row.findViewById(R.id.spinnerDose);
            dropDownListDose=row.findViewById(R.id.dropDownListDose);
            medDataDay=new MedDataDay();
            myInterfaceForDays.getData(medDataDay);

        }

        public void bind(){

            for(int i=0;i<count;i++){
                getTvUnitOfDay().setText(unit);
            }

            btnTimeOfDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(EdtTimeEveryDay);
                    medTime=EdtTimeEveryDay.getText().toString();
                    medDataDay.setTime(medTime);
                    Log.i("TAG","time from btnTimeOfDay "+medTime);

                }
            });

            EdtTimeEveryDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(EdtTimeEveryDay);
                    medTime=EdtTimeEveryDay.getText().toString();
                    medDataDay.setTime(medTime);
                    Log.i("TAG","time from EdtTimeEveryDay click "+medTime);
                }
            });
            String[] arraySpinner = new String[]{"Dose","0.25", "0.5", "0.75", "1", "1.25", "1.5", "1.75","2","Others"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.getApplicationContext(),
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDose.setAdapter(adapter);
            dropDownListDose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinnerDose.performClick();
                }
            });
            spinnerDose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    dose = parent.getItemAtPosition(position).toString();
                    medDataDay.setDose(dose);
                    Log.i("TAG"," dose from spinner click = "+dose);

                }
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });
            Log.i("TAG","time from onBind= "+medTime+" dose= "+dose);
            Log.i("TAG","time from constructor= "+medTime+" dose= "+dose);

        }


        public View getRow() {
            return row;
        }
        public TextView getEdtTimeEveryDay() {
            return EdtTimeEveryDay;
        }
        public TextView getTvUnitOfDay() {
            return tvUnitOfDay;
        }
        public ImageView getBtnTimeOfDay() {
            return btnTimeOfDay;
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
                    medTime=time;
                    medDataDay.setTime(medTime);
                    Log.i("TAG","time from EdtTimeEveryDay click "+medTime);
                }
            }, hour, minute, false);
            timePickerDialog.show();
        }
    }



    @NonNull
    @Override
    public RecycleAdapterMedDays.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_row_days,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
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
