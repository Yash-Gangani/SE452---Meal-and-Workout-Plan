package com.mealplan.project.person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired
  private PersonRepository repo;

  static final Logger log = LoggerFactory.getLogger(PersonService.class);

  public List<Person> list(){
    log.info("Begun retrieving person list...");
    try {
      List<Person> listOfCA = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
      log.info("Person list retrieval successful!");
      return listOfCA;
    } catch(Exception e) {
      log.error("Person list retrieval unsuccessful! Exception: {}", e.getMessage(), e);
      return null;
    }
  }

  public Person getPersonById(Integer id){
    log.info("Begun retrieving person by ID: {}", id);
    try {
      Person person = repo.findById(id).orElse(null);
      if (person != null) {
        log.info("Retrieved person by ID: {} successfully!", id);
      } else {
        log.warn("No person found with ID: {}", id);
      }
      return person;
    } catch(Exception e) {
      log.error("Person by ID: {} retrieval unsuccessful! Exception: {}", id, e.getMessage(), e);
      return null;
    }
  }

  public Person save(Person person){
    log.info("Begun saving person with id: {}", person.getId() != null ? person.getId() : "null");
    try {
      Person savedPerson = repo.save(person);
      log.info("Saving person with id: {} successfully!", savedPerson.getId());
      return savedPerson;
    } catch(Exception e){
      log.error("Saving person with id: {} unsuccessful! Exception: {}", person.getId() != null ? person.getId() : "null", e.getMessage(), e);
      return null;
    }
  }

  public void delete(Person person){
    log.info("Begun deleting person with id: {}", person.getId());
    try {
      repo.delete(person);
      log.info("Person with id: {} deleted successfully!", person.getId());
    } catch (Exception e) {
      log.error("Deletion of person with id: {} unsuccessful! Exception: {}", person.getId(), e.getMessage(), e);
    }
  }

  public void deleteById(Integer id){
    log.info("Begun deleting person with id: {}", id);
    try {
      repo.deleteById(id);
      log.info("Person with id: {} deleted successfully!", id);
    } catch (Exception e) {
      log.error("Deletion of person with id: {} unsuccessful! Exception: {}", id, e.getMessage(), e);
    }
  }
}
