package com.mealplan.project.person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealplan.project.meal.dao.Meal;
import com.mealplan.project.meal.dao.MealRepository;
import com.mealplan.project.person.dao.Person;
import com.mealplan.project.person.dao.PersonRepository;

@Service
public class PersonService {
  @Autowired
  private PersonRepository repo;
  @Autowired 
  private MealRepository mealRepo;

  static final Logger logger = LoggerFactory.getLogger(PersonService.class);

  public List<Person> list() {
    logger.debug("retrieving person list from person repo");
    List<Person> list = null;
    try {
      list = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
      logger.info("successfully retrieved person list");
    } catch (Exception e) {
      logger.error("Error retrieving person list from person repo");
    }
    return list;

  }

  public Person getPersonById(Integer id) {
    Person p1 = null;
    logger.debug("Begin retrieving person by id {}", id);
    try {
      p1 = repo.findById(id).orElseThrow();
      logger.info("Found person id {}", id);
    } catch (Exception e) {
      logger.error("Repo call findById({})", id, e);
    }
    logger.debug("Successfully returning person id {}", id);
    return p1;
  }

  public Person create(Person person) {
    logger.debug("Begin saving to the person repo person {}", person.toString());
    try {
      repo.save(person);
      logger.info("Successfully saved person {}", person.toString());
    } catch (Exception e) {
      logger.error("Saving to the person repo person {}", person.toString());
    }

    logger.debug("End saving into Person");
    return person;
  }

  public void delete(Person person) {
    logger.info("Begin deleting from Person");
    repo.delete(person);
    logger.info("End deleting from Person");
  }

 public void deleteById(Integer id){
  logger.info("Begin deleting by id from Person");
  repo.deleteById(id);
  logger.info("End deleting by id from Person");

 }
 public void addMealToMealList(Integer id, Integer meal_id){
  logger.debug("Begin adding meal {} to person meal list person id: {}", meal_id, id );
  try{
    List<Meal> list = repo.findById(id).orElseThrow().getMeals();
    Meal meal = mealRepo.findById(meal_id).orElseThrow();
    list.add(meal);
    logger.info("Added meal {} to person meal list person id: {}", meal.toString(), id);
  }catch(Exception e){
    logger.error("Error adding meal {} to person meal list person id: {}", meal_id,id, e);
  }
  logger.debug("End adding meal {} to person meal list person id: {}");
 }

}
