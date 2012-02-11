package org.fest.assertions.examples;

import static java.lang.Integer.toHexString;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Fail.fail;

import org.junit.Test;

import org.fest.assertions.examples.data.Person;
import org.fest.assertions.examples.data.TolkienCharacter;

/**
 *  Assertions available for all objects.
 */
public class BasicAssertionsExamples extends AbstractAssertionsExamples {

  // the data used are initialized AbstractAssertionsExamples.

  @Test
  public void isEqualTo_isNotEqualTo_assertions_examples() {
    // the most simple assertion
    assertThat(frodo.getAge()).isEqualTo(33);
    assertThat(frodo.getName()).isEqualTo("Frodo").isNotEqualTo("Frodon");
  }

  @Test
  public void meaningful_error_with_test_description_example() {
    try {
      // set a bad name to Mr Frodo, just to see how nice is the assertion error message
      frodo.setName("Frodon");
      // you can specifiy a test description with as() method or describedAs()
      assertThat(frodo.getName()).as("check Frodo's name").isEqualTo("Frodo");
    } catch (AssertionError e) {
      assertThat(e).hasMessage("[check Frodo's name] expected:<'Frodo[]'> but was:<'Frodo[n]'>");
    }
  }

  @Test
  public void isSame_isNotSame_assertions_examples() {
    // isSame compares objects reference
    Object jake = new Person("Jake", 43);
    Object sameJake = jake;
    Object jakeClone = new Person("Jake", 43); // equals to jake but not the same
    assertThat(jake).isSameAs(sameJake).isNotSameAs(jakeClone);
  }

  @Test
  public void isIn_isNotIn_assertions_examples() {
    assertThat(frodo).isIn(fellowshipOfTheRing);
    assertThat(frodo).isIn(sam, frodo, pippin);
    assertThat(sauron).isNotIn(fellowshipOfTheRing);
  }

  @Test
  public void isNull_isNotNull_assertions_examples() {
    Object nullObject = null;
    assertThat(nullObject).isNull();
    Object nonNullObject = new Object();
    assertThat(nonNullObject).isNotNull();
  }

  @Test
  public void isInstanceOf_assertions_examples() {
    assertThat(gandalf).isInstanceOf(TolkienCharacter.class).isInstanceOfAny(Object.class, TolkienCharacter.class);
  }

  // new in FEST 2.0
  @Test
  public void assertion_error_message_differentiates_expected_and_actual_persons() throws Exception {
    // Assertion error message is built with toString description of involved objects.
    // Sometimes, objects differs but not their toString description, in that case the error message would
    // confusing because, if toString returns "Jake" for different objects, isEqualTo would return :
    // "expected:<'Jake'> but was:<'Jake'> ... How confusing !
    // In that case, Fest is smart enough and differentiates objects description by adding their class and hashCode.
    Person actual = new Person("Jake", 43);
    Person expected = new Person("Jake", 47);
    try {
      assertThat(actual).isEqualTo(expected);
    } catch (AssertionError e) {
      assertThat(e).hasMessage(
          "expected:<'Person[name=Jake] (Person@" + toHexString(expected.hashCode())
              + ")'> but was:<'Person[name=Jake] (Person@" + toHexString(actual.hashCode()) + ")'>");
      return;
    }
    fail("AssertionError expected");
  }

}
