package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.cs446.covidsafe.R;

public class VaccineAlertInputFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void closeInputFragment()
    {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(this);
        Log.d("myLog", "removing current fragment");

        VaccineAlertInfoFragment alertsPage = new VaccineAlertInfoFragment();
        fragmentTransaction.add(R.id.alertFragmentContainerView, alertsPage  );
        Log.d("myLog", "added info fragment");

        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vaccine_alert_input, container, false);

        Button button = (Button) view.findViewById(R.id.VaccineAlertInfo_AddFirstDoseButton);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.VaccineAlertInfo_FirstDoseDate);
        EditText location = (EditText) view.findViewById(R.id.VaccineAlertInfo_FirstDoseLocation);
        Spinner type = (Spinner) view.findViewById(R.id.VaccineAlertInfo_FirstDoseType);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vm) {

                // retrieve shared preferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("vaccInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("vaccMonth", datePicker.getMonth());
                editor.putInt("vaccDay", datePicker.getDayOfMonth());
                editor.putInt("vaccYear", datePicker.getYear());
                editor.putString("vaccLocation", String.valueOf(location.getText()));
                editor.putString("vaccType", type.getSelectedItem().toString());
                editor.putBoolean("isVaccinated", true);
                editor.commit();

                closeInputFragment();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}