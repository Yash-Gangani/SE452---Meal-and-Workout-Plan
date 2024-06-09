package com.mealplan.project.meal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mealplan.project.meal.dao.Meal;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/meal")
public class MealController {

  @Autowired
  MealService service;

  @GetMapping
  public String list(Model model, HttpSession session){
    model.addAttribute("meal", service.list());
    if(session.getAttribute("m") == null){
      model.addAttribute("m", new Meal());
      model.addAttribute("btnAddOrModifyLabel", "Add");
    }
    else{
      model.addAttribute("m", session.getAttribute("m"));
      model.addAttribute("btnAddOrModifyLabel", "Modify");
    }
    return "meal/list";
  }


  @PostMapping
  public String save(@ModelAttribute Meal meal, HttpSession session) {
      if (meal.getId() == null || meal.getId() == 0){
        
          service.create(Meal.builder().t(meal.getT()).fat(meal.getFat()).calories(meal.getCalories()).sugar(meal.getSugar()).build());
      }
     else {
          var editMeal = service.getMealById(meal.getId());
          editMeal.setT(meal.getT());
          editMeal.setCalories(meal.getCalories());
          editMeal.setFat(meal.getFat());
          editMeal.setSugar(meal.getSugar());
          service.create(editMeal);
          session.setAttribute("m", null);
      }
      return "redirect:/meal";
  }    


  @GetMapping("/edit/{id}")
    public String get(@PathVariable("id") Integer id, Model model, HttpSession session) {
        session.setAttribute("m", service.getMealById(id));
        return "redirect:/meal";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        service.deleteById(id);
        return "redirect:/meal";
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
