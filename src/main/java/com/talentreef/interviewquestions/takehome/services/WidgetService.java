package com.talentreef.interviewquestions.takehome.services;

import com.talentreef.interviewquestions.takehome.models.Widget;
import com.talentreef.interviewquestions.takehome.respositories.WidgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WidgetService {
  private static Logger logger = LoggerFactory.getLogger(WidgetService.class);

  private final WidgetRepository widgetRepository;

  @Autowired
  private WidgetService(WidgetRepository widgetRepository) {
    Assert.notNull(widgetRepository, "widgetRepository must not be null");
    this.widgetRepository = widgetRepository;
  }

  public List<Widget> getAllWidgets() {
    logger.debug("Getting all widgets");
    return widgetRepository.findAll();
  }

  public ResponseEntity<?> editWidget(Widget widget) {
    logger.debug("Editing widget with name {}", widget.getName());
    Optional<Widget> widgetOpt = widgetRepository.findById(widget.getName());
    if(!widgetOpt.isPresent()){
      return ResponseEntity.badRequest().body(String.format("widget with name %s doesn't exist", widget.getName()));
    } else {
      Widget editedWidget = widgetOpt.get();
      editedWidget.setName(widget.getName());
      editedWidget.setDescription(widget.getDescription());
      editedWidget.setPrice(widget.getPrice());
      widgetRepository.save(editedWidget);
      return ResponseEntity.ok().body(widgetRepository.save(editedWidget));
    }
  }

  public ResponseEntity<?> deleteWidget(String name) {
    logger.debug("Deleting widget with name {}", name);
    Optional<Widget> widgetOpt = widgetRepository.findById(name);
    if(!widgetOpt.isPresent()){
      return ResponseEntity.badRequest().body(String.format("widget with name %s not found", name));
    } else {
      widgetRepository.deleteById(name);
      return ResponseEntity.ok(String.format("Widget with name %s deleted successfully", name));
    }
  }
  public Widget addWidget(Widget widget) {
    logger.debug("Adding new widget");
    return widgetRepository.save(widget);
  }
}
