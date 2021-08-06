package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cs446.covidsafe.R;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class VaccineAlertInfoFragment extends Fragment {

    private int day;
    private int month;
    private int year;

    private int secondDate;
    private int secondMonth;
    private int secondYear;

    private int secondFinalDate = 0;
    private int secondFinalMonth = 0;
    private int secondFinalYear = 0;

    private String location;
    private String type;

    private boolean notifsEnabled;
    private boolean firstTimingNotifEnabled;
    private boolean secondTimingNotifEnabled;

    private int firstTimingNotifSetting;
    private int secondTimingNotifSetting;

    public VaccineAlertInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vaccine_alert, container, false);

        // Get data from SharedPreferences
        SharedPreferences sharedPreferences;
        sharedPreferences= getActivity().getSharedPreferences("vaccInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        day = sharedPreferences.getInt("vaccDay", 0);
        month = sharedPreferences.getInt("vaccMonth", 0);
        year = sharedPreferences.getInt("vaccYear", 0);

        location = sharedPreferences.getString("vaccLocation", "null");
        type = sharedPreferences.getString("vaccType", "null");

        notifsEnabled = sharedPreferences.getBoolean("notifsEnabled", false);
        firstTimingNotifEnabled = sharedPreferences.getBoolean("firstTimingNotifEnabled", false);
        secondTimingNotifEnabled = sharedPreferences.getBoolean("secondTimingNotifEnabled", false);

        firstTimingNotifSetting = sharedPreferences.getInt("firstTimingNotifSetting", 0);
        secondTimingNotifSetting = sharedPreferences.getInt("secondTimingNotifSetting", 0);
        // -----------------------------------------------------------------------------------------------------

        // Programmatically initialize all of the components
        CheckBox reminderEnableCheckbox = (CheckBox) view.findViewById(R.id.reminderEnableCheckbox);
        TextView vaccineAlertInfo_firstDoseData = (TextView) view.findViewById(R.id.vaccineAlertInfo_firstDoseData);
        TextView vaccineAlertInfo_secondDoseData = (TextView) view.findViewById(R.id.vaccineAlertInfo_secondDoseData);
        CheckBox earliestTimingCheckbox = (CheckBox) view.findViewById(R.id.earliestTimingCheckbox);
        Spinner earliestTimingSpinner = (Spinner) view.findViewById(R.id.earliestTimingSpinner);
        CheckBox latestTimingCheckbox = (CheckBox) view.findViewById(R.id.latestTimingCheckbox);
        Spinner latestTimingSpinner = (Spinner) view.findViewById(R.id.latestTimingSpinner);
        // -----------------------------------------------------------------------------------------------------



        // First dose info textview ---------------------------------------------------------------
        String monthStr = new DateFormatSymbols().getMonths()[month-1];
        vaccineAlertInfo_firstDoseData.setText("You received your first dose of the " + type + " vaccine on " + day + " " + monthStr + ", " + year + " at " + location + ".");
        // -----------------------------------------------------------------------------------------------------

        // Second dose info textview ---------------------------------------------------------------
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(year + "-" + month + "-" + day));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String secondDoseData = "";

        // Moderna
        if(type.equals(getResources().getStringArray(R.array.vaccine_types)[0]))
        {
            c.add(Calendar.MONTH, 1);
            secondDate = c.get(Calendar.DAY_OF_MONTH);
            secondMonth = c.get(Calendar.MONTH) + 1;
            secondYear = c.get(Calendar.YEAR);

            String secondMonthStr = new DateFormatSymbols().getMonths()[secondMonth-1];
            secondDoseData = "The recommended timing for your second dose is on " + secondDate + " " + secondMonthStr + " " + secondYear + ".";
            earliestTimingCheckbox.setText("Enable reminder for recommended timing");

            latestTimingCheckbox.setVisibility(View.GONE);
            latestTimingSpinner.setVisibility(View.GONE);
            view.findViewById(R.id.textView6).setVisibility(View.GONE);
        }
        // Pfizer
        else if(type.equals(getResources().getStringArray(R.array.vaccine_types)[1]))
        {
            c.add(Calendar.DATE, 21);
            secondDate = c.get(Calendar.DAY_OF_MONTH);
            secondMonth = c.get(Calendar.MONTH) + 1;
            secondYear = c.get(Calendar.YEAR);

            String secondMonthStr = new DateFormatSymbols().getMonths()[secondMonth-1];
            secondDoseData = "The recommended timing for your second dose is on " + secondDate + " " + secondMonthStr + " " + secondYear + ".";
            earliestTimingCheckbox.setText("Enable reminder for recommended timing");

            latestTimingCheckbox.setVisibility(View.GONE);
            latestTimingSpinner.setVisibility(View.GONE);
            view.findViewById(R.id.textView6).setVisibility(View.GONE);
        }
        // AstraZeneca
        else if(type.equals(getResources().getStringArray(R.array.vaccine_types)[2]))
        {
            // Date Range Start
            c.add(Calendar.WEEK_OF_YEAR, 4);
            secondDate = c.get(Calendar.DAY_OF_MONTH);
            secondMonth = c.get(Calendar.MONTH) + 1;
            secondYear = c.get(Calendar.YEAR);

            // Date Range End
            c.add(Calendar.WEEK_OF_YEAR, 8);
            secondFinalDate = c.get(Calendar.DAY_OF_MONTH);
            secondFinalMonth = c.get(Calendar.MONTH) + 1;
            secondFinalYear = c.get(Calendar.YEAR);

            String secondMonthStr = new DateFormatSymbols().getMonths()[secondMonth-1];
            String secondFinalMonthStr = new DateFormatSymbols().getMonths()[secondFinalMonth-1];
            secondDoseData = "The earliest recommended timing for your second dose is on " + secondDate + " " + secondMonthStr + " " + secondYear + ". The latest recommended timing for your second dose is on " + secondFinalDate + " " + secondFinalMonthStr + " " + secondFinalYear + " .";
        }
        // Janssen
        else if(type.equals(getResources().getStringArray(R.array.vaccine_types)[3]))
        {
            secondDoseData = "A second shot is not necessary for the Janssen vaccine.";
            reminderEnableCheckbox.setEnabled(false);
            earliestTimingCheckbox.setVisibility(View.GONE);
            earliestTimingSpinner.setVisibility(View.GONE);
            latestTimingCheckbox.setVisibility(View.GONE);
            latestTimingSpinner.setVisibility(View.GONE);
            view.findViewById(R.id.textView5).setVisibility(View.GONE);
            view.findViewById(R.id.textView6).setVisibility(View.GONE);
        }

        vaccineAlertInfo_secondDoseData.setText(secondDoseData);
        // -----------------------------------------------------------------------------------------------------

        // Enable earliest timing reminder checkbox ---------------------------------------------------------------
        earliestTimingCheckbox.setChecked(firstTimingNotifEnabled);
        earliestTimingCheckbox.setEnabled(notifsEnabled);
        earliestTimingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                firstTimingNotifEnabled = isChecked;
                editor.putBoolean("firstTimingNotifEnabled", firstTimingNotifEnabled);
                Log.d("myLog", "firstTimingNotifEnabled: " + isChecked);
                editor.commit();

                if(firstTimingNotifEnabled)
                {
                    earliestTimingSpinner.setEnabled(true);
                }
                else
                {
                    earliestTimingSpinner.setEnabled(false);
                }
            }
        });
        // -----------------------------------------------------------------------------------------------------

        // Enable latest timing reminder checkbox ---------------------------------------------------------------
        latestTimingCheckbox.setChecked(secondTimingNotifEnabled);
        latestTimingCheckbox.setEnabled(notifsEnabled);
        latestTimingCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                secondTimingNotifEnabled = isChecked;
                editor.putBoolean("secondTimingNotifEnabled", secondTimingNotifEnabled);
                Log.d("myLog", "secondTimingNotifEnabled: " + isChecked);
                editor.commit();

                if(secondTimingNotifEnabled)
                {
                    latestTimingSpinner.setEnabled(true);
                }
                else
                {
                    latestTimingSpinner.setEnabled(false);
                }
            }
        });
        // -----------------------------------------------------------------------------------------------------

        // Earliest timing notification setting spinner ---------------------------------------------------------------
        earliestTimingSpinner.setSelection(firstTimingNotifSetting);
        earliestTimingSpinner.setEnabled(firstTimingNotifEnabled);
        earliestTimingSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                firstTimingNotifSetting = pos;
                editor.putInt("firstTimingNotifSetting", firstTimingNotifSetting);
                Log.d("myLog", "firstTimingNotifSetting: " + firstTimingNotifSetting);
                editor.commit();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing happens
            }
        });
        // -----------------------------------------------------------------------------------------------------

        // Earliest timing notification setting spinner ---------------------------------------------------------------
        latestTimingSpinner.setSelection(secondTimingNotifSetting);
        latestTimingSpinner.setEnabled(secondTimingNotifEnabled);
        latestTimingSpinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                secondTimingNotifSetting = pos;
                editor.putInt("secondTimingNotifSetting", secondTimingNotifSetting);
                Log.d("myLog", "secondTimingNotifSetting: " + secondTimingNotifSetting);
                editor.commit();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                // Nothing happens
            }
        });
        // -----------------------------------------------------------------------------------------------------

        // Enable second dose reminders checkbox ---------------------------------------------------------------
        reminderEnableCheckbox.setChecked(notifsEnabled);
        reminderEnableCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                notifsEnabled = isChecked;
                editor.putBoolean("notifsEnabled", notifsEnabled);
                Log.d("myLog", "notifications: " + isChecked);
                editor.commit();

                if(notifsEnabled)
                {
                    earliestTimingCheckbox.setEnabled(true);
                    latestTimingCheckbox.setEnabled(true);
                }
                else
                {
                    earliestTimingCheckbox.setEnabled(false);
                    earliestTimingCheckbox.setChecked(false);
                    latestTimingCheckbox.setEnabled(false);
                    latestTimingCheckbox.setChecked(false);
                    earliestTimingSpinner.setEnabled(false);
                    latestTimingSpinner.setEnabled(false);
                }

            }
        });
        // -----------------------------------------------------------------------------------------------------




        return view;
    }
}