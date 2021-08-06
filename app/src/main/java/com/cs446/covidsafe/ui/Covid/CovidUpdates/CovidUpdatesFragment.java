package com.cs446.covidsafe.ui.Covid.CovidUpdates;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.ui.Main.NotificationReceiver;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidUpdatesFragment extends Fragment {

    private CovidUpdatesViewModel viewModel;
    private AlarmManager alarmManager;

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;

    private Long DailyDeaths;
    private Long DailyConfirmed;
    private Long DailyRecovered;

    private Long freq;

    private PieDataSet set;
    private PieData data;

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

    @BindView(R.id.FrequencySpinner)
    Spinner spinner;

    public CovidUpdatesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CovidUpdatesViewModel.class);
        viewModel.init();
        alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        set = new PieDataSet(null, "");
        data = new PieData(set);
        freq = AlarmManager.INTERVAL_DAY;

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        createNotificationChannel("CasesDailyUpdates", "CovidUpdatesChannel",
                "Covid Daily Updates Notification Channel", NotificationManager.IMPORTANCE_DEFAULT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_updates, container, false);
        ButterKnife.bind(this, view);


        String[] frequency = new String[]{"Daily", "Weekly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, frequency);
        spinner.setAdapter(adapter);

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

                    DailyDeaths = sorted.get(Date) - sorted.get(sorted.lowerKey(Date));
                    if (DailyDeaths < 0) {
                        deathsCase.setText("N/A");
                    } else {
                        deathsCase.setText(Long.toString(DailyDeaths));
                        setPieChart(DailyDeaths, "Deaths", getResources().getColor(R.color.blue));
                    }
                }
            }
        });

        viewModel.getResponseData("Confirmed").observe(getViewLifecycleOwner(), new Observer<Map<String, Long>>() {
            @Override
            public void onChanged(Map<String, Long> confirmed) {
                if (confirmed != null) {
                    TreeMap<String, Long> sorted = new TreeMap<String, Long>(confirmed);
                    String Date = sorted.lastKey();

                    DailyConfirmed = sorted.get(Date) - sorted.get(sorted.lowerKey(Date));
                    if (DailyConfirmed < 0) {
                        confirmedCase.setText("N/A");
                    } else {
                        confirmedCase.setText(Long.toString(DailyConfirmed));
                        setPieChart(DailyConfirmed, "Confirmed", getResources().getColor(R.color.orange));
                    }
                }
            }
        });

        viewModel.getResponseData("Recovered").observe(getViewLifecycleOwner(), new Observer<Map<String, Long>>() {
            @Override
            public void onChanged(Map<String, Long> recovered) {
                if (recovered != null) {
                    TreeMap<String, Long> sorted = new TreeMap<String, Long>(recovered);
                    String Date = sorted.lastKey();

                    DailyRecovered = sorted.get(Date) - sorted.get(sorted.lowerKey(Date));
                    if (DailyRecovered < 0) {
                        recoveredCase.setText("N/A");
                    } else {
                        recoveredCase.setText(Long.toString(DailyRecovered));
                        setPieChart(DailyRecovered, "Recovered", getResources().getColor(R.color.green));
                    }
                }
            }
        });

        boolean checked = sharedpreferences.getBoolean("checked", false);
        int interval = sharedpreferences.getInt("interval", 0);
        notification.setChecked(checked);
        spinner.setSelection(interval);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (frequency[i].equals(frequency[0])) {
                    freq = 5 * 1000L;
                } else if (frequency[i].equals(frequency[1])) {
                    freq = AlarmManager.INTERVAL_DAY * 7;
                }
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("interval", i);
                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
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
                if (isChecked) {
                    Toast.makeText(getActivity(), "Notification Enabled", Toast.LENGTH_SHORT).show();

                    Calendar cal = Calendar.getInstance();
                    Long currentTime = cal.getTimeInMillis();

                    Intent intent = new Intent(getActivity(), NotificationReceiver.class);

                    intent.putExtra("countryInfo", "Canada");
                    intent.setAction("CaseNotification");

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, currentTime, freq, pendingIntent);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean("checked", notification.isChecked());
                    editor.commit();
                } else {
                    Toast.makeText(getActivity(), "Notification Disabled", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), NotificationReceiver.class);
                    intent.putExtra("countryInfo", "Canada");
                    intent.setAction("CaseNotification");

                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.cancel(pendingIntent);
                    pendingIntent.cancel();

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean("checked", notification.isChecked());
                    editor.commit();
                }
            }
        });
    }

    private void setPieChart(Long cases, String label, int color) {
        PieEntry Entry = new PieEntry(cases, label);

        if (set.getEntryCount() == 0) {
            set.setColor(color);
        } else {
            set.addColor(color);
        }

        set.addEntry(Entry);
        chart.setData(data);
        chart.invalidate();
    }

    private void createNotificationChannel(String CHANNEL_ID, String name, String channel_description, int importance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(channel_description);
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}
