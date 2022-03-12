package com.example.medicationreminder;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public RecycleAdapterMedMonth(Context context) {
            this.context = context;
        }
    class ViewHolder extends RecyclerView.ViewHolder{

        View row;
        Spinner spinnerDaysInMonth;
        TextView tvTimeMonth,tvUnitOfMonth;
        EditText edtDoseMonth;
        ImageView imgTimeMonth;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row=convertView;
            spinnerDaysInMonth=row.findViewById(R.id.spinnerDaysInMonth);
            tvTimeMonth=row.findViewById(R.id.tvTimeMonth);
            edtDoseMonth=row.findViewById(R.id.edtDoseMonth);
            tvUnitOfMonth=row.findViewById(R.id.tvUnitOfMonth);
            imgTimeMonth=row.findViewById(R.id.imgTimeMonth);

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

        public EditText getedtDoseMonth() {
            return edtDoseMonth;
        }

        public ImageView getimgTimeMonth() {
            return imgTimeMonth;
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
            c = Calendar.getInstance();
            monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            Log.i("tag",monthMaxDays+"");
            String[] daysOfMonth=new String[monthMaxDays];
            for(int i=0;i<monthMaxDays;i++){
                String value=String.valueOf(i+ 1);
                daysOfMonth[i]=value;
            }
            ArrayAdapter<String> numberTakenAdapter = new ArrayAdapter<String>(context.getApplicationContext(),
                    android.R.layout.simple_spinner_item, daysOfMonth);
            numberTakenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            holder.getspinnerDaysInMonth().setAdapter(numberTakenAdapter);

            for(int i=0;i<count;i++){
                holder.gettvUnitOfMonth().setText(AddMedFragment1.medUnit);
            }
            holder.getimgTimeMonth().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(holder.tvTimeMonth);
                }
            });
            holder.gettvTimeMonth().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getTime(holder.tvTimeMonth);
                }
            });
        Log.i("TAG","onBindViewHolder");
    }


    @Override
        public int getItemCount() {
            return count;
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
                }
            }, hour, minute, false);
            timePickerDialog.show();
        }

    }
