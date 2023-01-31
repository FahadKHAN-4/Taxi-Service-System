package testTaxi.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static taxi.utils.Constants.NODES;
import static taxi.utils.Constants.SEED;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import taxi.map.Graph;
import taxi.models.Road;

public class TestGraph {
  Graph graph = new Graph(NODES, SEED);
  Road[][] distanceMatrix = graph.getDistanceMatrix();
  int numNodes = graph.getNodes();

  @Test
  public void testEdgeValidity() {
    boolean pass = true;
    outerloop: for (int i = 0; i < numNodes; i++) {
      for (int j = 0; j < numNodes; j++) {
        if (distanceMatrix[i][j] == null && distanceMatrix[j][i] == null)
          continue;
        if (distanceMatrix[i][j] != distanceMatrix[j][i]) {
          pass = false;
          break outerloop;
        }
      }
    }
    assertTrue(null, pass);
  }

  @Test
  public void testEdgeNumber() {
    int numDegree = 0;
    for (int i = 0; i < numNodes; i++) {
      for (int j = 0; j < numNodes; j++) {
        if (distanceMatrix[i][j] != null)
          numDegree++;
      }
    }
    boolean pass = numDegree <= numNodes * (numNodes - 1) && numDegree >= numNodes - 1;
    assertTrue(null, pass);
  }

  @Test
  public void testConnectivity() {
    Queue<Integer> queue = new LinkedList<Integer>();
    boolean[] visited = new boolean[numNodes];
    int count = 1;
    queue.add(0);
    visited[0] = true;
    while (!queue.isEmpty()) {
      int currentNode = queue.remove();
      for (int i = 0; i < numNodes; i++) {
        if (distanceMatrix[currentNode][i] != null && !visited[i]) {
          queue.add(i);
          visited[i] = true;
          count++;
        }
      }
    }
    assertEquals(count, numNodes);
  }

  @Test
  public void testPrint() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    graph.printDistanceMatrix();
    String print = " N  7  N  37  18  6  N  N  N  N  N  27  N  20  N \n"
        + " 7  N  N  N  3  35  34  6  N  N  N  N  N  N  48 \n"
        + " N  N  N  N  N  24  21  0  30  37  49  N  N  23  N \n"
        + " 37  N  N  N  N  N  N  46  29  N  44  33  N  38  N \n"
        + " 18  3  N  N  N  N  N  N  8  18  N  N  N  N  N \n"
        + " 6  35  24  N  N  N  7  N  36  28  40  18  N  N  16 \n"
        + " N  34  21  N  N  7  N  14  27  43  1  31  5  38  19 \n"
        + " N  6  0  46  N  N  14  N  29  23  31  N  17  N  40 \n"
        + " N  N  30  29  8  36  27  29  N  8  38  N  38  5  N \n"
        + " N  N  37  N  18  28  43  23  8  N  N  N  N  4  25 \n"
        + " N  N  49  44  N  40  1  31  38  N  N  N  36  12  37 \n"
        + " 27  N  N  33  N  18  31  N  N  N  N  N  N  N  N \n"
        + " N  N  N  N  N  N  5  17  38  N  36  N  N  N  N \n"
        + " 20  N  23  38  N  N  38  N  5  4  12  N  N  N  12 \n"
        + " N  48  N  N  N  16  19  40  N  25  37  N  N  12  N \n";
    assertEquals(print, out.toString());
  }
}
