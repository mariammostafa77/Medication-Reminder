package com.example.medicationreminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class CustomDialogFragment extends DialogFragment {

    private static final String TAG = "CUSTOM";
DatabaseReference reference;
    TextView nameee,skip,refill,snooze,count;
    Button start, cancel, later;
    String tripName,  startPoint, endPoint,tripId;
    MediaPlayer mediaPlayer;
    NotificationManager notificationManager;


    public CustomDialogFragment() {
        // Required empty public constructor
    }
    public CustomDialogFragment(String tripIdString ,String tripName, String startPoint, String endPoint) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.tripId = tripId;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_custom_dialog, container, false);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        nameee=view.findViewById(R.id.namemed);
        count=view.findViewById(R.id.num);
        mediaPlayer = MediaPlayer.create(getContext(), Settings.System.DEFAULT_RINGTONE_URI);
        notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(200);
        ArrayList<String>arrayList=new ArrayList();
        ArrayList<Integer>arrayList2=new ArrayList<>();
        mediaPlayer.start();
        createNotificationChannel();

       skip=view.findViewById(R.id.skkip);
       refill=view.findViewById(R.id.refill);
       snooze=view.findViewById(R.id.snooze);

       skip.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getContext(), "cancel Clicked", Toast.LENGTH_SHORT).show();
               getDialog().cancel();
               getActivity().finish();
               mediaPlayer.stop();
           }
       });
       // name.setText(tripName);

        refill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //displayTrack(startPoint, endPoint);
                getDialog().cancel();
                getActivity().finish();

                mediaPlayer.stop();

            }
        });
        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), Notification.class);
                getActivity().sendBroadcast(intent);

                Toast.makeText(getContext(), "later Clicked", Toast.LENGTH_SHORT).show();
                getDialog().cancel();
                getActivity().finish();
                mediaPlayer.stop();

            }
        });
/*
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo change trip status to canceled
                Toast.makeText(getContext(), "cancel Clicked", Toast.LENGTH_SHORT).show();
                getDialog().cancel();
                getActivity().finish();
                mediaPlayer.stop();
            }
        });*/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        LocalDate localDate = LocalDate.now();
        // Date date=dtf.format(localDate);
        System.out.println(dtf.format(localDate));
        try {
            Date todate=new SimpleDateFormat("dd/MM/yyyy").parse(dtf.format(localDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }


       // MedData medData=new MedData();
        reference= FirebaseDatabase.getInstance().getReference().child("MedicationData");
        Query query=reference.startAt("startDate").endAt("endDate");
        reference.addValueEventListener(new ValueEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

        for(DataSnapshot dataSnapshot:snapshot.getChildren())
        {
            String name=dataSnapshot.child("medName").getValue(String.class);
            String startdate=dataSnapshot.child("startDate").getValue(String.class);
            String enddate=dataSnapshot.child("endDate").getValue(String.class);
           // String time=dataSnapshot.child("")
            int num= dataSnapshot.child("numOfTimes").getValue(Integer.class);
          //  String time=dataSnapshot.child("remindTime").getValue().toString();

          //  count.setText(""+num);
         //   MedInfo medInfo=new MedInfo();
            arrayList.add(name);
            arrayList2.add(num);
           // RefillMed refillMed=new RefillMed(num);
           // MedInfo medInfo1=new MedInfo(name);
            try {
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(startdate);
                Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(enddate);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
     StringBuilder stringBuilder=new StringBuilder();
            StringBuilder stringBuilder2=new StringBuilder();
        for(int i=0;i<arrayList.size();i++)
        {
            stringBuilder.append(arrayList.get(i));
        }
            for(int i=0;i<arrayList2.size();i++)
            {
                stringBuilder2.append(arrayList2.get(i));
            }
        nameee.setText(""+stringBuilder.toString());
           count.setText(""+stringBuilder2.toString());
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
        return view;
    }

   /* private void displayTrack(String startPoint, String endPoint) {
        try {
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + startPoint + "/" + endPoint);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        }
    }*/

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence sequence = "ReminderChannel";
            String description = "Channel For Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("notify", sequence, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}