package com.test.comparator;

import java.time.LocalDate;

import com.test.comparator.Gender;
import com.test.comparator.Person;

public class PersonBuilder {

  private static LocalDate DATENOW = LocalDate.now();

  public Person constructPerson(int marker) {
    return constructPerson(marker, marker, marker);
  }

  public Person constructPerson(int nameMarker, int ageMarker, int emailMarker) {
    return new Person("name" + nameMarker, DATENOW.plusYears(ageMarker), Gender.MALE,
        "email" + emailMarker + "@email.com");
  }

  public Person constructPerson1() {
    return constructPerson(1);
  }

  public Person constructPerson2() {
    return constructPerson(2);
  }

  public Person[] constructPersonsRandom() {
    Person[] rosterAsArray = new Person[5];
    rosterAsArray[0] = constructPerson(3);
    rosterAsArray[1] = constructPerson(1);
    rosterAsArray[2] = constructPerson(2);
    rosterAsArray[3] = constructPerson(5);
    rosterAsArray[4] = constructPerson(4);

    return rosterAsArray;
  }

  public Person[] constructPersonsSortedByName() {
    Person[] rosterAsArray = new Person[5];
    rosterAsArray[0] = constructPerson(1);
    rosterAsArray[1] = constructPerson(2);
    rosterAsArray[2] = constructPerson(3);
    rosterAsArray[3] = constructPerson(4);
    rosterAsArray[4] = constructPerson(5);

    return rosterAsArray;
  }

  public Person[] constructPersonsSortedByAge() {
    Person[] rosterAsArray = new Person[5];
    rosterAsArray[0] = constructPerson(1);
    rosterAsArray[1] = constructPerson(2);
    rosterAsArray[2] = constructPerson(3);
    rosterAsArray[3] = constructPerson(4);
    rosterAsArray[4] = constructPerson(5);

    return rosterAsArray;
  }

}
