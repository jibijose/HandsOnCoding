package comtest.comparator.objectoriented;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.test.comparator.Person;
import com.test.comparator.PersonBuilder;
import com.test.comparator.objectoriented.PersonNameComparator;

@RunWith(BlockJUnit4ClassRunner.class)
public class PersonNameComparatorTest {

  PersonBuilder personBuilder;
  PersonNameComparator personNameComparator;
  Person[] personArray;

  @Before
  public void beforeEachTest() {
    personBuilder = new PersonBuilder();
    personNameComparator = new PersonNameComparator();
    personArray = personBuilder.constructPersonsRandom();
  }

  @Test
  public void checkComparePersons() {
    Person[] persons = personBuilder.constructPersonsRandom();

    Arrays.sort(persons, personNameComparator);

    Person[] personsSortedByName = personBuilder.constructPersonsSortedByName();
    assertArrayEquals("Should Array compare by age", personsSortedByName, persons);
  }
}
