package com.gosha.universityproject.service;

import com.gosha.universityproject.entity.People;
import com.gosha.universityproject.model.dto.IssPositionDto;

import java.net.URISyntaxException;
import java.util.List;

public interface IssService {

    List<People> getPeople() throws URISyntaxException;

    IssPositionDto getIssPosition() throws URISyntaxException;
}
