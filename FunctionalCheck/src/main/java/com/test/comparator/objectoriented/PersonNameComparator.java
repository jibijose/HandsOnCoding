package com.test.comparator.objectoriented;

import java.util.Comparator;

import com.test.comparator.Person;

public class PersonNameComparator implements Comparator<Person> {
  public int compare(Person a, Person b) {
    return a.getName().compareTo(b.getName());
  }
}
