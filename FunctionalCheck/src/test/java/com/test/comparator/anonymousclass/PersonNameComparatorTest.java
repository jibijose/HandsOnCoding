package com.test.comparator.anonymousclass;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.comparator.Person;
import com.test.comparator.PersonBuilder;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonNameComparatorTest {

  PersonBuilder personBuilder;
  Person[] personArray;

  @Before
  public void beforeEachTest() {
    personBuilder = new PersonBuilder();
    personArray = personBuilder.constructPersonsRandom();
  }

  @Test
  public void checkComparePersons() {
    Person[] persons = personBuilder.constructPersonsRandom();

    Arrays.sort(persons, new Comparator<Person>() {
      @Override
      public int compare(Person person1, Person person2) {
        return person1.getName().compareTo(person2.getName());
      }
    });

    Person[] personsSortedByName = personBuilder.constructPersonsSortedByName();
    assertArrayEquals("Should Array compare by age", personsSortedByName, persons);
  }
}
