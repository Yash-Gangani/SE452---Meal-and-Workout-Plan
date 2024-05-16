package com.mealplan.project.person;

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
public class PersonControllerTest {
 private static final String url = "/persons";
 
 @Autowired
 private PersonRepository repo;

 @Autowired
 private MockMvc mvc;

 @Autowired
 private ObjectMapper objectMapper;


 @Test
 public void getAllPersons() throws Exception {
  ResultActions response = mvc.perform(MockMvcRequestBuilders.get(url));

  var count = (int) repo.count();
  response.andExpect(MockMvcResultMatchers.status().isOk());
  response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(count)));
 }

 public void getPersonByID(){
  
 }

 @Test
 public void testAddPerson() throws Exception{
  var b4Count = (int) repo.count();
  Person p = Person.builder().name("Harry").age(29).gender(Gender.M).build();
  String personAsJson = objectMapper.writeValueAsString(p);

  var request = MockMvcRequestBuilders.post(url);
  request.contentType(MediaType.APPLICATION_JSON);
  request.content(personAsJson);

  ResultActions response = mvc.perform(request);

  var jsonResponse = response.andReturn().getResponse().getContentAsString();
  var afterCount = (int) repo.count();
  Person updatedPerson = new ObjectMapper().readValue(jsonResponse, Person.class);
  response.andExpect(MockMvcResultMatchers.status().isOk());
  
  assertEquals("Harry", updatedPerson.getName());
  assertEquals(afterCount, b4Count+1);
 }


 @Test
 public void testDeletePerson() throws Exception{
    Person p = Person.builder().name("Harry").age(29).build();
    repo.save(p);
    
    
    long b4Count = repo.count();

    var request = MockMvcRequestBuilders.delete(url+"/"+p.getId());
    ResultActions response = mvc.perform(request);

    long afterCount = repo.count();
    response.andExpect(MockMvcResultMatchers.status().isOk());
    assertEquals(b4Count-1, afterCount);

 }
}
