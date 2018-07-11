package com.example.hp.souq2.task2.model;

public class CountryInfo {
    private String id;
    private String titleEN;
    private String titleAR;
    private String currencyId;
    private String currencyEN;
    private String currencyAR;
    private String codeEN;
    private String codeAR;
    private String code;

    public CountryInfo(String id, String titleEN, String titleAR, String currencyId, String currencyEN, String currencyAR,
                       String codeEN, String codeAR, String code) {
        this.id = id;
        this.titleEN = titleEN;
        this.titleAR = titleAR;
        this.currencyId = currencyId;
        this.currencyEN = currencyEN;
        this.currencyAR = currencyAR;
        this.codeEN = codeEN;
        this.codeAR = codeAR;
        this.code = code;
    }

    public CountryInfo(String id, String titleEN, String titleAR, String currencyId) {
        this.id = id;
        this.titleEN = titleEN;
        this.titleAR = titleAR;
        this.currencyId = currencyId;
    }

    private String s;

    public String getVariableName(CountryInfo countryInfo ,String variable) {
        switch (variable) {
            case "id":
                s = countryInfo.getId();
                break;
            case "titleEN":
                s = countryInfo.getTitleEN();
                break;
            case "titleAR":
                s = countryInfo.getTitleAR();
                break;
            case "currencyId":
                s = countryInfo.getCurrencyId();
                break;
            case "currencyEN":
                s = countryInfo.getCurrencyEN();
                break;
            case "currencyAR":
                s = countryInfo.getCurrencyAR();
                break;
            case "codeEN":
                s = countryInfo.getCodeEN();
                break;
            case "codeAR":
                s = countryInfo.getCodeAR();
                break;
            case "code":
                s = countryInfo.getCode();
                break;
            default:
                s = "";
                break;
        }

        return s;
    }

    public CountryInfo() {

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

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyEN() {
        return currencyEN;
    }

    public void setCurrencyEN(String currencyEN) {
        this.currencyEN = currencyEN;
    }

    public String getCurrencyAR() {
        return currencyAR;
    }

    public void setCurrencyAR(String currencyAR) {
        this.currencyAR = currencyAR;
    }

    public String getCodeEN() {
        return codeEN;
    }

    public void setCodeEN(String codeEN) {
        this.codeEN = codeEN;
    }

    public String getCodeAR() {
        return codeAR;
    }

    public void setCodeAR(String codeAR) {
        this.codeAR = codeAR;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
