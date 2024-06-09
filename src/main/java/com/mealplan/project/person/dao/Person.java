package com.mealplan.project.person.dao;

import java.util.ArrayList;
import java.util.List;

import com.mealplan.project.meal.dao.Meal;
import com.mealplan.project.mealplan.MealPlan;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Person {

  public Person(){
    this.meals = new ArrayList<>();
    this.mealPlans = new ArrayList<>();
  }
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;
  private Integer age;
  private Gender gender;
  private Double height;
  private Double weight;
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Meal> meals;
  @ManyToMany(fetch = FetchType.EAGER)
  private List<MealPlan> mealPlans;
}