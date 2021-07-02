package com.cs446.covidsafe.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CovidCasesResponse {
    @Expose
    private Map<String, Map<String, ProvinceData>> Countries;
}