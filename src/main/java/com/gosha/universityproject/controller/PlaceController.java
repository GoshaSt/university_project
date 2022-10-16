package com.gosha.universityproject.controller;

import com.gosha.universityproject.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final LocationService locationService;

    @GetMapping("/location")
    public String getLocation() throws URISyntaxException {
        return locationService.getLocation();
    }
}
