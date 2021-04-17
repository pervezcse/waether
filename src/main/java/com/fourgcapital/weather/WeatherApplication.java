package com.fourgcapital.weather;

import com.fourgcapital.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherApplication.class);
    private final WeatherService weatherService;

    public WeatherApplication(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

    @Override
    public void run(String... args) {
        weatherService.getMaxTemperatureSpread()
                .ifPresent(maxTempSpread -> LOG.info("{} {}", maxTempSpread.getDay(), maxTempSpread.getSpread()));
    }

}
