
package com.example.medicationreminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationView;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.ShowMedication.presenter.ShowMedicationPresenter;

import java.util.ArrayList;
import java.util.Calendar;



public class HomeFragment extends Fragment implements IShowMedicationView {

    RecyclerView medRecyclerView;
    MedAdapter medAdapter;
    public static ArrayList<MedInfo> medList;
    ShowMedicationPresenter presenter;
    AlarmManager alarmManager;

    PendingIntent pendingIntent;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
//**************************************************
    public int[] splitMedDate(String data) {
        String[] splitDate = data.split("/");
        int day = Integer.parseInt(splitDate[0]);

        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int[] allDate = {day, month, year};
        return allDate;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        CalendarView calendarView = v.findViewById(R.id.custom_calendar);

        Calendar c = Calendar.getInstance();
        int thisday = c.get(Calendar.DAY_OF_MONTH);
        int thismonth = c.get(Calendar.MONTH) + 1;
        int thisyear = c.get(Calendar.YEAR);
        presenter=new ShowMedicationPresenter(this);


        medRecyclerView = v.findViewById(R.id.medRecyclerview);

        medRecyclerView.setHasFixedSize(true);
        medRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        medList = new ArrayList<>();
        medAdapter = new MedAdapter(getContext(), medList);

        medList.clear();

        presenter.onCalenderItemSlected(medList,medAdapter,thisday, thismonth, thisyear);

        setupAlarm();
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(medList!=null){
                    for(int i=0;i<1;i++) {
                        for (int j = 0; j < medList.get(i).getNumOfTimes(); j++){
                            Log.i("TAG", medList.get(i).getTimeList().get(j).getTime());
                            int[] splitArray2 = splitTime(medList.get(i).getTimeList().get(j).getTime());
                            Log.i("TAG", "split = " + splitArray2[0] + " , " + splitArray2[1]);
                            setupAlarm(splitArray2[0], splitArray2[1], i);
                        }
                    }
                }
            }
        }, 5000);*/



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int actualMonth = month + 1;
                Log.i("Data", "" + dayOfMonth);
                medList.clear();
                presenter.onCalenderItemSlected(medList,medAdapter,dayOfMonth, actualMonth, year);

            }
        });

        return v;

    }

    @Override
    public void showMed() {
        medAdapter.notifyDataSetChanged();
        medRecyclerView.setAdapter(medAdapter);

    }

    public void setupAlarm() {

        alarmManager = (AlarmManager)   getContext().getSystemService(Context.ALARM_SERVICE);

        Intent intent =new  Intent(getContext(), AlarmRecievier.class);

        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);

        setAlarm();

    }

    public void setAlarm() {
        AlarmManager am = (AlarmManager)   getContext().getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 5000, pendingIntent);


    }
    public int[] splitTime(String data) {
        String[] splitDate = data.split(":");
        int hour = Integer.parseInt(splitDate[0]);

        int minuits = Integer.parseInt(splitDate[1]);
        int[] allDate = {hour, minuits};
        return allDate;
    }
}