package org.fest.assertions.examples;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.entry;
import static org.fest.assertions.examples.data.Ring.nenya;
import static org.fest.assertions.examples.data.Ring.oneRing;

import org.fest.assertions.examples.data.Ring;
import org.junit.Test;

/**
 * Map assertions example.
 *
 * @author Joel Costigliola
 */
public class MapAssertionsExamples extends AbstractAssertionsExamples {

  @Test
  public void map_assertions_examples() {
    // ringBearers is a Map of TolkienCharacter indexed by the Ring they are wearing.
    assertThat(ringBearers).isNotEmpty().hasSize(4);
    
    // note the usage of Assertions.entry(key, value) synthetic sugar for better readability (similar to MapEntry.entry(key, value)). 
    assertThat(ringBearers).contains(entry(oneRing, frodo), entry(nenya, galadriel));
    assertThat(ringBearers).doesNotContain(entry(oneRing, aragorn));
    
    // Assertion on key
    assertThat(ringBearers).containsKey(Ring.nenya);
    assertThat(ringBearers).doesNotContainKey(Ring.manRing);
    
    // Assertion on value
    assertThat(ringBearers).containsValue(frodo);
    assertThat(ringBearers).doesNotContainValue(sam);
    
    
    ringBearers.clear();
    assertThat(ringBearers).isEmpty();
    
  }
  
  
}
