package com.fourgcapital.weather.loader;

import com.fourgcapital.weather.bean.WeatherBean;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.List;

@Component
public class CsvWeatherBeanLoader extends WeatherBeanLoader {

    private final ResourceLoader resourceLoader;
    private final String inputFilePath;

    public CsvWeatherBeanLoader(ResourceLoader resourceLoader, @Value("${input.file.path}") String inputFilePath) {
        this.resourceLoader = resourceLoader;
        this.inputFilePath = inputFilePath;
    }

    List<WeatherBean> loadWeatherBeans() {
        try {
            InputStream resource = resourceLoader.getResource(inputFilePath).getInputStream();
            try (Reader reader = new BufferedReader(new InputStreamReader(resource))) {
                CsvToBean<WeatherBean> cb = new CsvToBeanBuilder<WeatherBean>(reader)
                        .withType(WeatherBean.class)
                        .withSeparator(';')
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                return cb.parse();
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
