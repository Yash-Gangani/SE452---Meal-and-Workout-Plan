package com.mealplan.project.meal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mealplan.project.meal.dao.Meal;
import com.mealplan.project.meal.dao.MealRepository;



@Service
public class MealService {
 @Autowired
 private MealRepository repo;
 static final Logger logger = LoggerFactory.getLogger(MealService.class);

 public List<Meal> list(){
  logger.debug("retrieving meal list from meal repo");
  List<Meal> list = null;
  try{
    list = StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
    logger.info("successfully retrieved meal list");
  }catch(Exception e){
    logger.error("Error retrieving meal list from meal repo");
  }
  return list;

}

 public Meal getMealById(Integer id){
  Meal m1 = null;
  logger.debug("Begin retrieving meal by id {}", id);
  try{
    m1 = repo.findById(id).orElseThrow();
    logger.info("Found meal id {}", id);
  }catch(Exception e){
    logger.error("Error: Repo call findById({})",id, e);
  }
  logger.debug("Successfully returning meal id {}", id);
  return m1;
 }

 public Meal create(Meal meal){
  logger.debug("Begin saving to the meal repo meal {}", meal.toString());
  try{
    repo.save(meal);
    logger.info("Successfully saved meal {}", meal.toString());
  }
  catch(Exception e){
    logger.error("Error saving to the meal repo meal {}", meal.toString());
  }
 
  logger.debug("End saving into Meal");
  return meal;
 }
 

 public void delete(Meal meal){
  logger.info("Begin deleting from Meal");
  repo.delete(meal);
  logger.info("End deleting from Meal");
 }

 public void deleteById(Integer Id){
  logger.info("Begin deleting by id from Meal");
  repo.deleteById(Id);
  logger.info("End deleting by id from Meal");
 }
 
 
}
