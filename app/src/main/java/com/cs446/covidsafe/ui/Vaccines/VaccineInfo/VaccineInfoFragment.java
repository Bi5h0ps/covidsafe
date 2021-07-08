package com.cs446.covidsafe.ui.Vaccines.VaccineInfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs446.covidsafe.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VaccineInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccineInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String types[], desc[];
    RecyclerView info_recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VaccineInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VaccineInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VaccineInfoFragment newInstance(String param1, String param2) {
        VaccineInfoFragment fragment = new VaccineInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_vaccine_info, container, false);
        // Inflate the layout for this fragment
        types = getResources().getStringArray(R.array.vaccine_types);
        desc = getResources().getStringArray(R.array.vaccine_desc);
        info_recyclerView = view.findViewById(R.id.vaccine_info_recyclerView);
        vaccine_info_adapter adaptor = new vaccine_info_adapter(getContext(), types, desc);
        info_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        info_recyclerView.setAdapter(adaptor);
        return view;
    }
}