package com.cs446.covidsafe.repository;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cs446.covidsafe.model.CovidCasesResponse;
import com.cs446.covidsafe.model.CovidHistoryResponse;
import com.cs446.covidsafe.network.apis.CovidStatsClient;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidCasesRepository {
    private static final String BASE_URL = "https://covid-api.mmediagroup.fr/v1";

    private CovidStatsClient covidStatsClient;
    private MutableLiveData<CovidCasesResponse> covidCasesResponseLiveData;
    private MutableLiveData<CovidHistoryResponse> covidHistoryResponseLiveData;

    public CovidCasesRepository() {
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
                .enqueue(new Callback<CovidCasesResponse>() {

                    @Override
                    public void onResponse(Call<CovidCasesResponse> call, Response<CovidCasesResponse> response) {
                        if (response.body() != null) {
                            covidCasesResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CovidCasesResponse> call, Throwable t) {
                        covidCasesResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<CovidCasesResponse> getCovidCasesResponseLiveData() {
        return covidCasesResponseLiveData;
    }

    public void getCovidHistory(String status, @Nullable String country) {
        covidStatsClient.getHistory(status, country)
                .enqueue(new Callback<CovidHistoryResponse>() {

                    @Override
                    public void onResponse(Call<CovidHistoryResponse> call, Response<CovidHistoryResponse> response) {
                        if (response.body() != null) {
                            covidHistoryResponseLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<CovidHistoryResponse> call, Throwable t) {
                        covidHistoryResponseLiveData.postValue(null);
                    }
                });
    }

    public LiveData<CovidHistoryResponse> getCovidHistoryResponseLiveData() {
        return covidHistoryResponseLiveData;
    }
}
