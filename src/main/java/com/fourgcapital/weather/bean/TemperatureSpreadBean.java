package com.fourgcapital.weather.bean;

public class TemperatureSpreadBean {

    private final String day;
    private final Double spread;

    public TemperatureSpreadBean(String day, Double spread) {
        this.day = day;
        this.spread = spread;
    }

    public String getDay() {
        return day;
    }

    public Double getSpread() {
        return spread;
    }
}
