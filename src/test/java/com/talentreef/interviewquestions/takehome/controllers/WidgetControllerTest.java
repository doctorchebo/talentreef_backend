package com.talentreef.interviewquestions.takehome.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.services.WidgetService;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(MockitoJUnitRunner.class)
public class WidgetControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @InjectMocks
    private WidgetController widgetController;

    @Mock
    private WidgetService widgetService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(widgetController).build();
    }

    @Test
    public void getAllWidgets() throws Exception {
        Widget widget = Widget.builder()
                .name("Awesome widget")
                .price(150.00)
                .description("Awesome description")
                .build();
        List<Widget> widgetList = List.of(widget);
        when(widgetService.getAllWidgets()).thenReturn(widgetList);

        MvcResult mvcResult = mockMvc.perform(get("/v1/widgets/getAll"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<Widget> parsedResult = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<Widget>>() {
                });
        assertThat(parsedResult).isEqualTo(widgetList);

    }

    @Test
    public void addWidget() throws Exception {
        Widget widget = Widget.builder()
                .name("Awesome widget")
                .price(150.00)
                .description("Awesome description")
                .build();
        when(widgetService.addWidget(widget)).thenReturn(widget);

        MvcResult mvcResult = mockMvc
                .perform(post("/v1/widgets/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(widget)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Widget parsedResult = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<Widget>() {
                });
        assertThat(parsedResult).isEqualTo(widget);
    }

    @Test
    public void editWidget() throws Exception {
        Widget widget = Widget.builder()
                .name("Awesome widget")
                .price(150.00)
                .description("Awesome description")
                .build();
        when(widgetService.editWidget(widget)).thenReturn((ResponseEntity) ResponseEntity.ok(widget));
        MvcResult mvcResult = mockMvc.perform(post("/v1/widgets/edit/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(widget)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Widget parsedResult = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<Widget>() {
                });
        assertThat(parsedResult).isEqualTo(widget);
    }

    @Test
    public void deleteWidget() throws Exception {
        String widgetName = "incredible";
        String successResponse = String.format("Widget with name %s deleted successfully", widgetName);
        when(widgetService.deleteWidget(eq(widgetName)))
                .thenReturn((ResponseEntity) ResponseEntity.ok(successResponse));
        MvcResult mvcResult = mockMvc.perform(post("/v1/widgets/delete/"+ widgetName))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        String parsedResult = mvcResult.getResponse().getContentAsString();

        assertThat(parsedResult).isEqualTo(successResponse);
    }
}