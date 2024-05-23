package com.mealplan.project.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mealplan.project.meal.Meal;
import com.mealplan.project.meal.MealService;

@SpringBootTest
public class PersonServiceTest {
  
  @Autowired
  private PersonService personService;
  @Autowired
  private MealService mealService;

  @Test
  public void testAddPerson(){

    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    Person p2 = new Person();
    p2.setName("Sally");
    p2.setAge(25);
    Person p3 = new Person();
    p3.setName("Jimmy");
    p3.setAge(65);

    long sizeB4 = personService.list().size();

    personService.save(p1);
    personService.save(p2);
    personService.save(p3);

    assertEquals(sizeB4+3, personService.list().size());
  }

  @Test
  public void testGetAndUpdatePerson(){
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
  
    personService.save(p1);

    Person p2 = personService.getPersonById(1);
    p2.setAge(45);
    personService.save(p2);

    Person p3 = personService.getPersonById(1);

    assertEquals("Harry", p3.getName());
    assertEquals(45, p3.getAge());
  }

  @Test
  public void testAddMealToList(){

    Meal m1 = mealService.getMealById(1);
    Meal m2 = mealService.getMealById(2);
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    p1.getMeals().add(m1);
    personService.save(p1);
    personService.addMeal(p1, m2);
    assertEquals(2, personService.list().get(0).getMeals().size());
    assertEquals(1, personService.list().size());
  }

  @Test
  public void testDeleteMealFromList(){

    Meal m1 = mealService.getMealById(1);
    Meal m2 = mealService.getMealById(2);
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    p1.getMeals().add(m1);
    personService.save(p1);
    personService.deleteMeal(p1, m1);
    assertEquals(0, personService.list().get(0).getMeals().size());
    assertEquals(1, personService.list().size());
  }

  @Test
  public void testDeletePerson(){
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    Person p2 = new Person();
    p2.setName("Sally");
    p2.setAge(25);
    Person p3 = new Person();
    p3.setName("Jimmy");
    p3.setAge(65);

    long sizeB4 = personService.list().size();

    personService.save(p1);
    personService.save(p2);
    personService.save(p3);

    assertEquals(sizeB4+3, personService.list().size());

    personService.delete(p3);

    assertEquals(sizeB4+2, personService.list().size());;

  }

  @Test
  public void testDeletePersonById(){
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    Person p2 = new Person();
    p2.setName("Sally");
    p2.setAge(25);
    Person p3 = new Person();
    p3.setName("Jimmy");
    p3.setAge(65);

    long sizeB4 = personService.list().size();

    personService.save(p1);
    personService.save(p2);
    personService.save(p3);

    assertEquals(sizeB4+3, personService.list().size());

    personService.deleteById(p3.getId());

    assertEquals(sizeB4+2, personService.list().size());;

  }
  
}
