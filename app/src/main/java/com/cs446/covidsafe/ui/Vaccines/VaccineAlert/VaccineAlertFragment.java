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

import java.util.Map;

public class VaccineAlertFragment extends Fragment {

    // the alerts page
    VaccineAlertInfoFragment alertsPage = new VaccineAlertInfoFragment();
    // the other page
    VaccineAlertInputFragment infoPage = new VaccineAlertInputFragment();

    private void childFragmentSelector()
    {

        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // retrieve shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences= getActivity().getSharedPreferences("vaccInfo", Context.MODE_PRIVATE);
//        Log.d("myLog", sharedPreferences.toString());

        Map<String,?> keys = sharedPreferences.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Log.d("myLog",entry.getKey() + ": " +
                    entry.getValue().toString());
        }

        // if user has already been vaccinated
        Boolean isVaccinated = sharedPreferences.getBoolean("isVaccinated", false);

        if(fragmentManager.findFragmentByTag("alertsPage") != null)
        {
            fragmentTransaction.remove(alertsPage);
        }
        else if(fragmentManager.findFragmentByTag("infoPage") != null)
        {
            fragmentTransaction.remove(infoPage);
        }

        if(isVaccinated)
        {
            fragmentTransaction.add(R.id.alertFragmentContainerView, alertsPage, "alertsPage");
        }
        else
        {
            fragmentTransaction.add(R.id.alertFragmentContainerView, infoPage, "infoPage");
        }
        fragmentTransaction.commit();

    }


    public static VaccineAlertFragment newInstance() {
        return new VaccineAlertFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        childFragmentSelector();
        return inflater.inflate(R.layout.vaccine_alert_fragment, container, false);
    }

    @Override
    public void onResume () {
        Log.d("myLog", "do we resume?");
        childFragmentSelector();
        super.onResume();
    }



//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(VaccineAlertViewModel.class);
//        // TODO: Use the ViewModel
//    }

}