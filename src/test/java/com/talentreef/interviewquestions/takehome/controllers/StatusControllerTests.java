package com.talentreef.interviewquestions.takehome.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatusControllerTests {

  private ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mockMvc;

  @InjectMocks
  private StatusController statusController;

  @Before
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(statusController).build();
  }

  @Test
  public void when_statusCheck_expect_statusOk() throws Exception {
    final Map<String, String> okStatus = new HashMap<>();
    okStatus.put("status", "OK");

    mockMvc.perform(get("/status")
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(okStatus)));
  }
}
