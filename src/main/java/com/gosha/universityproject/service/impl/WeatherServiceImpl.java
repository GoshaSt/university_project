package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final LocationService locationService;

    @Override
    public String getWeather() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String place;
        place = locationService.getLocation();

        String location = place;
        String request = location.replaceAll(" ", "%20");
        final String baseUrl = "http://api.weatherapi.com/v1/current.json?key=f5cec576cb134ae2955125110221610&q=" + request + "&aqi=no";
        URI uri = new URI(baseUrl);
        ResponseEntity<WeatherDto> responce = restTemplate.getForEntity(uri, WeatherDto.class);

        WeatherDto result = ModelMapperUtil.modelMapper().map(responce.getBody(), WeatherDto.class);
        result.setPlace(location);

        return result.getPlace() + " " + result.getCurrent().getTemp_c() + "°C";
    }
}
