package com.cs446.covidsafe.ui.Vaccines.VaccineAlert;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.ui.Vaccines.VaccineInfo.vaccine_info_adapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VaccineAlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccineAlertFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String location;
    private String time; // this should probably be converted to something using the proper time class afterwards

    private String[] reminderSettings = {
            "Notification 1 week, 3 days, and 1 day prior",
            "Notification 3 days and 1 day prior",
            "Notification 1 day prior"
    };

    public VaccineAlertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VaccineAlertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VaccineAlertFragment newInstance(String param1, String param2) {
        VaccineAlertFragment fragment = new VaccineAlertFragment();
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
        View view = inflater.inflate(R.layout.fragment_vaccine_alert, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}