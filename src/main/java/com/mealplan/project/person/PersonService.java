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
      List<Person> listOfCA = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
      return listOfCA;
  }

  public Person getPersonById(Integer id){
    return repo.findById(id).orElse(null);
  }

  public Person save(Person person){
  
      Person savedPerson = repo.save(person);
      
      return savedPerson;
    
    
  }

  public void delete(Person person){
    
      repo.delete(person);
      

  }

  public void deleteById(Integer id){
  
      repo.deleteById(id);
     
  }
}
