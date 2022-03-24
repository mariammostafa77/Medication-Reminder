package com.example.medicationreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.medicationreminder.Model.MedInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.awt.font.TextAttribute;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
ArrayList<String > arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferencesGuest=getSharedPreferences("guest", Context.MODE_PRIVATE);
        Thread splashScreenStarter = new Thread() {
            public void run() {
                try {
                    int delay = 0;
                    while (delay < 2000) {
                        sleep(150);
                        delay = delay + 100;
                    }
                    if(sharedPreferences.getString("email",null) ==null) {
                       startActivity(new Intent(MainActivity.this, StartActivity.class));
                }
                    else{
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                     }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }

        };
        splashScreenStarter.start();
        arr=new ArrayList<>();
       /* DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // String []arr=snapshot.getValue();
                if(snapshot.exists())
                {

                    for(DataSnapshot dss:snapshot.getChildren())
                    {

                        String timename=dss.getValue(String.class);
                        arr.add(timename);
                    }
                    for(int i=0;i<arr.size();i++)
                    {
                    System.out.println("+++++++++++"+ arr.get(i));
                }
                }
                else
                {
                    System.out.println("+++++++++++"+"not exist");
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.child("MedicationData").getChildren()) {
                    final String medtime = postSnapshot.child("timeList").child("time").getValue(String.class);
                    final int mun = postSnapshot.child("numOfTimes").getValue(Integer.class);
                    //com.example.medicationreminder.Model.MedInfo Medinfot = new MedInfo(medname,mun);
                    arr.add(medtime);

                }
                for(int i=0;i<arr.size();i++)
                {
                    System.out.println("finallllllllllllllll"+arr.get(i));
                }
                //recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        Intent intent = new Intent(MainActivity.this, ReminderBroadCast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long time = System.currentTimeMillis();
        long tenSecondsImMillis = 1000 * 2;

        alarmManager.set(AlarmManager.RTC_WAKEUP, time + tenSecondsImMillis, pendingIntent);
        System.out.println(time + tenSecondsImMillis);


    }

}