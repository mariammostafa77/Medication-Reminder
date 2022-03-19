package com.example.medicationreminder;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkManagerHandler extends Worker {
    private MedicationNotification mNotificationHelper;
    public WorkManagerHandler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        mNotificationHelper=new MedicationNotification(getApplicationContext());
        NotificationCompat.Builder nb=mNotificationHelper.getChannelNotification("Med Time","You should take the medication");
        mNotificationHelper.getManager().notify(1,nb.build());
        Log.i("Date","inDoWork");
        return Result.success();
    }
}
