package com.mealplan.project.person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealplan.project.meal.Meal;

@Service
public class PersonService {
  @Autowired
  private PersonRepository repo;

  static final Logger log = LoggerFactory.getLogger(PersonService.class);

  public List<Person> list(){
      List<Person> listOfPersons = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
      return listOfPersons;
  }

  public Person getPersonById(Integer id){
    return repo.findById(id).orElse(null);
  }

  public Person save(Person person){
  
      Person savedPerson = repo.save(person);
      
      return savedPerson;
  }

  public Person addMeal(Person person,Meal meal){
    person.getMeals().add(meal);
    repo.save(person);
    return person;
  }

  public void delete(Person person){
    
      repo.delete(person);
      
  }

  public void deleteById(Integer id){
  
      repo.deleteById(id);
     
  }

  public void deleteMeal(Person person, Meal meal){
    person.getMeals().remove(meal);
    repo.save(person);
  }
}
