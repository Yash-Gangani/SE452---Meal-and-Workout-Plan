package com.mealplan.project.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mealplan.project.person.dao.Person;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/person")
public class PersonResource{
  @Autowired
  private PersonService service;

  @GetMapping 
  public List<Person> list(){
    return service.list();
    
  }
  
  @GetMapping("/{id}")
  public Person getPerson(@PathVariable Integer id){
    return service.getPersonById(id);
  }


  @PostMapping 
  public Person create(@RequestBody Person person){
    service.create(person);
    return person;
  }
  
 
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id){
    service.deleteById(id);
  }
  

  @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }    


}
