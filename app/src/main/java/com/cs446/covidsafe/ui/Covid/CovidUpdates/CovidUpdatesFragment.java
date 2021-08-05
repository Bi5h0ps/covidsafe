package com.cs446.covidsafe.ui.Covid.CovidUpdates;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.model.ProvinceData;
import com.cs446.covidsafe.ui.Main.MainActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidUpdatesFragment extends Fragment {

    private CovidUpdatesViewModel viewModel;
    private NotificationManager mNotificationManager;

    public static final int NOTIFICATION_ID = 777;

    @BindView(R.id.DeathsCase)
    TextView deathsCase;

    @BindView(R.id.ConfirmedCase)
    TextView confirmedCase;

    @BindView(R.id.RecoveredCase)
    TextView recoveredCase;

    @BindView(R.id.chart)
    PieChart chart;

    @BindView(R.id.notification)
    Switch notification;

    public CovidUpdatesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CovidUpdatesViewModel.class);
        viewModel.init();
        mNotificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_updates, container, false);
        ButterKnife.bind(this, view);

        PieDataSet set = new PieDataSet(null, "");
        PieData data = new PieData(set);

        set.setDrawValues(false);
        chart.setDrawEntryLabels(false);
        chart.getDescription().setEnabled(false);

        chart.setHoleColor(getResources().getColor(R.color.white));
        chart.setHoleRadius(60f);
        chart.setCenterText("Canada COVID-19 Daily Updates");
        chart.setCenterTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

        chart.animateY(2000, Easing.EaseInCirc);

        Legend chartLegend = chart.getLegend();

        chartLegend.setFormSize(12f);
        chartLegend.setTextSize(12f);
        chartLegend.setTypeface(Typeface.DEFAULT_BOLD);

        viewModel.getResponseData("Deaths").observe(getViewLifecycleOwner(), new Observer<Map<String, Long>>() {
            @Override
            public void onChanged(Map<String, Long> deaths) {
                if (deaths != null ) {
                    TreeMap<String, Long> sorted = new TreeMap<String, Long>(deaths);
                    String Date = sorted.lastKey();
                    Long DailyDeaths = sorted.get(Date) - sorted.get(sorted.lowerKey(Date));
                    deathsCase.setText(Long.toString(DailyDeaths));

                    PieEntry DeathEntry = new PieEntry(DailyDeaths, "Deaths");
                    if (set.getEntryCount() == 0) {
                        set.setColor(getResources().getColor(R.color.blue));
                    } else {
                        set.addColor(getResources().getColor(R.color.blue));
                    }
                    set.addEntry(DeathEntry);
                    chart.setData(data);
                    chart.invalidate();
                }
            }
        });

        viewModel.getResponseData("Confirmed").observe(getViewLifecycleOwner(), new Observer<Map<String, Long>>() {
            @Override
            public void onChanged(Map<String, Long> confirmed) {
                if (confirmed != null) {
                    TreeMap<String, Long> sorted = new TreeMap<String, Long>(confirmed);
                    String Date = sorted.lastKey();
                    Long DailyConfirmed = sorted.get(Date) - sorted.get(sorted.lowerKey(Date));
                    confirmedCase.setText(Long.toString(DailyConfirmed));

                    PieEntry ConfirmedEntry = new PieEntry(DailyConfirmed, "Confirmed");
                    if (set.getEntryCount() == 0) {
                        set.setColor(getResources().getColor(R.color.orange));
                    } else {
                        set.addColor(getResources().getColor(R.color.orange));
                    }
                    set.addEntry(ConfirmedEntry);
                    chart.setData(data);
                    chart.invalidate();
                }
            }
        });

        viewModel.getResponseData("Recovered").observe(getViewLifecycleOwner(), new Observer<Map<String, Long>>() {
            @Override
            public void onChanged(Map<String, Long> recovered) {
                if (recovered != null) {
                    TreeMap<String, Long> sorted = new TreeMap<String, Long>(recovered);
                    String Date = sorted.lastKey();
                    Long DailyRecovered = sorted.get(Date) - sorted.get(sorted.lowerKey(Date));
                    recoveredCase.setText(Long.toString(DailyRecovered));

                    PieEntry RecoveredEntry = new PieEntry(DailyRecovered, "Recovered");
                    if (set.getEntryCount() == 0) {
                        set.setColor(getResources().getColor(R.color.green));
                    } else {
                        set.addColor(getResources().getColor(R.color.green));
                    }
                    set.addEntry(RecoveredEntry);
                    chart.setData(data);
                    chart.invalidate();
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //Calendar calendar = Calendar.getInstance();
                String CHANNEL_ID = "chat";
                if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                            "chat",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }

                mNotificationManager.notify(1, createNotification(false));
                Toast.makeText(getActivity(), "Show Notification clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Creates a new notification depending on the argument.
     *
     * @param makeHeadsUpNotification A boolean value to indicating whether a notification will be
     *                                created as a heads-up notification or not.
     *                                <ul>
     *                                <li>true : Creates a heads-up notification.</li>
     *                                <li>false : Creates a non-heads-up notification.</li>
     *                                </ul>
     *
     * @return A Notification instance.
     */
    private Notification createNotification(boolean makeHeadsUpNotification) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getActivity(), "chat")
                .setSmallIcon(R.drawable.covid)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setContentTitle("Sample Notification")
                .setContentText("This is a normal notification.");
        if (makeHeadsUpNotification) {
            Intent push = new Intent();
            push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            push.setClass(getActivity(), MainActivity.class);

            PendingIntent fullScreenPendingIntent = PendingIntent.getActivity(getActivity(), 0,
                    push, PendingIntent.FLAG_CANCEL_CURRENT);
            notificationBuilder
                    .setContentText("Heads-Up Notification on Android L or above.")
                    .setFullScreenIntent(fullScreenPendingIntent, true);
        }
        return notificationBuilder.build();
    }
}