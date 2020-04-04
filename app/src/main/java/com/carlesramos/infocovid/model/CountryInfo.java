package com.carlesramos.infocovid.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryInfo implements Serializable {

    @SerializedName("country")
    private String name;
    @SerializedName("cases")
    private int cases;
    @SerializedName("todayCases")
    private int todayCases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("todayDeaths")
    private int todayDeaths;
    @SerializedName("recovered")
    private int recovered;
    @SerializedName("active")
    private int active;
    @SerializedName("critical")
    private int critical;
    @SerializedName("casesPerOneMillion")
    private int casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    private int deathsPerOneMillion;

    public CountryInfo(String name, int cases, int todayCases, int deaths, int todayDeaths,
                       int recovered, int active, int critical, int casesPerOneMillion,
                       int deathsPerOneMillion) {
        this.name = name;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
    }

    public String getName() {
        return name;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getActive() {
        return active;
    }

    public int getCritical() {
        return critical;
    }

    public int getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public int getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    @Override
    public String toString() {
        return "CountryInfo{" +
                "name='" + name + '\'' +
                ", cases=" + cases +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", critical=" + critical +
                ", casesPerOneMillion=" + casesPerOneMillion +
                ", deathsPerOneMillion=" + deathsPerOneMillion +
                '}';
    }
}
