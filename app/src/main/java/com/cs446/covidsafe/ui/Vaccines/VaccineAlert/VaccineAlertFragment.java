package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs446.covidsafe.R;

public class VaccineAlertFragment extends Fragment {

    private VaccineAlertViewModel mViewModel;



    public static VaccineAlertFragment newInstance() {
        return new VaccineAlertFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // retrieve shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("vaccInfo", Context.MODE_PRIVATE);

        // if user has already been vaccinated
        Boolean isVaccinated = sharedPreferences.getBoolean("isVaccinated", false);
        if(isVaccinated)
        {
            // Call the alerts page
            VaccineAlertFragmentOld alertsPage = new VaccineAlertFragmentOld();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.alertFragmentContainerView, alertsPage  );
            fragmentTransaction.commit();
        }
        else
        {
            // Call the other page
            VaccineAlertInfoFragment alertsPage = new VaccineAlertInfoFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.alertFragmentContainerView, alertsPage  );
            fragmentTransaction.commit();
        }

        return inflater.inflate(R.layout.vaccine_alert_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(VaccineAlertViewModel.class);
        // TODO: Use the ViewModel
    }

}