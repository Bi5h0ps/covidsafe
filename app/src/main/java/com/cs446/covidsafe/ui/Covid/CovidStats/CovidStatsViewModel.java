package com.cs446.covidsafe.ui.Covid.CovidStats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cs446.covidsafe.model.ProvinceData;
import com.cs446.covidsafe.repository.CovidCasesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CovidStatsViewModel extends AndroidViewModel {

    private LiveData<Map<String, Map<String, ProvinceData>>> data;
    private LiveData<Map<String, Long>> statsData;
    private static final String[] DATA_TYPES = {"deaths", "confirmed", "recovered"};

    private HashMap<String, ArrayList<String>> countryList;

    private ArrayList<String> provinceList;

    private String mCountry = "";
    private String mDataType = "";
    private String mProvince = "All";

    CovidCasesRepository mCovidCasesRepo = null;


    public CovidStatsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        countryList = new HashMap<>();
        provinceList = new ArrayList<>();
        mCovidCasesRepo = new CovidCasesRepository();
        data = mCovidCasesRepo.getCovidCasesResponseLiveData();
        statsData = mCovidCasesRepo.getCovidHistoryResponseLiveData();
        mCovidCasesRepo.getCovidCases(null);
    }

    public LiveData<Map<String, Map<String, ProvinceData>>> getResponseData() {
        return data;
    }

    public LiveData<Map<String, Long>> getHistoryStatsData() {
        return statsData;
    }

    public void onDataTypeSelected(int position) {
        mDataType = DATA_TYPES[position];
    }

    public void requestHistoryData() {
        mCovidCasesRepo.getCovidHistory(mDataType, mCountry, mProvince);
    };


    public void setCountriesAndProvinces() {
        if(data != null && data.getValue().size() != 0) {
            Map<String, Map<String, ProvinceData>> caseResponse = data.getValue();
            for (String country : caseResponse.keySet()) {
                ArrayList<String> provinces = new ArrayList<>(Objects.requireNonNull(caseResponse.get(country)).keySet());
                countryList.put(country, provinces);
            }
        }
    }

    public HashMap<String, ArrayList<String>> getCountryList() {
        return countryList;
    }

    public ArrayList<String> getProvinceList() {
        return provinceList;
    }

    public String getCountry() {
        return mCountry;
    }

    public String getProvince() {
        return mProvince;
    }

    public void onCountrySelected(int which) {
        mCountry = countryList.keySet().toArray(new String[0])[which];
        mProvince = "All";
        provinceList = countryList.get(mCountry);
    }

    public void onProvinceSelected(int which) {
        mProvince = provinceList.get(which);
    }

    public boolean isCountrySelected() {
        return !mCountry.isEmpty();
    }
}
