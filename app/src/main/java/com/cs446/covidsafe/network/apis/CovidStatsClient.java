package com.cs446.covidsafe.network.apis;

import com.cs446.covidsafe.model.ProvinceData;
import com.cs446.covidsafe.model.ProvinceHistoryData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CovidStatsClient {

    @GET("cases")
    Call<Map<String, Map<String, ProvinceData>>> getCases(
            @Query("country") String country
    );

    @GET("history")
    Call<Map<String, ProvinceHistoryData>> getHistory(
            @Query("status") String status,
            @Query("country") String country
    );
}
