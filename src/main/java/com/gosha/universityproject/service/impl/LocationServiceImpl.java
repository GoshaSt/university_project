package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.model.dto.IssPositionDto;
import com.gosha.universityproject.model.dto.PlaceDto;
import com.gosha.universityproject.service.IssService;
import com.gosha.universityproject.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final IssService issService;

    @Override
    public String getLocation() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        IssPositionDto position = new IssPositionDto();
        position = issService.getIssPosition();

        final String baseUrl = "http://api.positionstack.com/v1/reverse?access_key=517db2665e9559e3457dbe236237cd83&query=" + position.getIss_position().getLatitude() + "," + position.getIss_position().getLongitude();
        URI uri = new URI(baseUrl);
        ResponseEntity<PlaceDto> result = restTemplate.getForEntity(uri, PlaceDto.class);

        return result.getBody().getData().get(0).getLabel();
    }
}
