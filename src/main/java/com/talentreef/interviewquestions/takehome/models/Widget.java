package com.talentreef.interviewquestions.takehome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table
public class Widget {

  private String name;

}
