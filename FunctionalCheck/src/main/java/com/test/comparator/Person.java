package com.test.comparator;

import static java.util.Objects.hash;

import java.time.LocalDate;
import java.util.Objects;

public class Person {

  public Person(String name, LocalDate birthday, Gender gender, String emailAddress) {
    this.name = name;
    this.birthday = birthday;
    this.gender = gender;
    this.emailAddress = emailAddress;
  }

  String name;
  LocalDate birthday;
  Gender gender;
  String emailAddress;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /************************************************************************************************/
  @Override
  public String toString() {
    return "Person [name=" + name + ", birthday=" + birthday + ", gender=" + gender
        + ", emailAddress=" + emailAddress + "]";
  }

  @Override
  public int hashCode() {
    return hash(name, birthday, gender, emailAddress);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Person other = (Person) obj;
    return Objects.equals(other.name, name) && Objects.equals(other.birthday, birthday)
        && Objects.equals(other.gender, gender) && Objects.equals(other.emailAddress, emailAddress);
  }

  /************************************************************************************************/

  public static int compareByName(Person a, Person b) {
    return a.getName().compareTo(b.getName());
  }

  public static int compareByAge(Person a, Person b) {
    return a.getBirthday().compareTo(b.getBirthday());
  }
}
