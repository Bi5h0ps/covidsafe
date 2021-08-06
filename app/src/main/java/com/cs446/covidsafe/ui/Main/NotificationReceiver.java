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

public class NotificationReceiver extends BroadcastReceiver {
    private static final int NOTIFICATION_FLAG = 999;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "basic notification created", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals("notify")) {

            PendingIntent pendingIntent = PendingIntent.getActivities(context, 0,
                    new Intent[]{new Intent(context, MainActivity.class)}, 0);

            NotificationManager manager = (NotificationManager) context
                    .getSystemService(context.NOTIFICATION_SERVICE);
            manager.notify(1, createNotification(context, false));
            Toast.makeText(context, "Show Notification clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private Notification createNotification(Context context, boolean makeHeadsUpNotification) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "chat")
                .setSmallIcon(R.drawable.covid)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Sample Notification")
                .setContentText("This is a normal notification.");
        if (makeHeadsUpNotification) {
            Intent push = new Intent();
            push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            push.setClass(context, MainActivity.class);

            PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
                    push, PendingIntent.FLAG_CANCEL_CURRENT);
            notificationBuilder
                    .setContentText("Heads-Up Notification on Android L or above.")
                    .setFullScreenIntent(fullScreenPendingIntent, true);
        }
        return notificationBuilder.build();
    }
}
