package com.cs446.covidsafe.ui.Covid.CovidStats;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.model.ProvinceData;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CovidStatsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private CovidStatsViewModel viewModel;
    private NavController mNavController;

    @BindView(R.id.data_type_spinner)
    Spinner mSpinner;

    @BindView(R.id.country_picker)
    EditText mEditTextCountryPicker;

    @BindView(R.id.province_picker)
    EditText mEditTextProvincePicker;

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
                    viewModel.setCountriesAndProvinces();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_stats, container, false);
        getChildFragmentManager().setFragmentResultListener("country_selected",
                getViewLifecycleOwner(),
                (requestKey, result) -> {
                    String countryName = result.getString("country_name");
                    Toast.makeText(getContext(), countryName, Toast.LENGTH_SHORT).show();
                }) ;
        ButterKnife.bind(this, view);
/*
        NavHostFragment fragment =
                (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.covidStatsFragment);
        assert fragment != null;
        mNavController = fragment.getNavController();*/

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.covid_data_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEditTextCountryPicker.
                setOnClickListener(v ->
                        mNavController.navigate(R.id.action_covidStatsFragment_to_countryPickerFragment));
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
