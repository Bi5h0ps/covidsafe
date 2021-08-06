package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.cs446.covidsafe.R;

public class VaccineAlertNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(1, createNotification(context, false));
    }

    private Notification createNotification(Context context, boolean makeHeadsUpNotification) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "chat")
                .setSmallIcon(R.drawable.covid)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Sample Notification")
                .setContentText("This is a normal notification.");
        return notificationBuilder.build();
    }
}
