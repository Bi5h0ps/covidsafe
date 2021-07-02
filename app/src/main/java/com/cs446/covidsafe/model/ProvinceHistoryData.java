package com.cs446.covidsafe.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ProvinceHistoryData {
    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("population")
    @Expose
    private Long population;

    @SerializedName("sq_km_area")
    @Expose
    private Long area;

    @SerializedName("life_expectancy")
    @Expose
    private String lifeExpectancy;

    @SerializedName("elevation_in_meters")
    @Expose
    private Long elevation;

    @SerializedName("continent")
    @Expose
    private String continent;

    @SerializedName("abbreviation")
    @Expose
    private String abbreviation;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("iso")
    @Expose
    private Long iso;

    @SerializedName("capital_city")
    @Expose
    private String capitalCity;

    @SerializedName("dates")
    @Expose
    private Map<String, Long> data;
}