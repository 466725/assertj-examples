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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.examples.neo4j;

import org.junit.Test;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;

import static org.assertj.neo4j.api.Assertions.assertThat;

public class ResourceIteratorAssertionExamples extends Neo4jAssertionExamples {

  @Test
  public void resource_iterator_assertion_examples() {
    try (Transaction tx = graphDB.beginTx()) {
        // let us find the character names created from fusions
        ResourceIterator<String> fusionsIterator = dragonBallGraph.fusionCharactersIterator();

        // this consumes the entire iterator
        // therefore, you cannot chain this assertion with others
        assertThat(fusionsIterator).hasSize(4);

        // let us call again the same method to get a brand new resource iterator
        fusionsIterator = dragonBallGraph.fusionCharactersIterator();

        // the following assertions can be chained
        // *so long as* the specified index increases
        assertThat(fusionsIterator)
                .containsAtRow(0, "Gogeta")
                .containsAtRow(1, "Prilin")
                .containsAtRow(2, "Tiencha")
                .containsAtRow(3, "Veku");

        tx.close();
    }
  }
}
