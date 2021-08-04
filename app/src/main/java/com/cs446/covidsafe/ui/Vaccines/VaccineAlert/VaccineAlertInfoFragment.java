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
import android.widget.EditText;
import android.widget.Spinner;

import com.cs446.covidsafe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VaccineAlertInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccineAlertInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VaccineAlertInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VaccineAlertInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VaccineAlertInfoFragment newInstance(String param1, String param2) {
        VaccineAlertInfoFragment fragment = new VaccineAlertInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_vaccine_alert_info, container, false);

        Button button = (Button) view.findViewById(R.id.VaccineAlertInfo_AddFirstDoseButton);

        EditText time = (EditText) view.findViewById(R.id.VaccineAlertInfo_FirstDoseTime);
        EditText date = (EditText) view.findViewById(R.id.VaccineAlertInfo_FirstDoseDate);
        EditText location = (EditText) view.findViewById(R.id.VaccineAlertInfo_FirstDoseLocation);
        Spinner type = (Spinner) view.findViewById(R.id.VaccineAlertInfo_FirstDoseType);

//        time.setVisibility(View.GONE);
//        date.setVisibility(View.GONE);
//        location.setVisibility(View.GONE);
//        type.setVisibility(View.GONE);


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View vm) {

                // retrieve shared preferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("vaccInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("vaccTime", String.valueOf(time.getText()));
                editor.putString("vaccDate", String.valueOf(date.getText()));
                editor.putString("vaccLocation", String.valueOf(location.getText()));
                editor.putString("vaccType", type.getSelectedItem().toString());
                editor.putBoolean("isVaccinated", true);
                editor.commit();

                // Call the alerts page
                VaccineAlertFragmentOld alertsPage = new VaccineAlertFragmentOld();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.alertFragmentContainerView, alertsPage  );
                fragmentTransaction.commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}