package taxi.algorithm;

import java.util.ArrayList;

import taxi.map.Graph;
import taxi.models.Road;

public class ShortestPath {
	private static final int NO_PARENT = -1;
	private double[] distances;
	private int[] parents;
	private Graph map;
	private int source;

	public ShortestPath(Graph map) {
		this.map = map;
		int nVertices = map.getNodes();
		parents = new int[nVertices];
		distances = new double[nVertices];
		for (int i = 0; i < nVertices; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		setSource(0);
	}

	public ShortestPath(int source, Graph map) {
		this.map = map;
		int nVertices = map.getNodes();
		parents = new int[nVertices];
		distances = new double[nVertices];
		for (int i = 0; i < nVertices; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		setSource(source);
	}

	public void setSource(int source) {
		int nVertices = map.getNodes();
		double[] distances = new double[nVertices];
		boolean[] added = new boolean[nVertices];
		for (int i = 0; i < nVertices; i++) {
			distances[i] = Integer.MAX_VALUE;
			added[i] = false;
		}
		distances[source] = 0;
		int[] parents = new int[nVertices];
		parents[source] = NO_PARENT;
		for (int i = 1; i < nVertices; i++) {
			int nearestVertex = -1;
			double shortestDistance = Integer.MAX_VALUE;
			for (int idx = 0; idx < nVertices; idx++) {
				if (!added[idx] && distances[idx] < shortestDistance) {
					nearestVertex = idx;
					shortestDistance = distances[idx];
				}
			}
			added[nearestVertex] = true;
			for (int idx = 0; idx < nVertices; idx++) {
				Road[][] adjacencyMatrix = map.getDistanceMatrix();
				if (adjacencyMatrix[nearestVertex][idx] != null) {
					double edgeDistance = adjacencyMatrix[nearestVertex][idx].getDistance();
					if ((shortestDistance + edgeDistance) < distances[idx]) {
						parents[idx] = nearestVertex;
						distances[idx] = shortestDistance + edgeDistance;
					}
				}
			}
		}
		this.source = source;
		this.distances = distances;
		this.parents = parents;
	}

	private void getPathHelper(int currentVertex, ArrayList<Integer> res) {
		if (currentVertex == NO_PARENT)
			return;
		getPathHelper(parents[currentVertex], res);
		res.add(currentVertex);
	}

	public ArrayList<Integer> getPath(int currentVertex) {
		ArrayList<Integer> nodes = new ArrayList<Integer>();
		getPathHelper(currentVertex, nodes);
		return nodes;
	}

	private void printPath(int currentVertex) {
		if (currentVertex == NO_PARENT)
			return;
		printPath(parents[currentVertex]);
		System.out.print(currentVertex + " ");
	}

	public void printSolution(int destination) {
		System.out.print("Route\t\t\t Distance\t\t Path");
		System.out.print("\n" + source + " -> ");
		System.out.print(destination + " \t\t\t ");
		System.out.print(distances[destination] + " \t\t\t ");
		printPath(destination);
		System.out.print("\n");
	}

	public double getShortestPath(int destination) {
		return distances[destination];
	}

	// public static void main(String[] args) {
	// Graph g = new Graph(10, 2);
	// int start = 3;
	//
	// ShortestPath sp = new ShortestPath(3, g);
	// System.out.println(sp.getPath(0).toString());
	// System.out.println(sp.getPath(2).toString());
	// System.out.println(sp.getPath(4).toString());
	// System.out.println(sp.getPath(5).toString());
	// System.out.println(sp.getPath(9).toString());
	//
	// System.out.println(sp.getShortestPath(2));
	// System.out.println(sp.getShortestPath(4));
	// System.out.println(sp.getShortestPath(5));
	// System.out.println(sp.getShortestPath(9));
	// for (int i = 0; i < g.getDistanceMatrix().length; i++) {
	// System.out.print("{");
	// for (int j = 0; j < g.getDistanceMatrix()[0].length; j++) {
	// if (g.getDistanceMatrix()[i][j] == null) {
	// System.out.print("null, ");
	// }
	// else {
	// System.out.printf("new Road(%.1f), ",
	// g.getDistanceMatrix()[i][j].getDistance());
	// }
	// }
	// System.out.print("},");
	// System.out.println();
	// }
	// }
}
