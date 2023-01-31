package testTaxi.algorithm;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import taxi.algorithm.DisjointSet;

public class TestDisjointSet {
  DisjointSet disjointSet = new DisjointSet(5);

  @Test
  public void testUniteA() {
    assertFalse(disjointSet.unite(0, 0));
  }

  @Test
  public void testUniteB() {
    assertTrue(disjointSet.unite(0, 1));
  }

  @Test
  public void testUniteC() {
    disjointSet.unite(0, 3);
    disjointSet.unite(2, 4);
    disjointSet.unite(0, 4);
    assertEquals(disjointSet.find(0), disjointSet.find((2)));
  }

  @Test
  public void testIsForestA() {
    disjointSet.unite(0, 3);
    disjointSet.unite(2, 4);
    disjointSet.unite(0, 4);
    assertTrue(disjointSet.isForest());
  }

  @Test
  public void testIsForestB() {
    for (int i = 0; i < disjointSet.getSize() - 1; i++) {
      disjointSet.unite(i, i + 1);
    }
    assertFalse(disjointSet.isForest());
  }
}
