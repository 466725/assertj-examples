package org.fest.assertions.examples;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

/**
 * String assertions specific examples
 *
 * @author Joel Costigliola
 */
public class StringAssertionsExamples extends AbstractAssertionsExamples {

  @Test
  public void string_assertions_examples() {
    
    assertThat("Frodo").startsWith("Fro").endsWith("do").hasSize(5);
    assertThat("Frodo").contains("rod").doesNotContain("fro");
    
    // you can ignore case for equals check
    assertThat("Frodo").isEqualToIgnoringCase("FROdO");
    
    // using regex
    assertThat("Frodo").matches("..o.o").doesNotMatch(".*d");
    
    // check for empty string, or not.
    assertThat("").isEmpty();
    assertThat("").isNullOrEmpty();
    assertThat("not empty").isNotEmpty();
  }

  @Test
  public void string_assertions_with_custom_comparison_examples() {
    
    // standard assertion are based on equals() method, but what if you want to ignore String case ?
    // one way is to use isEqualToIgnoringCase
    assertThat("Frodo").isEqualToIgnoringCase("FROdO");
    // this is good but it doest not work with the other assertions : contains, startsWith, ...
    // if you want consistent ignoring case comparison, you must use a case iNsenSiTIve comparison strategy :
    // We see that assertions succeeds even though case is not the same - ok that was the point ;-)
    assertThat("Frodo").usingComparator(caseInsensitiveStringComparator).startsWith("fr").endsWith("ODO");
    
    // All assertions made after usingComparator(caseInsensitiveStringComparator) are based on the given comparator ...
    assertThat("Frodo").usingComparator(caseInsensitiveStringComparator).contains("fro").doesNotContain("don");
    // ... but a assertions is not
    // assertThat("Frodo").startsWith("fr").endsWith("ODO"); // FAILS !!!
    
    // Last thing : matches or doesNotMatch don't use the given comparator
    assertThat("Frodo").usingComparator(caseInsensitiveStringComparator).doesNotMatch("..O.O");
    try {
      // failed assertion with specific comparator mention the comparison strategy to help interpreting the error
      assertThat("Frodo").usingComparator(caseInsensitiveStringComparator).startsWith("FROO");
    } catch (AssertionError e) {
      assertThat(e).hasMessage(
          "expecting:<'Frodo'> to start with:<'FROO'> according to 'CaseInsensitiveStringComparator' comparator");
    }
  }

}
