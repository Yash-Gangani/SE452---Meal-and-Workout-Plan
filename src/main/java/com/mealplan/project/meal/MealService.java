package com.mealplan.project.meal;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MealService {
 @Autowired
 private MealRepository repo;
 static final Logger logger = LoggerFactory.getLogger(MealService.class);

 public List<Meal> list(){
  return StreamSupport.stream(repo.findAll().spliterator(), false).collect(Collectors.toList());
 }

 public Meal getMealById(Integer id){
  Meal m1 = null;
  try{
    logger.debug("retrieving meal by id {}", id);
    m1 = repo.findById(id).orElseThrow();
  }catch(Exception e){
    logger.error("Repo call findById({})",id, e);
  }
  return m1;
 }

 public Meal save(Meal meal){
  logger.info("Begin saving into Meal table");
  repo.save(meal);
  logger.info("End saving into Meal");
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
