package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class VaccineAlertNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ababababa we are testing",Toast.LENGTH_SHORT).show();
    }
}
