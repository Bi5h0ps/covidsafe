package com.cs446.covidsafe.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ProvinceHistoryData {
    @SerializedName("country")
    @Expose(deserialize = false)
    private String country;

    @SerializedName("population")
    @Expose(deserialize = false)
    private Long population;

    @SerializedName("sq_km_area")
    @Expose(deserialize = false)
    private Long area;

    @SerializedName("life_expectancy")
    @Expose(deserialize = false)
    private String lifeExpectancy;

    @SerializedName("elevation_in_meters")
    @Expose(deserialize = false)
    private String elevation;

    @SerializedName("continent")
    @Expose(deserialize = false)
    private String continent;

    @SerializedName("abbreviation")
    @Expose(deserialize = false)
    private String abbreviation;

    @SerializedName("location")
    @Expose(deserialize = false)
    private String location;

    @SerializedName("iso")
    @Expose(deserialize = false)
    private Long iso;

    @SerializedName("capital_city")
    @Expose(deserialize = false)
    private String capitalCity;

    @SerializedName("dates")
    @Expose
    private Map<String, Long> data;

    public Map<String, Long> getData() {
        return data;
    }
}