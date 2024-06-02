package com.mealplan.project.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class MealControllerTest {
  private static final String url = "/meal";

  @Autowired
  private MealRepository repo;
  @Autowired
  private NutritionRepository repoN;

  @Autowired
  private MockMvc mvc;

  @Autowired 
  private ObjectMapper objectMapper;


  @Test
  public void testGetAllMeals() throws Exception {
    ResultActions response = mvc.perform(MockMvcRequestBuilders.get(url));

    var count = (int) repo.count();
    response.andExpect(MockMvcResultMatchers.status().isOk());
    response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(count)));
 
  }

  @Test
  public void testGetMealById() throws Exception{
    ResultActions response = mvc.perform(MockMvcRequestBuilders.get(url));
    response.andExpect(MockMvcResultMatchers.status().isOk());

    var jsonResponse = response.andReturn().getResponse().getContentAsString();
    ArrayList<Meal> mealList = objectMapper.readValue(jsonResponse, new TypeReference<ArrayList<Meal>>(){});
    Integer id = mealList.get(0).getId();
    Integer caloriesTest = mealList.get(0).getNutrition().getCalories();
    response = mvc.perform(MockMvcRequestBuilders.get(url+"/"+id));
    jsonResponse = response.andReturn().getResponse().getContentAsString();
    Meal meal =  objectMapper.readValue(jsonResponse, Meal.class);
    assertEquals(caloriesTest, meal.getNutrition().getCalories());
    
  }

  @Test
  public void testAddMeal() throws Exception{
    var before = (int) repo.count();
    Nutrition n1 = Nutrition.builder().calories(2500).fat(30).sugar(25).build();
    repoN.save(n1);
    Meal m1 = Meal.builder().t(MealType.NORMAL).nutrition(n1).build();
    String mealAsJson = objectMapper.writeValueAsString(m1);

    var request = MockMvcRequestBuilders.post(url);
    request.contentType(MediaType.APPLICATION_JSON);
    request.content(mealAsJson);

    ResultActions response = mvc.perform(request);

    var jsonResponse = response.andReturn().getResponse().getContentAsString();
    Meal meal = objectMapper.readValue(jsonResponse, Meal.class);
    Meal m2 = repo.findById(meal.getId()).orElse(null);

    response.andExpect(MockMvcResultMatchers.status().isOk());

    assertEquals(before+1, repo.count());
    assertEquals(meal, m2);
    
  }

  @Test
  public void testDeleteMeal() throws Exception{
    long before = repo.count();
    ResultActions response = mvc.perform(MockMvcRequestBuilders.get(url));
    response.andExpect(MockMvcResultMatchers.status().isOk());
    var jsonResponse = response.andReturn().getResponse().getContentAsString();

    ArrayList<Meal> mealList = objectMapper.readValue(jsonResponse, new TypeReference<ArrayList<Meal>>(){});
    Integer id = mealList.get(0).getId();
    
    var request = MockMvcRequestBuilders.delete(url+"/"+id);
    response = mvc.perform(request);

    long after = repo.count();
    assertEquals(before-1, after);
   }
}
