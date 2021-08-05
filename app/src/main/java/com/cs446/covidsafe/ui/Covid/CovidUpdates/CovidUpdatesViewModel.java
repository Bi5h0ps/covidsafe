package com.cs446.covidsafe.ui.Covid.CovidUpdates;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cs446.covidsafe.repository.CovidCasesRepository;

import java.util.Map;

public class CovidUpdatesViewModel extends AndroidViewModel {


    private LiveData<Map<String, Long>> data;

    private CovidCasesRepository DeathsDataCovidCasesRepo;
    private CovidCasesRepository ConfirmedDataCovidCasesRepo;
    private CovidCasesRepository RecoveredDataCovidCasesRepo;


    public CovidUpdatesViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        DeathsDataCovidCasesRepo = new CovidCasesRepository();
        ConfirmedDataCovidCasesRepo = new CovidCasesRepository();
        RecoveredDataCovidCasesRepo = new CovidCasesRepository();
    }

    public LiveData<Map<String, Long>> getResponseData(String status) {
        switch (status) {
            case "Deaths":
                DeathsDataCovidCasesRepo.getCovidHistory(status, "Canada", "All");
                data = DeathsDataCovidCasesRepo.getCovidHistoryResponseLiveData();
                break;
            case "Confirmed":
                ConfirmedDataCovidCasesRepo.getCovidHistory(status, "Canada", "All");
                data = ConfirmedDataCovidCasesRepo.getCovidHistoryResponseLiveData();
                break;
            case "Recovered":
                RecoveredDataCovidCasesRepo.getCovidHistory(status, "Canada", "All");
                data = RecoveredDataCovidCasesRepo.getCovidHistoryResponseLiveData();
                break;
        }
        return data;
    }

}
