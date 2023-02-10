package com.gosha.universityproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/iss")
public class IssController {

    private final IssService issService;

    @GetMapping("/people")
    public ResponseEntity<List<People>> getPeople() throws URISyntaxException {
        return ResponseEntity.ok(issService.getPeople());
    }

    @GetMapping("/position")
    public ResponseEntity<IssPositionDto> getIssPosition() throws URISyntaxException {
        return ResponseEntity.ok(issService.getIssPosition());
    }

}
