/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.examples;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.presentation.StandardRepresentation.STANDARD_REPRESENTATION;

import org.assertj.core.api.Assertions;
import org.assertj.examples.representation.CustomRepresentation;
import org.assertj.examples.representation.CustomRepresentation.Example;
import org.junit.After;
import org.junit.Test;

public class RepresentationExamples extends AbstractAssertionsExamples {

  @After
  public void afterTest() {
    Assertions.useRepresentation(STANDARD_REPRESENTATION);
  }
  
  @Test
  public void should_use_given_representation_in_assertion_error_messages() {
    
    Assertions.useRepresentation(new CustomRepresentation());
    Example example = new Example();
    // this assertion fails with error : "expected:<[null]> but was:<[Example]>"
    try {
      assertThat(example).isNull(); // example is not null !
    } catch (AssertionError e1) {
      assertThat(e1).hasMessageContaining("EXAMPLE");
    }
    
    try {
      assertThat("foo").startsWith("bar");
    } catch (AssertionError e) {
      assertThat(e).hasMessageContaining("$foo$")
                   .hasMessageContaining("$bar$");

      Assertions.useRepresentation(STANDARD_REPRESENTATION);
      try {
        assertThat("foo").startsWith("bar");
      } catch (AssertionError e2) {
        assertThat(e2).hasMessageContaining("\"foo\"")
                      .hasMessageContaining("\"bar\"");
      }
    }
  }
  
}
