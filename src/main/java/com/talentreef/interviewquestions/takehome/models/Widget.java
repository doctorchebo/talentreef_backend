package com.talentreef.interviewquestions.takehome.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder(toBuilder=true)
public class Widget {
  @Id
  @NotBlank(message = "Name is required")
  @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
  @Column(unique = true)
  private String name;

  @NotBlank(message = "Description is required")
  @Size(min = 5, max = 1000, message = "Description must be between 5 and 1000 characters")
  private String description;

  @DecimalMin(value = "1.00", message = "Price must be at least 1.00")
  @DecimalMax(value = "20000.00", message = "Price must not exceed 20000.00")
  @Digits(integer = 5, fraction = 2, message = "Price must have at most 5 digits before the decimal point and 2 digits after")
  private Double price;

}
