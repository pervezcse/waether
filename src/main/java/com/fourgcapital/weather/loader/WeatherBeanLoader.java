package com.fourgcapital.weather.loader;

import com.fourgcapital.weather.bean.WeatherBean;
import java.util.ArrayList;
import java.util.List;

public abstract class WeatherBeanLoader {

    private List<WeatherBean> weatherBeans = new ArrayList<>();

    public List<WeatherBean> getWeatherBeans() {
        weatherBeans.clear();
        weatherBeans.addAll(this.loadWeatherBeans());
        return weatherBeans;
    }

    abstract List<WeatherBean> loadWeatherBeans();

}
