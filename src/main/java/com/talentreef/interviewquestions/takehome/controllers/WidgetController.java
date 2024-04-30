package com.talentreef.interviewquestions.takehome.controllers;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.services.WidgetService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/widgets", produces = MediaType.APPLICATION_JSON_VALUE)
public class WidgetController {

  private final WidgetService widgetService;

  public WidgetController(WidgetService widgetService) {
    Assert.notNull(widgetService, "widgetService must not be null");
    this.widgetService = widgetService;
  }

  @GetMapping("/getAll")
  public ResponseEntity<List<Widget>> getAllWidgets() {
    return ResponseEntity.ok(widgetService.getAllWidgets());
  }

  @PostMapping("/add")
  public ResponseEntity<?> addWidget(@Valid @RequestBody Widget widget) {
    return ResponseEntity.ok(widgetService.addWidget(widget));
  }
  @PostMapping("/edit")
  public ResponseEntity<?> editWidget(@Valid @RequestBody Widget widget) {
    return widgetService.editWidget(widget);
  }

  @PostMapping("/delete/{name}")
  public ResponseEntity<?> deleteWidget(@PathVariable String name) {
    return widgetService.deleteWidget(name);
  }

}
