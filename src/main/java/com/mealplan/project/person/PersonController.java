package com.mealplan.project.person;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mealplan.project.person.dao.Person;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/person")
public class PersonController {

    static final Logger logger = LoggerFactory.getLogger(PersonService.class);
  @Autowired
  PersonService service;
  


    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("person", service.list());
        if (session.getAttribute("p") == null) {
            Person p = new Person();
            model.addAttribute("p", p);            
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("p", session.getAttribute("p"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "person/list";
        }

    @PostMapping
    public String save(@ModelAttribute Person person, HttpSession session) {
        if (person.getId() == null || person.getId() == 0){        
            service.create(Person.builder().name(person.getName()).gender(person.getGender()).age(person.getAge()).build());
        }
        else {
            var editPerson = service.getPersonById(person.getId());
            editPerson.setName(person.getName());
            editPerson.setGender(person.getGender());
            editPerson.setAge(person.getAge());
            editPerson.setMeals(person.getMeals());
            service.create(editPerson);
            session.setAttribute("p", null);
        }
            
      return "redirect:/person";
  }    

    @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Integer id, Model model, HttpSession session) {
        
        session.setAttribute("p", service.getPersonById(id));
        return "redirect:/person";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        service.deleteById(id);
        return "redirect:/person";
    }
  
    @GetMapping("/add")
    public String addMeal(@RequestParam(name="pid") Integer pid, @RequestParam(name="mid") Integer mid, HttpSession session ){
        service.addMealToMealList(pid, mid);
        Person p = service.getPersonById(pid);
        session.setAttribute("p", null);
        save(p, session);
        
        return "redirect:/person";
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
