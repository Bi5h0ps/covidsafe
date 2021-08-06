package com.cs446.covidsafe.ui.Covid.CovidStats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.broooapps.graphview.CurveGraphConfig;
import com.broooapps.graphview.CurveGraphView;
import com.broooapps.graphview.models.GraphData;
import com.broooapps.graphview.models.PointMap;
import com.cs446.covidsafe.R;
import com.cs446.covidsafe.model.ProvinceData;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidStatsFragment extends Fragment {

    private CovidStatsViewModel viewModel;
    private NavController mNavController;

    @BindView(R.id.data_type_spinner)
    Spinner mSpinner;

    @BindView(R.id.country_picker)
    EditText mEditTextCountryPicker;

    @BindView(R.id.province_picker)
    EditText mEditTextProvincePicker;

    @BindView(R.id.search_button)
    Button mSearchButton;

    @BindView(R.id.line_chart)
    LineChart mLineChart;

    public CovidStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(CovidStatsViewModel.class);
        viewModel.init();
        viewModel.getResponseData().observe(this, countries -> {
            if (countries != null) {
                viewModel.setCountriesAndProvinces();
            }
        });
        viewModel.getHistoryStatsData().observe(this, stats -> {
            if (stats != null) {
                ArrayList<Pair<String, Long>> sortedList = new ArrayList<>();
                for (String key : stats.keySet()) {
                    sortedList.add(new Pair<>(key, stats.get(key)));
                }
                Collections.sort(sortedList, (p1, p2) ->
                        p1.first.compareTo(p2.first));

                int index = 0;
                ArrayList<Entry> values = new ArrayList<>();
                for(Pair<String, Long> p : sortedList) {
                    values.add(new Entry(index, p.second.intValue()));
                    index++;
                }
                LineDataSet lineDataSet = new LineDataSet(values, "Data set 1");
                lineDataSet.setFillAlpha(110);
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(lineDataSet);
                LineData data = new LineData(dataSets);
                mLineChart.setData(data);
                mLineChart.invalidate();
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

        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(false);

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
        mEditTextCountryPicker.setOnClickListener(v -> {
            builderSingle.setTitle("Select a Country");
            builderSingle.setItems(
                    viewModel.getCountryList().keySet().toArray(new String[0]),
                    (dialog, which) -> {
                viewModel.onCountrySelected(which);
                mEditTextCountryPicker.setText(viewModel.getCountry());
                mEditTextProvincePicker.setText(viewModel.getProvince());
            });
            AlertDialog dialog = builderSingle.create();
            dialog.show();
        });

        mEditTextProvincePicker.setOnClickListener(v -> {
            builderSingle.setTitle("Select a Province");
            builderSingle.setItems(
                    viewModel.getProvinceList().toArray(new String[0]),
                    (dialog, which) -> {
                        viewModel.onProvinceSelected(which);
                        mEditTextProvincePicker.setText(viewModel.getProvince());
                    });
            AlertDialog dialog = builderSingle.create();
            dialog.show();
        });

        mSearchButton.setOnClickListener(v -> {
            //make a request
            if(!viewModel.isCountrySelected()) {
                Toast.makeText(requireContext(),
                        "Please select a country", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.requestHistoryData();
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.onDataTypeSelected(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        // Inflate the layout for this fragment
        return view;
    }
}
