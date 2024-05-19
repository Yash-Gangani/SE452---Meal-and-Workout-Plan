package com.mealplan.project.meal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {
  @Id
  //@GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private MealType t;
  @OneToOne
  private Nutrition nutrition;

} 
