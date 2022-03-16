
package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class HomeFragment extends Fragment {
    String meddate;
    RecyclerView medRecyclerView;
    DatabaseReference database;
    MedAdapter medAdapter;
    ArrayList<MedInfo> medList;
    int i;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

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


        medRecyclerView = v.findViewById(R.id.medRecyclerview);
        database = FirebaseDatabase.getInstance().getReference("MedicationData");
        medRecyclerView.setHasFixedSize(true);
        medRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        medList = new ArrayList<>();
        medAdapter = new MedAdapter(getContext(), medList);

        medList.clear();
        loadMedData(thisday, thismonth, thisyear);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int actualMonth = month + 1;
                Log.i("Data", "" + dayOfMonth);
                medList.clear();
                loadMedData(dayOfMonth, actualMonth, year);

            }
        });

        return v;

    }

    private void loadMedData(int day, int month, int year) {
        String thisDay=day+"/"+month+"/"+year;
        int dayNume=converToDayNum(thisDay);
        String thisDayName=dayName(dayNume);
        database = FirebaseDatabase.getInstance().getReference().child("MedicationData");
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MedInfo medInfo = snapshot.getValue(MedInfo.class);
                medAdapter.notifyDataSetChanged();

                String startDate = medInfo.getStartDate();
                String endtDate = medInfo.getEndDate();
                int[] allStartDates = splitMedDate(startDate);
                int[] allEndDates = splitMedDate(endtDate);
                boolean between=  betweenDate(allStartDates[0],allStartDates[1],allStartDates[2],
                        allEndDates[0],allEndDates[1],allEndDates[2] ,
                        day,month,year);
                boolean equal=  equalDate(allStartDates[0],allStartDates[1],allStartDates[2],
                        allEndDates[0],allEndDates[1],allEndDates[2] ,
                        day,month,year);

                Log.i("Data", "End: " + allEndDates);
                if(medInfo.getUserId().equals(id)) {


                if(medInfo.getMedTakenUnit().equals("Day") && between==true) {
                    for (i = 0; i < medInfo.getNumOfTimes(); i++) {

                      medList.add(medInfo);
                    }
                }
           if(medInfo.getMedTakenUnit().equals("Month")) {

               for (int c = 0; c < medInfo.getNumOfTimes(); c++) {

                   if (
                           ((day == Integer.parseInt(medInfo.getTimeList().get(c).getDayOfMonth())
                                   )
                                   && month >= allStartDates[1] && year >= allStartDates[2])
                                   &&
                                   (month <= allEndDates[1] && year <= allEndDates[2]) && equal == true

                   ) {
                       medList.add(medInfo);
                   }


               }
           }

                    if(medInfo.getMedTakenUnit().equals("Week")) {
                        for (int w = 0; w < medInfo.getNumOfTimes(); w++) {
                            if (
                                    (thisDayName.equals(medInfo.getTimeList().get(w).getDayOfWeek()))
                                            && (month >= allStartDates[1] && year >= allStartDates[2])
                                            && (month <= allEndDates[1] && year <= allEndDates[2])
                                            && equal == true
                            ) {
                                medList.add(medInfo);
                            }
                        }
                    }

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        medAdapter.notifyDataSetChanged();
        medRecyclerView.setAdapter(medAdapter);

    }

    private boolean betweenDate(int sDay,int sMonth,int sYear,int eDay,int eMonth,int eYear,int cDay,int cMonth,int cYear){
        boolean result=false;
        Date start = new Date(sYear, sMonth-1,sDay);  // -1 because months are from 0 to 11
        Date end   = new Date(eYear, eMonth-1, eDay);
        Date check = new Date(cYear, cMonth-1, cDay);

        if((check.compareTo(start)>0 && check.compareTo(end)<0)|| check.compareTo(start)==0 || check.compareTo(end)==0){

            result=true;
        }
        return result;
    }
    private boolean equalDate(int sDay,int sMonth,int sYear,int eDay,int eMonth,int eYear,int cDay,int cMonth,int cYear){
        boolean result=false;
        Date start = new Date(sYear, sMonth-1,sDay);  // -1 because months are from 0 to 11
        Date end   = new Date(eYear, eMonth-1, eDay);
        Date check = new Date(cYear, cMonth-1, cDay);

        if( !(check.compareTo(start)<0) && !(check.compareTo(end)>0)){

            result=true;
        }
        return result;
    }
    private int converToDayNum(String myDate){
        int dateNum=0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(myDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            dateNum = c.get(Calendar.DAY_OF_WEEK);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateNum;
    }
    private String dayName(int dayNum){
        String name="";
        switch (dayNum){
            case 1:
                name="Sunday";
                break;
            case 2:
                name="Monday";
                break;
            case 3:
                name="Tuesday";
                break;
            case 4:
                name="Wednesday";
                break;
            case 5:
                name="Thursday";
                break;
            case 6:
                name="Friday";
                break;
            case 7:
                name="Saturday";
                break;

        }
        return name;
    }
}