package com.talentreef.interviewquestions.takehome.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WidgetControllerTests {

  final private ObjectMapper objectMapper = new ObjectMapper();

  private MockMvc mockMvc;

  @Mock
  private WidgetService widgetService;

  @InjectMocks
  private WidgetController widgetController;

  @Before
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(widgetController).build();
  }

  @Test
  public void when_getAllWidgets_expect_allWidgets() throws Exception {
    Widget widget = Widget.builder().name("Widget von Hammersmark").build();
    List<Widget> allWidgets = List.of(widget);
    when(widgetService.getAllWidgets()).thenReturn(allWidgets);

    MvcResult result = mockMvc.perform(get("/v1/widgets"))
               .andExpect(status().isOk())
               .andDo(print())
               .andReturn();

    List<Widget> parsedResult = objectMapper.readValue(result.getResponse().getContentAsString(),
        new TypeReference<List<Widget>>(){});
    assertThat(parsedResult).isEqualTo(allWidgets);
  }

}
