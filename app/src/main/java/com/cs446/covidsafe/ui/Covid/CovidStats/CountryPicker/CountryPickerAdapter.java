package com.cs446.covidsafe.ui.Covid.CovidStats.CountryPicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cs446.covidsafe.R;
import com.cs446.covidsafe.databinding.VhCountryPickerBinding;

import java.util.Arrays;
import java.util.List;

public class CountryPickerAdapter extends RecyclerView.Adapter<CountryPickerAdapter.CountryVH> {
    private static final String TAG = "CountryPickerAdapter";
    private final List<String> countryList;
    private final CountrySelectionListener mCallback;

    public CountryPickerAdapter(String[] countries, CountrySelectionListener callback) {
        countryList = Arrays.asList(countries);
        mCallback = callback;

    }


    @NonNull
    @Override
    public CountryPickerAdapter.CountryVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryVH(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.vh_country_picker, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryPickerAdapter.CountryVH holder, int position) {
        String countryName = countryList.get(position);
        holder.binding.setCountry(countryName);
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onCountrySelected(countryName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    static class CountryVH extends RecyclerView.ViewHolder {
        private static final String TAG = "CountryVH";

        VhCountryPickerBinding binding;

        public CountryVH(VhCountryPickerBinding vhCountryPickerBinding) {
            super(vhCountryPickerBinding.getRoot());
            binding = vhCountryPickerBinding;
        }
    }
}
