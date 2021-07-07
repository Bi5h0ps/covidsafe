package com.cs446.covidsafe.ui.Covid.CovidStats.CountryPicker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cs446.covidsafe.R;

import java.util.ArrayList;
import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CountryPickerFragment extends Fragment implements CountrySelectionListener{

    private CountryPickerViewModel mViewModel;

    @BindView(R.id.country_recycler_view)
    RecyclerView mRecyclerView;

    public static CountryPickerFragment newInstance() {
        return new CountryPickerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.country_picker_fragment, container, false);
        ButterKnife.bind(this, view);

        //get arguments
        Bundle bundle = getArguments();
        String[] countries = CountryPickerFragmentArgs.fromBundle(bundle).getCountries();
        mViewModel = ViewModelProviders.of(this).get(CountryPickerViewModel.class);
        mViewModel.init(countries);
        mViewModel.getCountries().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] countries) {
                initRecyclerView(countries);
            }
        });
        return view;
    }

    private void initRecyclerView(String[] countries) {
        CountryPickerAdapter countryPickerAdapter = new CountryPickerAdapter(countries, this);
        mRecyclerView.setAdapter(countryPickerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onCountrySelected(String countryName) {
        Toast.makeText(getContext(), countryName, Toast.LENGTH_SHORT).show();
    }
}

interface CountrySelectionListener {
    public abstract void onCountrySelected(String countryName);
}