package com.gosha.universityproject.model.dto;

import com.gosha.universityproject.entity.Weather;
import lombok.Data;

@Data
public class WeatherDto {

    String place;

    Weather current;

}
