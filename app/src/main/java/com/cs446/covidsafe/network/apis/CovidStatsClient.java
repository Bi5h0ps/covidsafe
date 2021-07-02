package com.cs446.covidsafe.network.apis;

import com.cs446.covidsafe.model.CovidCasesResponse;
import com.cs446.covidsafe.model.CovidHistoryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidStatsClient {

    @GET("/cases")
    Call<CovidCasesResponse> getCases(
            @Query("country") String country
    );

    @GET("/history")
    Call<CovidHistoryResponse> getHistory(
            @Query("status") String status,
            @Query("country") String country
    );
}
