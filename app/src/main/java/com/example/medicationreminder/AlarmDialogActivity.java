package com.example.medicationreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AlarmDialogActivity extends AppCompatActivity {

    TextView tvMedNameReminder,tvDoseReminder;
    ImageView btnCancelReminder,btnTakeReminder,btnSnoozeReminder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_dialog);
        tvMedNameReminder=findViewById(R.id.tvMedNameReminder);
        tvDoseReminder=findViewById(R.id.tvDoseReminder);
        btnCancelReminder=findViewById(R.id.btnCancelReminder);
        btnTakeReminder=findViewById(R.id.btnTakeReminder);
        btnSnoozeReminder=findViewById(R.id.btnSnoozeReminder);

        tvMedNameReminder.setText("Setovage");
        tvDoseReminder.setText("1 pill");


        btnCancelReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSnoozeReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}