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
package org.handson;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.examples.data.Race.HOBBIT;
import static org.assertj.examples.data.Ring.dwarfRing;
import static org.assertj.examples.data.Ring.manRing;
import static org.assertj.examples.data.Ring.narya;
import static org.assertj.examples.data.Ring.nenya;
import static org.assertj.examples.data.Ring.oneRing;
import static org.assertj.examples.data.Ring.vilya;

import org.assertj.examples.AbstractAssertionsExamples;
import org.assertj.examples.data.Ring;
import org.assertj.examples.data.TolkienCharacter;
import org.junit.Test;

public class AssertionsDemo extends AbstractAssertionsExamples {

	// the data used are initialized in AbstractAssertionsExamples.

	@Test
	public void basic_assertions_examples() {

		assertThat(this.frodo.age).isEqualTo(33);

		assertThat(this.frodo.getName())
				.as("Checking Mr Frodo's name")
				.isNotNull()
				.isEqualTo("Frodo")
				.isNotEqualTo("Frodon");

		assertThat(this.frodo).isInstanceOf(TolkienCharacter.class)
				.isIn(this.fellowshipOfTheRing)
				.isIn(this.sam, this.frodo, this.pippin);

		assertThat(this.sauron).isNotIn(this.fellowshipOfTheRing);
	}

	@Test
	public void collection_assertions_examples() {

		final Iterable<Ring> elvesRings = newArrayList(vilya, nenya, narya);

		assertThat(elvesRings)
				.isNotEmpty()
				.hasSize(3)
				.contains(nenya)
				.doesNotContain(oneRing)
				// order does not matters
				.containsOnly(nenya, vilya, narya)
				// order matters
				.containsExactly(vilya, nenya, narya);

		// you can also check the start or end of your collection/iterable
		final Iterable<Ring> allRings = newArrayList(oneRing, vilya, nenya, narya, dwarfRing, manRing);
		assertThat(allRings)
				.startsWith(oneRing, vilya)
				.endsWith(dwarfRing, manRing)
				.containsAll(elvesRings)
				.containsSequence(nenya, narya, dwarfRing)
				.containsSubsequence(oneRing, nenya, dwarfRing)
				.doesNotContainSequence(vilya, nenya, oneRing, narya);
	}

	@Test
	public void collection_filter_and_extracting_examples() {

		assertThat(this.fellowshipOfTheRing)
				.filteredOn(tc -> tc.getRace().equals(HOBBIT))
				.containsOnly(this.sam, this.frodo, this.pippin, this.merry);

		assertThat(this.fellowshipOfTheRing)
				.filteredOn(tc -> tc.getRace().equals(HOBBIT))
				.extracting(tc -> tc.getName())
				.containsOnly("Sam", "Frodo", "Pippin", "Merry");

	}

}
