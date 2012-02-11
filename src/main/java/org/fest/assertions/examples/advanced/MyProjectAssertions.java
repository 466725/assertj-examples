package org.fest.assertions.examples.advanced;

import org.fest.assertions.api.Assertions;
import org.fest.assertions.examples.data.TolkienCharacter;


/**
 * A single entry point for all assertions, Fest standard assertions and MyProject custom assertions.
 * <p>
 * With  MyProjectAssertions.assertThat sttaic import, you will access all possible assertions (standard and custom ones)
 */
public class MyProjectAssertions extends Assertions { // extending make all standard Fest assertions available.  

  // add the custom assertions related to MyProject
  
  public static TolkienCharacterAssert assertThat(TolkienCharacter actual) {
    return new TolkienCharacterAssert(actual);
  }
  

}
