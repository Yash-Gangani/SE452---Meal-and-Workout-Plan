package com.mealplan.project.meal;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/* the tests in this suite are intended to be executed individually, not as a group */

@SpringBootTest
public class MealServiceTest {
  @Autowired
  private MealService service;
  @Autowired
  private NutritionRepository repoN;

  @Test
  public void testInitListSize(){
    List<Meal> list = service.list();
    assertEquals(2, list.size());
  }
 

  @Test
  public void testAddMeal(){
    long before = service.list().size();
    Nutrition n1 = Nutrition.builder().id(100).calories(650).fat(25).build();
    repoN.save(n1);
    service.save(Meal.builder().id(100).t(MealType.LOW_CARB).nutrition(n1).build());
    long after = service.list().size();
    assertEquals(before+1, after);
  }

  @Test 
  public void testGetandUpdateMeal(){
    Meal m1 = service.getMealById(1);
    m1.setT(MealType.LOW_CARB);
    service.save(m1);

    Meal m2 = service.getMealById(1);
    assertEquals(m1.getT(), m2.getT());
  }

  @Test
  public void testDeleteMeal(){
    long before = service.list().size();
    Meal m1 = service.getMealById(2);
    service.delete(m1);
    long after = service.list().size();
    assertEquals(before-1, after);

  }

  @Test 
  public void testDeleteMealById(){
    long before = service.list().size();
    service.deleteById(2);
    long after = service.list().size();
    assertEquals(before-1, after);
  }

}
