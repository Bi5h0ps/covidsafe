package com.cs446.covidsafe.ui.Covid.CovidUpdates;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.model.ProvinceData;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidUpdatesFragment extends Fragment {

    private CovidUpdatesViewModel viewModel;

    @BindView(R.id.DeathsCase)
    TextView deathsCase;

    @BindView(R.id.ConfirmedCase)
    TextView confirmedCase;

    @BindView(R.id.RecoveredCase)
    TextView recoveredCase;

    @BindView(R.id.chart)
    PieChart chart;

    public CovidUpdatesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CovidUpdatesViewModel.class);
        viewModel.init();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_covid_updates, container, false);
        ButterKnife.bind(this, view);
        viewModel.getResponseData().observe(getViewLifecycleOwner(), new Observer<Map<String, Map<String, ProvinceData>>>() {
            @Override
            public void onChanged(Map<String, Map<String, ProvinceData>> countries) {
                if (countries != null) {
                    deathsCase.setText(Long.toString(countries.get("Canada").get("All").getDeaths()));
                    confirmedCase.setText(Long.toString(countries.get("Canada").get("All").getConfirmed()));
                    recoveredCase.setText(Long.toString(countries.get("Canada").get("All").getRecovered()));

                    List<PieEntry> entries = new ArrayList<>();
                    entries.add(new PieEntry(countries.get("Canada").get("All").getDeaths(), "deaths"));
                    entries.add(new PieEntry(countries.get("Canada").get("All").getConfirmed(), "confirmed"));
                    entries.add(new PieEntry(countries.get("Canada").get("All").getRecovered(), "recovered"));
                    PieDataSet set = new PieDataSet(entries, "");
                    set.setColors(new int[] { getResources().getColor(R.color.red), getResources().getColor(R.color.blue), getResources().getColor(R.color.green)});
                    PieData data = new PieData(set);
                    chart.setData(data);
                    chart.invalidate();
                }
            }
        });
        return view;
    }
}