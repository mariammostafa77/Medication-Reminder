package com.example.medicationreminder.ShowMedication.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicationreminder.MedAdapter;
import com.example.medicationreminder.MedInfo;
import com.example.medicationreminder.StartFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShowMedicationModel implements IshowMedicationModel{
    DatabaseReference database;
    String id=null;
    int i;
    @Override
    public void getMed(ArrayList<MedInfo> medList, MedAdapter medAdapter, int day, int month, int year) {
        String thisDay=day+"/"+month+"/"+year;
        int dayNume=converToDayNum(thisDay);
        String thisDayName=dayName(dayNume);
        database = FirebaseDatabase.getInstance().getReference().child("MedicationData");
        if(StartFragment.isGuest==false) {
            id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MedInfo medInfo = snapshot.getValue(MedInfo.class);
                medAdapter.notifyDataSetChanged();

                String startDate = medInfo.getStartDate();
                String endtDate = medInfo.getEndDate();
                int[] allStartDates = splitMedDate(startDate);
                int[] allEndDates = splitMedDate(endtDate);
                boolean between = betweenDate(allStartDates[0], allStartDates[1], allStartDates[2],
                        allEndDates[0], allEndDates[1], allEndDates[2],
                        day, month, year);
                boolean equal = equalDate(allStartDates[0], allStartDates[1], allStartDates[2],
                        allEndDates[0], allEndDates[1], allEndDates[2],
                        day, month, year);

                Log.i("Data", "End: " + allEndDates);
                if (medInfo.getUserId().equals(id)) {


                    if (medInfo.getMedTakenUnit().equals("Day") && between == true) {
                        for (i = 0; i < medInfo.getNumOfTimes(); i++) {

                            medList.add(medInfo);
                        }
                    }
                    if (medInfo.getMedTakenUnit().equals("Month")) {

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

                    if (medInfo.getMedTakenUnit().equals("Week")) {
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

    }

    @Override
    public boolean betweenDate(int sDay, int sMonth, int sYear, int eDay, int eMonth, int eYear, int cDay, int cMonth, int cYear) {
        boolean result=false;
        Date start = new Date(sYear, sMonth-1,sDay);  // -1 because months are from 0 to 11
        Date end   = new Date(eYear, eMonth-1, eDay);
        Date check = new Date(cYear, cMonth-1, cDay);

        if((check.compareTo(start)>0 && check.compareTo(end)<0)|| check.compareTo(start)==0 || check.compareTo(end)==0){

            result=true;
        }
        return result;
    }

    @Override
    public boolean equalDate(int sDay, int sMonth, int sYear, int eDay, int eMonth, int eYear, int cDay, int cMonth, int cYear) {
        boolean result=false;
        Date start = new Date(sYear, sMonth-1,sDay);  // -1 because months are from 0 to 11
        Date end   = new Date(eYear, eMonth-1, eDay);
        Date check = new Date(cYear, cMonth-1, cDay);

        if( !(check.compareTo(start)<0) && !(check.compareTo(end)>0)){

            result=true;
        }
        return result;
    }

    @Override
    public int converToDayNum(String myDate) {
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

    @Override
    public String dayName(int dayNum) {
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

    @Override
    public int[] splitMedDate(String data) {
        String[] splitDate = data.split("/");
        int day = Integer.parseInt(splitDate[0]);

        int month = Integer.parseInt(splitDate[1]);
        int year = Integer.parseInt(splitDate[2]);
        int[] allDate = {day, month, year};
        return allDate;
    }
}
