package com.carlesramos.infocovid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AllCountriesInfo implements Serializable {

    @SerializedName("cases")
    private int cases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("recovered")
    private int recovered;

    public AllCountriesInfo(int cases, int deaths, int recovered) {
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }
}
