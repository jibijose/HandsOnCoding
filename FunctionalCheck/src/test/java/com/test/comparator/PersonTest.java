package com.test.comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.comparator.Person;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonTest {

  PersonBuilder personBuilder;

  @Before
  public void beforeEachTest() {
    personBuilder = new PersonBuilder();
  }

  @Test
  public void checkCompareSamePersons() {
    Person person_first = personBuilder.constructPerson1();
    Person person_second = personBuilder.constructPerson1();

    assertEquals("Should match hashcode", person_first.hashCode(), person_second.hashCode());
    assertEquals("Should match equals", Boolean.TRUE, person_first.equals(person_second));
  }

  @Test
  public void checkCompareDifferentPersons() {
    Person person1 = personBuilder.constructPerson1();
    Person person2 = personBuilder.constructPerson2();

    assertNotEquals("Should not match hashcode", person1.hashCode(), person2.hashCode());
    assertNotEquals("Should not match equals", Boolean.TRUE, person1.equals(person2));
  }
}
