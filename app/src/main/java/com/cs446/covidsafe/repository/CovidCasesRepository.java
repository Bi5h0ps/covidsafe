package com.cs446.covidsafe.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs446.covidsafe.model.ProvinceData;
import com.cs446.covidsafe.network.apis.CovidStatsClient;


import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidCasesRepository {
    private static final String BASE_URL = "https://covid-api.mmediagroup.fr/v1/";

    private final CovidStatsClient covidStatsClient;
    private MutableLiveData<Map<String, Map<String, ProvinceData>>> covidCasesResponseLiveData;
    private MutableLiveData<Map<String, Map<String, ProvinceData>>> covidHistoryResponseLiveData;

    public CovidCasesRepository() {
        covidCasesResponseLiveData = new MutableLiveData<>();
        covidHistoryResponseLiveData = new MutableLiveData<>();

        OkHttpClient client = new OkHttpClient.Builder().build();

        covidStatsClient = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CovidStatsClient.class);
    }

    public void getCovidCases(@Nullable String country) {
        covidStatsClient.getCases(country)
                .enqueue(new Callback<Map<String, Map<String, ProvinceData>>>() {

                    @Override
                    public void onResponse(Call<Map<String, Map<String, ProvinceData>>> call, Response<Map<String, Map<String, ProvinceData>>> response) {
                        if (response.body() != null) {
                            covidCasesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Map<String, ProvinceData>>> call, Throwable t) {
                        covidCasesResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<Map<String, Map<String, ProvinceData>>> getCovidCasesResponseLiveData() {
        return covidCasesResponseLiveData;
    }

    public void getCovidHistory(String status, @Nullable String country) {
        covidStatsClient.getHistory(status, country)
                .enqueue(new Callback<Map<String, Map<String, ProvinceData>>>() {

                    @Override
                    public void onResponse(Call<Map<String, Map<String, ProvinceData>>> call, Response<Map<String, Map<String, ProvinceData>>> response) {
                        if (response.body() != null) {
                            covidHistoryResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, Map<String, ProvinceData>>> call, Throwable t) {
                        covidHistoryResponseLiveData.postValue(null);
                    }

                });
    }

    public LiveData<Map<String, Map<String, ProvinceData>>> getCovidHistoryResponseLiveData() {
        return covidHistoryResponseLiveData;
    }
}
