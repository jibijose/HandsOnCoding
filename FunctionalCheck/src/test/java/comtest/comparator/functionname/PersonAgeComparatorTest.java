package comtest.comparator.functionname;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.comparator.Person;
import com.test.comparator.PersonBuilder;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonAgeComparatorTest {

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
    
    Arrays.sort(persons, (Person a, Person b) -> {
      return Person.compareByAge(a, b);
    });

    Arrays.sort(persons, Person::compareByAge);

    Person[] personsSortedByAge = personBuilder.constructPersonsSortedByAge();
    assertArrayEquals("Should Array compare by age", personsSortedByAge, persons);
  }
}
