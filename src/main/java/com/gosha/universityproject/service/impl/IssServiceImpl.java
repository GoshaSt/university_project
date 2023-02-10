package com.gosha.universityproject.service.impl;

import com.gosha.universityproject.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssServiceImpl implements IssService {
    @Override
    public List<People> getPeople() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://api.open-notify.org/astros.json";
        URI uri = new URI(baseUrl);
        ResponseEntity<IssPeopleDto> result = restTemplate.getForEntity(uri, IssPeopleDto.class);
        IssPeopleDto peopleList = ModelMapperUtil.modelMapper().map(result.getBody(), IssPeopleDto.class);

        return peopleList.getPeople();
    }

    @Override
    public IssPositionDto getIssPosition() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://api.open-notify.org/iss-now.json";
        URI uri = new URI(baseUrl);
        ResponseEntity<IssPositionDto> result = restTemplate.getForEntity(uri, IssPositionDto.class);

        return ModelMapperUtil.modelMapper().map(result.getBody(), IssPositionDto.class);
    }
}
