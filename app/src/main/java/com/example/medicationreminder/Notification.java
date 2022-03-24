package com.example.medicationreminder;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Intent i = new Intent(context, ReminderBroadCast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, i, 0);

        NotificationCompat.Action action = new NotificationCompat.Action(null, "get Alert !", pendingIntent);
        NotificationCompat.Builder builder;

        builder = new NotificationCompat.Builder(context, "notify")

                .setContentTitle("your medication")
                .setContentText("Are You Ready To take your medication !")
                .addAction(action)
               .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, builder.build());

    }
}
