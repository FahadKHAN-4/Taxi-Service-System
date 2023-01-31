package taxi.map;

import taxi.algorithm.DisjointSet;
import taxi.models.Road;
import java.util.Random;

public class Graph {
	private int nodes;
	private Road distanceMatrix[][];

	public Graph(int n, int seed) {
		this.nodes = n;
		this.distanceMatrix = generateGraph(n, seed);
	}
	
	public Graph(Road[][] distanceMatrix) {
		this.nodes = distanceMatrix.length;
		this.distanceMatrix = distanceMatrix;
	}
	
	public int getNodes() {
		return nodes;
	}

	public Road[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public void printDistanceMatrix() {
		for (int i = 0; i < nodes; i++) {
			for (int j = 0; j < nodes; j++) {
				if (distanceMatrix[i][j] != null)
					System.out.print(" " + (int) (distanceMatrix[i][j].getDistance()) + " ");
				else
					System.out.print(" N ");
			}
			System.out.print("\n");
		}
	}

	// a helper function to connect 2 nodes
	private void connect(Road[][] distanceMatrix, int node1, int node2, Random rand) {
		Road road = new Road((int)(rand.nextDouble() * 50), rand.nextDouble());
		distanceMatrix[node1][node2] = road;
		distanceMatrix[node2][node1] = road;
	}

	// a function to generate a random graph
	private Road[][] generateGraph(int n, long seed) {
		Road[][] distanceMatrix = new Road[n][n];
		Random rand = new Random(seed);
		DisjointSet disjointSet = new DisjointSet(n);

		// create a random minimum spanning tree
		while (true) {
			if (!disjointSet.isForest())
				break;
			int node1 = rand.nextInt(n);
			int node2 = rand.nextInt(n);
			if (disjointSet.unite(node1, node2))
				connect(distanceMatrix, node1, node2, rand);
		}

		// populate the graph with a random number of additional edges
		int i = 0, additionalEdges = (int) (rand.nextDouble() * (n * (n - 1) / 2 - n + 1));
		while (i < additionalEdges) {
			int node1 = rand.nextInt(n);
			int node2 = rand.nextInt(n);
			if (node1 != node2 && distanceMatrix[node1][node2] == null) {
				connect(distanceMatrix, node1, node2, rand);
				i++;
			}
		}
		return distanceMatrix;
	}
}
