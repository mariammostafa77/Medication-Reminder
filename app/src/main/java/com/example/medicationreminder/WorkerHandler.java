package com.example.medicationreminder;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.medicationreminder.AddMed.View.RecycleAdapterMedDays;

public class WorkerHandler extends Worker {
    private MedicationNotification mNotificationHelper;
    public WorkerHandler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        mNotificationHelper=new MedicationNotification(getApplicationContext());
        NotificationCompat.Builder nb=mNotificationHelper.getChannelNotification("Med Time","You should take the Medication");
        mNotificationHelper.getManager().notify(1,nb.build());
        Log.i("Date","inDoWork");


        return Result.success();
    }
}
