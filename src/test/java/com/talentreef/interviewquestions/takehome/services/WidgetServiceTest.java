package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class WidgetServiceTest {

    @Mock
    private WidgetRepository widgetRepository;

    @InjectMocks
    private WidgetService widgetService;

    @Test
    public void getAllWidgets() {
        Widget widget = Widget.builder()
                .name("nice widget")
                .description("awesome description")
                .price(150.00)
                .build();
        List<Widget> widgetList = List.of(widget);
        when(widgetRepository.findAll()).thenReturn(widgetList);
        assertThat(widgetService.getAllWidgets()).isEqualTo(widgetList);
    }

    @Test
    public void editWidget() {
        Widget widget = Widget.builder()
                .name("nice widget")
                .description("awesome description")
                .price(150.00)
                .build();
        when(widgetRepository.findById(widget.getName())).thenReturn(Optional.of(widget));
        when(widgetRepository.save(widget)).thenReturn(widget);
        assertThat(widgetService.editWidget(widget)).isEqualTo((ResponseEntity) ResponseEntity.ok(widget));
    }

    @Test
    public void deleteWidget() {
        Widget widget = Widget.builder()
                .name("awesome")
                .description("awesome description")
                .price(150.00)
                .build();
        String widgetName = "awesome";
        String successResponse = String.format("Widget with name %s deleted successfully", widgetName);
        when(widgetRepository.findById(widget.getName())).thenReturn(Optional.of(widget));
        when(widgetRepository.deleteById(widgetName)).thenReturn(Collections.emptyList());
        assertThat(widgetService.deleteWidget(widgetName)).isEqualTo((ResponseEntity) ResponseEntity.ok(successResponse));
    }

    @Test
    public void addWidget() {
        Widget widget = Widget.builder()
                .name("awesome")
                .description("awesome description")
                .price(150.00)
                .build();
        when(widgetRepository.save(widget)).thenReturn(widget);
        assertThat(widgetService.addWidget(widget)).isEqualTo(widget);
    }
}