package com.cs446.covidsafe.ui.Covid.CovidStats.CountryPicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CountryPickerViewModel extends ViewModel {

    private MutableLiveData<String[]> mCountries;

    public LiveData<String[]> getCountries() {
        return mCountries;
    }

    public void init(String[] countries) {
        mCountries.postValue(countries);
    }
}