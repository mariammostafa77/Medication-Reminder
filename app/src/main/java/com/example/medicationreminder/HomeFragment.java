package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;




import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class HomeFragment extends Fragment  {
    String meddate;
    TextView allDate;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        CalendarView calendarView=v.findViewById(R.id.custom_calendar);
        allDate=v.findViewById(R.id.allDate);

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month= c.get(Calendar.MONTH)+1;
        int year=c.get(Calendar.YEAR);
        meddate=day + "/"+month+"/"+year;


        allDate.setText(meddate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

            int actualMonth=month+1;
               meddate=dayOfMonth+"/"+actualMonth+"/"+year;
                allDate.setText(meddate);

            }
        });
        return v;

    }
}