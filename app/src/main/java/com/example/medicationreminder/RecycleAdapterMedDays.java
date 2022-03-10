package com.example.medicationreminder;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class RecycleAdapterMedDays extends RecyclerView.Adapter<RecycleAdapterMedDays.ViewHolder>  {
    //MyData[] myModels;
    int count=AddMedFragment1.MedNum;
    Context context;

    public RecycleAdapterMedDays(Context context) {
        //this.myModels = myModels;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        View row;
        TextView EdtTimeEveryDay,edtDosDay,tvUnitOfDay;
        ImageView btnTimeOfDay;
        public ViewHolder(@NonNull View convertView) {
            super(convertView);
            row=convertView;
            EdtTimeEveryDay=row.findViewById(R.id.EdtTimeEveryDay);
            edtDosDay=row.findViewById(R.id.edtDosDay);
            tvUnitOfDay=row.findViewById(R.id.tvUnitOfDay);
            btnTimeOfDay=row.findViewById(R.id.btnTimeOfDay);

        }

        public View getRow() {
            return row;
        }
        public TextView getEdtTimeEveryDay() {
            return EdtTimeEveryDay;
        }
        public TextView getEdtDosDay() {
            return edtDosDay;
        }
        public TextView getTvUnitOfDay() {
            return tvUnitOfDay;
        }
        public ImageView getBtnTimeOfDay() {
            return btnTimeOfDay;
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
        //holder.getBtnTimeOfDay();
        //holder.getEdtDosDay();
        for(int i=0;i<count;i++){
            holder.getTvUnitOfDay().setText(AddMedFragment1.medUnit);
        }
        holder.getBtnTimeOfDay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(holder.EdtTimeEveryDay);
            }
        });
        holder.getEdtTimeEveryDay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(holder.EdtTimeEveryDay);
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
