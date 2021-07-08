package com.cs446.covidsafe.ui.Covid.CovidStats;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.model.ProvinceData;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidStatsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private CovidStatsViewModel viewModel;

    @BindView(R.id.data_type_spinner)
    Spinner mSpinner;

    public CovidStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CovidStatsViewModel.class);
        viewModel.init();
        viewModel.getResponseData().observe(this, new Observer<Map<String, Map<String, ProvinceData>>>() {
            @Override
            public void onChanged(Map<String, Map<String, ProvinceData>> countries) {
                if (countries != null) {

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_stats, container, false);
        ButterKnife.bind(this, view);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.covid_data_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setSelection(0);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        viewModel.onDataTypeSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }
}