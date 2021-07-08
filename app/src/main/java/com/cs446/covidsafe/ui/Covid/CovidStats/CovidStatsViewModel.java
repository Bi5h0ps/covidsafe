package com.cs446.covidsafe.ui.Covid.CovidStats;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs446.covidsafe.model.ProvinceData;
import com.cs446.covidsafe.repository.CovidCasesRepository;

import java.util.ArrayList;
import java.util.Map;

public class CovidStatsViewModel extends AndroidViewModel {

    private LiveData<Map<String, Map<String, ProvinceData>>> data;
    private static final String[] DATA_TYPES = {"deaths", "confirmed", "recovered"};

    private String mCountry = "";
    private String mDataType = "";
    private String mProvince = "";


    public CovidStatsViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
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


}
