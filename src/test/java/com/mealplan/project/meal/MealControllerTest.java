package com.mealplan.project.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    ResultActions response = mvc.perform(MockMvcRequestBuilders.get(url+"/1"));
    response.andExpect(MockMvcResultMatchers.status().isOk());

    var jsonResponse = response.andReturn().getResponse().getContentAsString();
    Meal meal = objectMapper.readValue(jsonResponse, Meal.class);
    assertEquals(1000, meal.getNutrition().getCalories());
    
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

    response.andExpect(MockMvcResultMatchers.status().isOk());

    assertEquals(10,meal.getId());
    assertEquals(2500, meal.getNutrition().getCalories());
  }

  @Test
  public void testDeleteMeal() throws Exception{
    long before = repo.count();

    var request = MockMvcRequestBuilders.delete(url+"/2");
    ResultActions response = mvc.perform(request);

    long after = repo.count();
    assertEquals(before-1, after);
   }
}
