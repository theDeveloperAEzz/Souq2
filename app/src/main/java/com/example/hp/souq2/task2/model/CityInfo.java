package com.example.hp.souq2.task2.model;

public class CityInfo {
    private String id;
    private String titleEN;
    private String titleAR;
    private String countryId;

    public CityInfo(String id, String titleEN, String titleAR, String countryId) {
        this.id = id;
        this.titleEN = titleEN;
        this.titleAR = titleAR;
        this.countryId = countryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public String getTitleAR() {
        return titleAR;
    }

    public void setTitleAR(String titleAR) {
        this.titleAR = titleAR;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}
