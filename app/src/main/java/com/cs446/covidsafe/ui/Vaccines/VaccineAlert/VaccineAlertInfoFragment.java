package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

        day = sharedPreferences.getInt("vaccDay", 0);
        month = sharedPreferences.getInt("vaccMonth", 0);
        year = sharedPreferences.getInt("vaccYear", 0);

        location = sharedPreferences.getString("vaccLocation", "null");
        type = sharedPreferences.getString("vaccType", "null");

        String monthStr = new DateFormatSymbols().getMonths()[month-1];
        TextView vaccineAlertInfo_firstDoseData = (TextView) view.findViewById(R.id.vaccineAlertInfo_firstDoseData);
        vaccineAlertInfo_firstDoseData.setText("You received your first dose of the " + type + " vaccine on " + day + " " + monthStr + ", " + year + " at " + location + ".");

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
            secondDoseData = "The earliest recommended timing for your second dose is on " + secondDate + " " + secondMonthStr + " " + secondYear + ".";
        }
        // Pfizer
        else if(type.equals(getResources().getStringArray(R.array.vaccine_types)[1]))
        {
            c.add(Calendar.DATE, 21);
            secondDate = c.get(Calendar.DAY_OF_MONTH);
            secondMonth = c.get(Calendar.MONTH) + 1;
            secondYear = c.get(Calendar.YEAR);

            String secondMonthStr = new DateFormatSymbols().getMonths()[secondMonth-1];
            secondDoseData = "The earliest recommended timing for your second dose is on " + secondDate + " " + secondMonthStr + " " + secondYear + ".";
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
            secondDoseData = "The earliest recommended timing for your second dose is on " + secondDate + " " + secondMonthStr + " " + secondYear + ". The last possible recommended timing for your second dose is on " + secondFinalDate + " " + secondFinalMonthStr + " " + secondFinalYear + " .";
        }
        // Janssen
        else if(type.equals(getResources().getStringArray(R.array.vaccine_types)[3]))
        {
            secondDoseData = "A second shot is not necessary for the Janssen vaccine.";
        }

        TextView vaccineAlertInfo_secondDoseData = (TextView) view.findViewById(R.id.vaccineAlertInfo_secondDoseData);
        vaccineAlertInfo_secondDoseData.setText(secondDoseData);

        return view;
    }
}