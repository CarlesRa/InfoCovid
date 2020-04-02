package com.carlesramos.infocovid.model;

public class CountryInfo {
    private String name;
    private int cases;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private int active;
    private int critical;
    private int casesPerMillion;
    private int deathsPerMillion;

    public CountryInfo(String name, int cases, int todayCases, int deaths, int todayDeaths,
                       int recovered, int active, int critical, int casesPerMillion,
                       int deathsPerMillion) {
        this.name = name;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.casesPerMillion = casesPerMillion;
        this.deathsPerMillion = deathsPerMillion;
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

    public int getCasesPerMillion() {
        return casesPerMillion;
    }

    public int getDeathsPerMillion() {
        return deathsPerMillion;
    }
}
