package com.fourgcapital.weather.service;

import com.fourgcapital.weather.bean.TemperatureSpreadBean;
import com.fourgcapital.weather.bean.WeatherBean;
import com.fourgcapital.weather.loader.WeatherBeanLoader;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherBeanLoader csvWeatherBeanLoader;

    public WeatherService(WeatherBeanLoader csvWeatherBeanLoader) {
        this.csvWeatherBeanLoader = csvWeatherBeanLoader;
    }

    public Optional<TemperatureSpreadBean> getMaxTemperatureSpread() {
        List<WeatherBean> weatherBeans = csvWeatherBeanLoader.getWeatherBeans();
        return weatherBeans.stream()
                .filter(WeatherBean::isDailyData)
                .map(WeatherBean::getTemperatureSpread)
                .max(Comparator.comparing(TemperatureSpreadBean::getSpread));
    }

}
