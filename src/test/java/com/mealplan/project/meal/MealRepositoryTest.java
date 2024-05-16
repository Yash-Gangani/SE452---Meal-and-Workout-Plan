package com.mealplan.project.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MealRepositoryTest {
  @Autowired
  private MealRepository repo;

  @Test
  public void testLombok(){
    Nutrition n1 = Nutrition.builder().calories(500).fat(20).sugar(5).build();
    Meal m1 = Meal.builder().type(MealType.NORMAL).nutrition(n1).build();

    Integer cals = n1.getCalories();
    MealType t = m1.getType();

    assertEquals(500, cals);
    assertEquals(MealType.NORMAL, t);
    
  }

  @Test
  public void testMealRepository(){
    
  }
}
