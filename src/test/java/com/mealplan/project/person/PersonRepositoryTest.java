package com.mealplan.project.person;
import com.mealplan.project.mealplan.MealPlan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.mealplan.project.meal.Meal;
import com.mealplan.project.meal.MealRepository;
import com.mealplan.project.meal.MealType;
import com.mealplan.project.meal.Nutrition;
import com.mealplan.project.meal.NutritionRepository;
import com.mealplan.project.mealplan.MealPlanRepository;



@SpringBootTest
public class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepo;
  @Autowired
  private MealRepository mealRepo;
  @Autowired
  private NutritionRepository nRepo;
  @Autowired
  private MealPlanRepository mpRepo;

  @Test
  public void testLombok(){

    Person u1 = new Person();
    u1.setName("Tester");
    u1.setAge(18);
    
    String name = u1.getName();
    Integer age = u1.getAge();

    assertEquals("Tester", name);
    assertEquals(18, age);
  }

  @Test
  public void testPersonRepository(){
    List<Person> persons = new ArrayList<>();

    Person u1 = new Person();
    Person u2 = new Person();
  
    Meal m1 = mealRepo.findById(1).orElseThrow();
  

    u1.setName("Harry");
    u1.setAge(29);
    u1.setGender(Gender.M);
    u1.setHeight(180.82);
    u1.setWeight(185.00);
    u1.getMeals().add(m1);
    personRepo.save(u1);
    persons.add(u1);

    u2.setName("Sally");
    u2.setAge(27);
    u2.setGender(Gender.F);
    u2.setHeight(150.4);
    u2.setWeight(125.00);
    u2.getMeals().add(m1);
    personRepo.save(u2);
    persons.add(u2);


    long size = personRepo.count();
    assertEquals(persons.size(), size);

    personRepo.delete(u2);
    assertEquals(persons.size()-1, personRepo.count());

    Person u3 = personRepo.findById(1).orElse(new Person());
    u3.setWeight(200.00);
    personRepo.save(u3);

    Person u4 = personRepo.findById(1).orElse(new Person());
    assertEquals("Harry", u4.getName());
    assertEquals(200.00, u4.getWeight());

  }
  @Test
  public void testMealAndPersonRepository(){

    Meal m1 = mealRepo.findById(1).orElseThrow();
    Person p1 = Person.builder().name("Harry").age(29).gender(Gender.M).meals(new ArrayList<>()).build();
    p1.getMeals().add(m1);
    personRepo.save(p1);

    Person p2 = personRepo.findById(1).orElseThrow();
    assertEquals(1, p2.getMeals().size());

    Nutrition n1 = Nutrition.builder().id(3).calories(1900).fat(13).sugar(8).build();
    nRepo.save(n1);
    Meal m2 = Meal.builder().id(3).t(MealType.PROTEIN_HEAVY).nutrition(n1).build();
    mealRepo.save(m2);
    assertEquals(3, mealRepo.count());

    p2.getMeals().add(m2);
    personRepo.save(p2);
    Person p3 = personRepo.findById(1).orElseThrow();
    assertEquals(2, p3.getMeals().size());

    p3.getMeals().remove(1);
    personRepo.save(p3);
    Person p4 = personRepo.findById(1).orElseThrow();
    assertEquals(1, p4.getMeals().size());

    personRepo.deleteById(1);
    assertEquals(0, personRepo.count());

    assertEquals(3, mealRepo.count());
  }

  @Test
  public void testMealAndMealPlanAndPersonRepository(){

    Person p1 = Person.builder().name("Harry").age(29).gender(Gender.M).meals(new ArrayList<>()).mealPlans(new ArrayList<>()).build();
    personRepo.save(p1);

    MealPlan mp1 = new MealPlan();
    mp1.setMeals(new ArrayList<>());

    MealPlan mp2 = new MealPlan();
    mp2.setMeals(new ArrayList<>());

    Meal m1 = mealRepo.findById(1).orElseThrow();
    Meal m2 = mealRepo.findById(2).orElseThrow();
    mp1.getMeals().add(m1);
    mp1.getMeals().add(m2);
    mpRepo.save(mp1);
    mp2.getMeals().add(m1);
    mp2.getMeals().add(m2);
    mpRepo.save(mp2);
    assertEquals(2, mpRepo.count());

    Person p2 = personRepo.findById(1).orElseThrow();
    p2.getMeals().add(m1);
    p2.getMeals().add(m2);
    p2.getMealPlans().add(mp1);
    p2.getMealPlans().add(mp2);
    personRepo.save(p2);
    Person p3 = personRepo.findById(1).orElseThrow();
    assertEquals(1, personRepo.count());
    assertEquals(2, p3.getMeals().size());
    assertEquals(2, p3.getMealPlans().size());

    MealPlan mp3 = p3.getMealPlans().get(1);
    mp3.getMeals().remove(1);
    mpRepo.save(mp3);
    //personRepo.save(p3); 

    Person p4 = personRepo.findById(1).orElseThrow();
    MealPlan mp4  = p4.getMealPlans().get(1);
    assertEquals(1, mp4.getMeals().size());

    p4.getMealPlans().remove(1);
    personRepo.save(p4);

    Person p5 = personRepo.findById(1).orElseThrow();
    assertEquals(1, p5.getMealPlans().size());



  }
 
  
}
