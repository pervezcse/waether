package com.fourgcapital.weather.service;

import com.fourgcapital.weather.bean.TemperatureSpreadBean;
import com.fourgcapital.weather.bean.WeatherBean;
import com.fourgcapital.weather.exception.DataNotFoundException;
import com.fourgcapital.weather.loader.WeatherBeanLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class WeatherServiceUnitTest {

    private WeatherService weatherService;
    @Mock
    private WeatherBeanLoader csvWeatherBeanLoader;
    private List<WeatherBean> weatherBeans;

    @BeforeEach
    void initUseCase() {
        weatherService = new WeatherService(csvWeatherBeanLoader);
        weatherBeans = new ArrayList<>();
        weatherBeans.add(new WeatherBean("1", "88", "59", 74.0, "",
                "54","","0","F","280","10",
                "270","17","2","93","23","1005"));
        weatherBeans.add(new WeatherBean("2", "79", "63", 71.0, "",
                "47","","0","F","330","9",
                "340","23","3","70","28","1005"));
    }

    @Test
    void givenSingleValidWeatherBean_whenGetMaxTemperatureSpread_thenDayAndSpreadShouldBeEqual() {
        WeatherBean weatherBean = weatherBeans.get(0);
        when(csvWeatherBeanLoader.getWeatherBeans()).thenReturn(Collections.singletonList(weatherBean));
        Optional<TemperatureSpreadBean> maxTemperatureSpread = weatherService.getMaxTemperatureSpread();
        assertThat(maxTemperatureSpread.isPresent(), equalTo(true));
        assertThat(maxTemperatureSpread.get().getDay(), equalTo(weatherBean.getDay()));
        assertThat(maxTemperatureSpread.get().getDay(), equalTo(weatherBean.getTemperatureSpread().getDay()));
        assertThat(maxTemperatureSpread.get().getSpread(), equalTo(weatherBean.getTemperatureSpread().getSpread()));
    }

    @Test
    void givenMultipleValidWeatherBeans_whenGetMaxTemperatureSpread_thenDayAndSpreadShouldBeFound() {
        when(csvWeatherBeanLoader.getWeatherBeans()).thenReturn(weatherBeans);
        Optional<TemperatureSpreadBean> maxTemperatureSpread = weatherService.getMaxTemperatureSpread();
        assertThat(maxTemperatureSpread.isPresent(), equalTo(true));
        List<TemperatureSpreadBean> temperatureSpreadBeans = weatherBeans.stream()
                .map(WeatherBean::getTemperatureSpread)
                .collect(Collectors.toList());
        assertThat(temperatureSpreadBeans,
                hasItem(allOf(hasProperty("day", equalTo(maxTemperatureSpread.get().getDay())),
                        hasProperty("spread", equalTo(maxTemperatureSpread.get().getSpread())))));
    }

    @Test
    void givenInvalidWeatherBeanWithNoMaxT_whenGetMaxTemperatureSpread_thenDataNotFoundExceptionShouldBeThrown() {
        WeatherBean weatherBean = new WeatherBean("2", "", "63", 71.0, "",
                "47","","0","F","330","9",
                "340","23","3","70","28","1005");
        when(csvWeatherBeanLoader.getWeatherBeans()).thenReturn(Collections.singletonList(weatherBean));
        Exception exception = assertThrows(DataNotFoundException.class, () -> weatherService.getMaxTemperatureSpread());
        String expectedMessage = "MaxT not found in weather input file";
        assertThat(exception.getMessage(), is(expectedMessage));
    }
}
