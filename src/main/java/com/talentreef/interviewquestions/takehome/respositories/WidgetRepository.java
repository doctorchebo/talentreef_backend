package com.talentreef.interviewquestions.takehome.respositories;

import com.talentreef.interviewquestions.takehome.models.Widget;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class WidgetRepository {

  private List<Widget> widgets = new ArrayList<>(List.of(
          Widget.builder().name("name 1").description("description 1").price(10.0).build(),
          Widget.builder().name("name 2").description("description 2").price(20.0).build(),
          Widget.builder().name("name 3").description("description 3").price(30.0).build(),
          Widget.builder().name("name 4").description("description 4").price(40.0).build(),
          Widget.builder().name("name 5").description("description 5").price(50.0).build(),
          Widget.builder().name("name 6").description("description 6").price(60.0).build(),
          Widget.builder().name("name 7").description("description 7").price(70.0).build()
  ));

  public List<Widget> deleteById(String name) {
    this.widgets = widgets.stream()
                      .filter((Widget widget) -> !name.equals(widget.getName()))
                      .collect(Collectors.toCollection(ArrayList::new));
    return widgets;
  }

  public List<Widget> findAll() {
    return widgets;
  }

  public Optional<Widget> findById(String name) {
    Optional<Widget> result = widgets.stream()
                                   .filter((Widget widget) -> name.equals(widget.getName()))
                                   .findAny();
    return result;
  }

  public Widget save(Widget widget) {
    deleteById(widget.getName());
    widgets.add(widget);
    return widget;
  }

}
