package com.cs446.covidsafe.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProvinceData {
    @SerializedName("confirmed")
    @Expose
    private Long confirmed;

    @SerializedName("recovered")
    @Expose
    private Long recovered;

    @SerializedName("deaths")
    @Expose
    private Long deaths;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("population")
    @Expose
    private Long population;

    @SerializedName("sq_km_area")
    @Expose(deserialize = false)
    private Float area;

    @SerializedName("life_expectancy")
    @Expose
    private String lifeExpectancy;

    @SerializedName("elevation_in_meters")
    @Expose
    private String elevation;

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

    @SerializedName("lat")
    @Expose
    private String latitude;

    @SerializedName("long")
    @Expose
    private String longitude;

    @SerializedName("updated")
    @Expose
    private String timeStamp;

    public Long getConfirmed() {
        return confirmed;
    }

    public Long getRecovered() {
        return recovered;
    }

    public Long getDeaths() {
        return deaths;
    }

    public String getCountry() {
        return country;
    }

    public Long getPopulation() {
        return population;
    }

    public Float getArea() {
        return area;
    }

    public String getLifeExpectancy() {
        return lifeExpectancy;
    }

    public String getElevation() {
        return elevation;
    }

    public String getContinent() {
        return continent;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getLocation() {
        return location;
    }

    public Long getIso() {
        return iso;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
