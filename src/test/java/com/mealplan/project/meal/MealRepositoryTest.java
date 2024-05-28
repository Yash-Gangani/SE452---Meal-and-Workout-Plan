package com.mealplan.project.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import org.springframework.test.annotation.DirtiesContext.ClassMode;
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@SpringBootTest
public class MealRepositoryTest {
  @Autowired
  private MealRepository repo;
  @Autowired
  private NutritionRepository repoN;

  @Test
  public void testLombok(){
    Nutrition n1 = Nutrition.builder().calories(500).fat(20).sugar(5).build();
    Meal m1 = Meal.builder().t(MealType.NORMAL).nutrition(n1).build();

    Integer cals = n1.getCalories();
    MealType t = m1.getT();

    assertEquals(500, cals);
    assertEquals(MealType.NORMAL, t);
    
  }

  @Test
  public void testMealRepository(){
    long before = repo.count();
    Nutrition n1 = Nutrition.builder().id(5).calories(500).fat(14).sugar(5).build();
    repoN.save(n1);
    Meal m1 = Meal.builder().id(5).t(MealType.NORMAL).nutrition(n1).build();

    Nutrition n2 = Nutrition.builder().id(6).calories(400).fat(20).sugar(5).build();
    repoN.save(n2);
    Meal m2 = Meal.builder().id(6).t(MealType.NORMAL).nutrition(n2).build();

    repo.save(m1);
    repo.save(m2);

    long after = repo.count();

    assertEquals(before+2, after);

    repo.delete(m2);
    after = repo.count();
    assertEquals(before+1, after);

    Meal m3 = repo.findById(5).orElse(new Meal());
    m3.setT(MealType.LOW_CARB);
    repo.save(m3);

    Meal m4 = repo.findById(5).orElse(new Meal());
    assertEquals(m3.getNutrition(), m4.getNutrition());

  }
}
