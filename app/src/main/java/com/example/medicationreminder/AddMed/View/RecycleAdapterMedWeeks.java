package com.example.medicationreminder.AddMed.View;



import android.app.TimePickerDialog;
import android.content.Context;
import android.media.browse.MediaBrowser;
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
import androidx.work.OneTimeWorkRequest;

import com.example.medicationreminder.AddMed.Model.MedDataWeek;
import com.example.medicationreminder.AddMed.Presenter.AddMedPresenter;
import com.example.medicationreminder.AddMed.Presenter.PresenterInterface;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.R;
import com.example.medicationreminder.WorkerHandler;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RecycleAdapterMedWeeks extends RecyclerView.Adapter<RecycleAdapterMedWeeks.ViewHolder>  {
        int count;
        Context context;
        MyInterfaceForWeek myInterfaceForWeek;
        PresenterInterface presenter=new AddMedPresenter();
        public static String medTime,dose;
        String unit;
    List<String> days=new ArrayList();


public RecycleAdapterMedWeeks(Context context,MyInterfaceForWeek myInterfaceForWeek,int count,String unit) {
        this.context = context;
        this.myInterfaceForWeek=myInterfaceForWeek;
        this.count=count;
        this.unit=unit;
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
            getTvUnitOfWeek().setText(unit);
        }



        imgTimeWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(tvTimeWeek);
                medTime=tvTimeWeek.getText().toString();
                medDataWeek.setTime(medTime);

            }
        });

        tvTimeWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTime(tvTimeWeek);
                medTime=tvTimeWeek.getText().toString();
                medDataWeek.setTime(medTime);

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
                dose = parent.getItemAtPosition(position).toString();
                medDataWeek.setDose(dose);
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
                days.add(selectedItem);
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
                medTime=time;
                medDataWeek.setTime(medTime);

                //Notification
                RecycleAdapterMedDays.myMedName=AddMedWeekFragment.medName;
                RecycleAdapterMedDays.myMedDoseUnit=AddMedWeekFragment.medUnit;
                RecycleAdapterMedDays. myMedDose=dose;

                String endDate=AddMedWeekFragment.endDate;
                Log.i("Date","endDate"+endDate);
                Calendar today = Calendar.getInstance();
                Calendar calendar1 = Calendar.getInstance();
                Calendar calendar2=Calendar.getInstance();
                Format format = new SimpleDateFormat("EEEE");
                Date myDate = new Date();
                Date myEndDate = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    myEndDate=dateFormat.parse(endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                for(int i=1;i<=7;i++){
                    String compareDate=simpleDateFormat.format(myDate);
                    if(myEndDate.after(myDate)||endDate.equals(compareDate)){
                        String str = format.format(myDate);
                        if(days.contains(str)){
                            Calendar calendar3=Calendar.getInstance();
                            calendar3.setTime(myDate);
                            int year= calendar3.get(Calendar.YEAR);
                            int month=calendar3.get(Calendar.MONTH);
                            int day=calendar3.get(Calendar.DAY_OF_MONTH);
                            calendar2.set(year,month+1,day,hour,minute);
                            long diffInMinutes=((calendar2.getTimeInMillis()-today.getTimeInMillis())/60000);
                            diffInMinutes=diffInMinutes-44640;
                            Log.i("Date",String.valueOf(diffInMinutes));
                            OneTimeWorkRequest workRequest= new  OneTimeWorkRequest.Builder(WorkerHandler.class)
                                    .setInitialDelay(diffInMinutes, TimeUnit.MINUTES)
                                    .build();
                            if(diffInMinutes>0){
                                RecycleAdapterMedDays.requests.add(workRequest);

                            }

                        }
                        calendar1.add(Calendar.DATE, 1);
                        myDate = calendar1.getTime();
                    }
                }

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


    public int[] splitMedDate(String data) {
        String[] splitDate = data.split("/");
        int day = Integer.parseInt(splitDate[0]);

        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int[] allDate = {day, month, year};
        return allDate;
    }
}
