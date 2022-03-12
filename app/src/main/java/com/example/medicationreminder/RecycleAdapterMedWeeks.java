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

public class RecycleAdapterMedWeeks extends RecyclerView.Adapter<RecycleAdapterMedWeeks.ViewHolder>  {
        //MyData[] myModels;
        int count=AddMedFragment1.MedNum;
        Context context;

public RecycleAdapterMedWeeks(Context context) {
        //this.myModels = myModels;
        this.context = context;
        }

class ViewHolder extends RecyclerView.ViewHolder{

    View row;
    Spinner spinnerDays;
    TextView tvTimeWeek,tvUnitOfWeek;
    EditText edtDoseWeek;
    ImageView imgTimeWeek;
    public ViewHolder(@NonNull View convertView) {
        super(convertView);
        row=convertView;
        spinnerDays=row.findViewById(R.id.spinnerDays);
        tvTimeWeek=row.findViewById(R.id.tvTimeWeek);
        edtDoseWeek=row.findViewById(R.id.edtDoseWeek);
        tvUnitOfWeek=row.findViewById(R.id.tvUnitOfWeek);
        imgTimeWeek=row.findViewById(R.id.imgTimeWeek);

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

    public EditText getEdtDoseWeek() {
        return edtDoseWeek;
    }

    public ImageView getImgTimeWeek() {
        return imgTimeWeek;
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

        String[] daysOfWeeks = new String[]{"Saturday", "Sunday", "Monday","Tuesday","Wednesday","Thursday","Friday"};
        ArrayAdapter<String> numberTakenAdapter = new ArrayAdapter<String>(context.getApplicationContext(),
                android.R.layout.simple_spinner_item, daysOfWeeks);
        numberTakenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.getSpinnerDays().setAdapter(numberTakenAdapter);

        for(int i=0;i<count;i++){
            holder.getTvUnitOfWeek().setText(AddMedFragment1.medUnit);
        }
        holder.getImgTimeWeek().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(holder.tvTimeWeek);
            }
        });
        holder.getTvTimeWeek().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(holder.tvTimeWeek);
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
