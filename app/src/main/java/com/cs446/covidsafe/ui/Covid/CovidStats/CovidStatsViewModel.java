package com.cs446.covidsafe.ui.Covid.CovidStats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs446.covidsafe.model.ProvinceData;
import com.cs446.covidsafe.repository.CovidCasesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CovidStatsViewModel extends AndroidViewModel {

    private LiveData<Map<String, Map<String, ProvinceData>>> data;
    private static final String[] DATA_TYPES = {"deaths", "confirmed", "recovered"};

    private HashMap<String, ArrayList<String>> countryList;

    private String mCountry = "";
    private String mDataType = "";
    private String mProvince = "";


    public CovidStatsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        countryList = new HashMap<>();
        CovidCasesRepository mCovidCasesRepo = new CovidCasesRepository();
        data = mCovidCasesRepo.getCovidCasesResponseLiveData();
        mCovidCasesRepo.getCovidCases(null);
    }

    public LiveData<Map<String, Map<String, ProvinceData>>> getResponseData() {
        return data;
    }

    public void onDataTypeSelected(int position) {
        mDataType = DATA_TYPES[position];
    }


    public void setCountriesAndProvinces() {
        if(data != null && data.getValue().size() != 0) {
            Map<String, Map<String, ProvinceData>> caseResponse = data.getValue();
            for (String country : caseResponse.keySet()) {
                ArrayList<String> provinces = new ArrayList<>(Objects.requireNonNull(caseResponse.get(country)).keySet());
                countryList.put(country, provinces);
            }
        }
    }
}
