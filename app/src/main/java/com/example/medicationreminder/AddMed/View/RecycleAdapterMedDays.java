package com.example.medicationreminder.AddMed.View;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.OneTimeWorkRequest;

import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Presenter.AddMedPresenter;
import com.example.medicationreminder.AddMed.Presenter.PresenterInterface;
import com.example.medicationreminder.HomeActivity;
import com.example.medicationreminder.R;
import com.example.medicationreminder.WorkerHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RecycleAdapterMedDays extends RecyclerView.Adapter<RecycleAdapterMedDays.ViewHolder>  {
    int count;
    String unit;
    Context context;
    MyInterfaceForDays myInterfaceForDays;
    PresenterInterface presenter=new AddMedPresenter();
    public static String medTime,dose;
    List<String> arraySpinner;
    //notification
    public static List<OneTimeWorkRequest> requests=new ArrayList<>();
    public static String myMedName,myMedDoseUnit,myMedDose;




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

            arraySpinner = new ArrayList<>();
            arraySpinner.add("Dose");
            arraySpinner.add("0.25");
            arraySpinner.add("0.5");
            arraySpinner.add("0.75");
            arraySpinner.add("1");
            arraySpinner.add("1.25");
            arraySpinner.add("1.5");
            arraySpinner.add("1.75");
            arraySpinner.add("2");
            arraySpinner.add("Others");
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

        public ImageView getDropDownListDose() {
            return dropDownListDose;
        }

        public Spinner getSpinnerDose() {
            return spinnerDose;
        }

        public MedDataDay getMedDataDay() {
            return medDataDay;
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
                    //Notification
                    myMedName=AddMedFragment2.medName;
                    myMedDoseUnit=AddMedFragment2.medUnit;
                    myMedDose=dose;

                    int[]startDate=splitMedDate(AddMedFragment2.startDate);

                    int[]endDate=splitMedDate(AddMedFragment2.endDate);
                    Calendar today = Calendar.getInstance();
                    Calendar calendarStartDate = Calendar.getInstance();
                    calendarStartDate.set(startDate[2],startDate[1],startDate[0],hour,minute);
                    long differentDay=differentBetweenDays(AddMedFragment2.startDate,AddMedFragment2.endDate);
                    long diffInMinutes=(calendarStartDate.getTimeInMillis()-today.getTimeInMillis())/60000;
                    long different=diffInMinutes-44640;
                    Log.i("Date",""+different);
                    OneTimeWorkRequest workRequest=new OneTimeWorkRequest.Builder(WorkerHandler.class)
                            .setInitialDelay(different, TimeUnit.MINUTES).build();
                    requests.add(workRequest);
                    for(long i=1;i<=differentDay;i++){
                        long duration=different+1440*i;
                        workRequest=new OneTimeWorkRequest.Builder(WorkerHandler.class)
                                .setInitialDelay(duration, TimeUnit.MINUTES).build();
                        requests.add(workRequest);
                    }



                }
            }, hour, minute, false);
            timePickerDialog.show();
        }
        private boolean CheckAllFields(ViewHolder holder) {
            if (holder.getEdtTimeEveryDay().getText().equals("Select Time")) {
                holder.getEdtTimeEveryDay().setError("This field is required");
                return false;
            }
            if (holder.getSpinnerDose().getSelectedItem().toString().equals("Dose")) {
                Toast.makeText(context, "This field is required", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
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


    public int[] splitMedDate(String data) {
        String[] splitDate = data.split("/");
        int day = Integer.parseInt(splitDate[0]);

        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int[] allDate = {day, month, year};
        return allDate;
    }
    public long differentBetweenDays(String sDate,String eDate){
        long diff=0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d1 = sdf.parse(sDate);
            Date d2 = sdf.parse(eDate);
            long difference_In_Time = d2.getTime() - d1.getTime();
            diff = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return diff;
    }

}
