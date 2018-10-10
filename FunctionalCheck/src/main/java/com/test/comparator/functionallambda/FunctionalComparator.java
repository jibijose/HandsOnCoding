package com.test.comparator.functionallambda;

import com.test.comparator.Person;

@FunctionalInterface
public interface FunctionalComparator {

  boolean compare(Person a, Person b);

}
