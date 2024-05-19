package com.mealplan.project.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonServiceTest {
  
  @Autowired
  private PersonService service;

  @Test
  public void testAddPerson(){

    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    Person p2 = new Person();
    p2.setName("Sally");
    p2.setAge(25);
    Person p3 = new Person();
    p3.setName("Jimmy");
    p3.setAge(65);

    long sizeB4 = service.list().size();

    service.save(p1);
    service.save(p2);
    service.save(p3);

    assertEquals(sizeB4+3, service.list().size());
  }

  @Test
  public void testGetAndUpdatePerson(){
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
  
    service.save(p1);

    Person p2 = service.getPersonById(1);
    p2.setAge(45);
    service.save(p2);

    Person p3 = service.getPersonById(1);

    assertEquals("Harry", p3.getName());
    assertEquals(45, p3.getAge());
  }

  @Test
  public void testDeletePerson(){
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    Person p2 = new Person();
    p2.setName("Sally");
    p2.setAge(25);
    Person p3 = new Person();
    p3.setName("Jimmy");
    p3.setAge(65);

    long sizeB4 = service.list().size();

    service.save(p1);
    service.save(p2);
    service.save(p3);

    assertEquals(sizeB4+3, service.list().size());

    service.delete(p3);

    assertEquals(sizeB4+2, service.list().size());;

  }

  @Test
  public void testDeletePersonById(){
    Person p1 = new Person();
    p1.setName("Harry");
    p1.setAge(29);
    Person p2 = new Person();
    p2.setName("Sally");
    p2.setAge(25);
    Person p3 = new Person();
    p3.setName("Jimmy");
    p3.setAge(65);

    long sizeB4 = service.list().size();

    service.save(p1);
    service.save(p2);
    service.save(p3);

    assertEquals(sizeB4+3, service.list().size());

    service.deleteById(p3.getId());

    assertEquals(sizeB4+2, service.list().size());;

  }
  
}
