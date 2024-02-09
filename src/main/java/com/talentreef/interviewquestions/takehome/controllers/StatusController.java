package com.talentreef.interviewquestions.takehome.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatusController {

  @GetMapping
  public ResponseEntity<Map<String, String>> status() {
    Map<String, String> result = new HashMap<>();
    result.put("status", "OK");
    return ResponseEntity.ok(result);
  }
}
