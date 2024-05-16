package com.mealplan.project.person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
  @Autowired
  private PersonRepository repo;

  public List<Person> list(){
     List<Person> listOfCA = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
    return listOfCA;
  }

  public Person getPersonById(Integer id){
    return repo.findById(id).orElse(null);
  }

  public Person save(Person person){
    repo.save(person);
    return person;
  }

  public void delete(Person person){
    repo.delete(person);
  }

  public void deleteById(Integer id){
    repo.deleteById(id);
  }
}
