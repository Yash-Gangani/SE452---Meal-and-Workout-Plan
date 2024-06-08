package com.mealplan.project.meal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NutritionRepository extends CrudRepository<Nutrition, Integer>{
  
}
