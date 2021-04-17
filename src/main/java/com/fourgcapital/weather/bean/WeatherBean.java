package com.fourgcapital.weather.bean;

import com.fourgcapital.weather.exception.DataNotFoundException;
import com.opencsv.bean.CsvBindByName;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.util.Scanner;

public class WeatherBean {

    @CsvBindByName(column = "Day")
    private String day;

    @CsvBindByName(column = "MaxT")
    private String maxT;

    @CsvBindByName(column = "MinT")
    private String minT;

    @CsvBindByName(column = "AvT")
    private Double avgT;

    @CsvBindByName(column = "HDDay")
    private String hDDay;

    @CsvBindByName(column = "AvDP")
    private String avDP;

    @CsvBindByName(column = "1HrP")
    private String oneHrP;

    @CsvBindByName(column = "TPcp")
    private String tPcp;

    @CsvBindByName(column = "WxType")
    private String wxType;

    @CsvBindByName(column = "PDir")
    private String pDir;

    @CsvBindByName(column = "AvSp")
    private String avSp;

    @CsvBindByName(column = "Dir")
    private String dir;

    @CsvBindByName(column = "MxS")
    private String mxS;

    @CsvBindByName(column = "SkyC")
    private String skyC;

    @CsvBindByName(column = "MxR")
    private String mxR;

    @CsvBindByName(column = "MnR")
    private String mnR;

    @CsvBindByName(column = "AvSLP")
    private String avSLP;

    public WeatherBean() {

    }

    public WeatherBean(String day, String maxT, String minT,
                       Double avgT, String hDDay, String avDP,
                       String oneHrP, String tPcp, String wxType,
                       String pDir, String avSp, String dir,
                       String mxS, String skyC, String mxR,
                       String mnR, String avSLP) {
        this.day = day;
        this.maxT = maxT;
        this.minT = minT;
        this.avgT = avgT;
        this.hDDay = hDDay;
        this.avDP = avDP;
        this.oneHrP = oneHrP;
        this.tPcp = tPcp;
        this.wxType = wxType;
        this.pDir = pDir;
        this.avSp = avSp;
        this.dir = dir;
        this.mxS = mxS;
        this.skyC = skyC;
        this.mxR = mxR;
        this.mnR = mnR;
        this.avSLP = avSLP;
    }

    public TemperatureSpreadBean getTemperatureSpread() {
        Double spread = getMaxT() - getMinT();
        return new TemperatureSpreadBean(day, spread);
    }

    public boolean isDailyData() {
        return NumberUtils.isParsable(day);
    }

    public String getDay() {
        return day;
    }

    public Double getMaxT() {
        if(StringUtils.isBlank(maxT)) {
            throw new DataNotFoundException("MaxT");
        }
        try(Scanner scanner = new Scanner(maxT)) {
            return scanner.useDelimiter("[^\\d]+").nextDouble();
        }
    }

    public Double getMinT() {
        if(StringUtils.isBlank(minT)) {
            throw new DataNotFoundException("MinT");
        }
        try(Scanner scanner = new Scanner(minT)) {
            return scanner.useDelimiter("[^\\d]+").nextDouble();
        }
    }

    public Double getAvgT() {
        return avgT;
    }

    public String gethDDay() {
        return hDDay;
    }

    public String getAvDP() {
        return avDP;
    }

    public String getOneHrP() {
        return oneHrP;
    }

    public String gettPcp() {
        return tPcp;
    }

    public String getWxType() {
        return wxType;
    }

    public String getpDir() {
        return pDir;
    }

    public String getAvSp() {
        return avSp;
    }

    public String getDir() {
        return dir;
    }

    public String getMxS() {
        return mxS;
    }

    public String getSkyC() {
        return skyC;
    }

    public String getMxR() {
        return mxR;
    }

    public String getMnR() {
        return mnR;
    }

    public String getAvSLP() {
        return avSLP;
    }
}
