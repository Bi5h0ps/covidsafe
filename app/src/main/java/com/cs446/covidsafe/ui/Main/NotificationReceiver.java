package com.cs446.covidsafe.ui.Main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.ui.Covid.CovidUpdates.CovidUpdatesFragment;

public class NotificationReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_FLAG = 777;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("CaseNotification")) {
            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            String countryInfo = intent.getStringExtra("countryInfo");
            manager.notify(1, createNotification(context, countryInfo));
        }
    }

    private Notification createNotification(Context context, String country) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "CasesDailyUpdates")
                .setSmallIcon(R.drawable.ic_notification_alert)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("CovidSafe")
                .setContentText("Check the Covid-19 Cases Update for " + country)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return notificationBuilder.build();
    }

}
