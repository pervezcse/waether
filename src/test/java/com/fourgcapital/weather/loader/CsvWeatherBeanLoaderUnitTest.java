package com.fourgcapital.weather.loader;

import com.fourgcapital.weather.bean.WeatherBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.io.UncheckedIOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CsvWeatherBeanLoaderUnitTest {

    private WeatherBeanLoader csvWeatherBeanLoader;
    @Mock
    private ResourceLoader resourceLoader;
    private String inputFilePath;

    @BeforeEach
    void initUseCase() {
        inputFilePath = "data/weather.csv";
        csvWeatherBeanLoader = new CsvWeatherBeanLoader(resourceLoader, inputFilePath);

    }

    @Test
    void givenValidInputFile_whenGetWeatherBeans_thenReturnMultipleWeatherBeans() {
        Resource resource = new ClassPathResource(inputFilePath);
        when(resourceLoader.getResource(inputFilePath)).thenReturn(resource);
        List<WeatherBean> weatherBeans = csvWeatherBeanLoader.getWeatherBeans();
        assertThat(weatherBeans, hasSize(31));
    }


    @Test
    void givenInvalidInputFilePath_whenGetWeatherBeans_thenUncheckedIOExceptionThrown() {
        Resource resource = new ClassPathResource("data/weather1.csv");
        when(resourceLoader.getResource(inputFilePath)).thenReturn(resource);
        assertThrows(UncheckedIOException.class, () -> csvWeatherBeanLoader.getWeatherBeans());
    }
}
