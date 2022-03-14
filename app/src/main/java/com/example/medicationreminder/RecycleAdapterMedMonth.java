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

public class RecycleAdapterMedMonth extends RecyclerView.Adapter<com.example.medicationreminder.RecycleAdapterMedMonth.ViewHolder>  {

    int count=AddMedFragment1.MedNum;
    Context context;
    Calendar c;
    int monthMaxDays;
    MyInterfaceForMonth myInterfaceForMonth;

    public RecycleAdapterMedMonth(Context context,MyInterfaceForMonth myInterfaceForMonth) {
        this.context = context;
        this.myInterfaceForMonth=myInterfaceForMonth;
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        View row;
        Spinner spinnerDaysInMonth,spinnerDoseOfMonth;
        TextView tvTimeMonth,tvUnitOfMonth;
        ImageView imgTimeMonth,dropDownListDoseOfMonth;
        MedDataMonth medDataMonth;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row=convertView;
            spinnerDaysInMonth=row.findViewById(R.id.spinnerDaysInMonth);
            tvTimeMonth=row.findViewById(R.id.tvTimeMonth);
            tvUnitOfMonth=row.findViewById(R.id.tvUnitOfMonth);
            imgTimeMonth=row.findViewById(R.id.imgTimeMonth);
            spinnerDoseOfMonth=row.findViewById(R.id.spinnerDoseOfMonth);
            dropDownListDoseOfMonth=row.findViewById(R.id.dropDownListDoseOfMonth);
            medDataMonth=new MedDataMonth();
            myInterfaceForMonth.getData(medDataMonth);
        }

        public void bind(){

            for(int i=0;i<count;i++){
                gettvUnitOfMonth().setText(AddMedFragment1.medUnit);
            }

            getimgTimeMonth().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(tvTimeMonth);

                }
            });

            gettvTimeMonth().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(tvTimeMonth);
                }
            });
            String[] arraySpinner = new String[]{"Dose","0.25", "0.5", "0.75", "1", "1.25", "1.5", "1.75","2","Others"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context.getApplicationContext(),
                    android.R.layout.simple_spinner_item, arraySpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDoseOfMonth.setAdapter(adapter);
            dropDownListDoseOfMonth.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinnerDoseOfMonth.performClick();
                }
            });
            spinnerDoseOfMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    medDataMonth.setDose(selectedItem);
                }
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });


            c = Calendar.getInstance();
            monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            Log.i("tag",monthMaxDays+"");
            String[] daysOfMonth=new String[monthMaxDays];
            for(int i=0;i<monthMaxDays;i++){
                String value=String.valueOf(i+ 1);
                daysOfMonth[i]=value;
            }
            ArrayAdapter<String> numberTakenAdapter2 = new ArrayAdapter<String>(context.getApplicationContext(),
                    android.R.layout.simple_spinner_item, daysOfMonth);
            numberTakenAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            getspinnerDaysInMonth().setAdapter(numberTakenAdapter2);
            for(int i=0;i<count;i++){
                gettvUnitOfMonth().setText(AddMedFragment1.medUnit);
            }
            spinnerDaysInMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    medDataMonth.setDayOfMonth(selectedItem);
                }
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });



        }

        public Spinner getspinnerDaysInMonth() {
            return spinnerDaysInMonth;
        }

        public TextView gettvTimeMonth() {
            return tvTimeMonth;
        }

        public TextView gettvUnitOfMonth() {
            return tvUnitOfMonth;
        }
        public ImageView getimgTimeMonth() {
            return imgTimeMonth;
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
                    medDataMonth.setTime(tvTimeMonth.getText().toString());

                }
            }, hour, minute, false);
            timePickerDialog.show();
        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_row_month,parent,false);
        ViewHolder viewHolder=new RecycleAdapterMedMonth.ViewHolder(view);
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
