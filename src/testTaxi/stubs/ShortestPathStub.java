package testTaxi.stubs;

import java.util.ArrayList;
import java.util.Arrays;

import taxi.algorithm.ShortestPath;

public class ShortestPathStub extends ShortestPath {
	private Integer[] path;
	private int source;

	public ShortestPathStub() {
		super(new GraphStub());
		this.path = new Integer[] {};
	}

	public ShortestPathStub(int source) {
		super(source, new GraphStub());
		this.source = source;
		this.path = new Integer[] {};
	}

	public ShortestPathStub(Integer[] path) {
		super(new GraphStub());
		this.path = path;
	}

	@Override
	public ArrayList<Integer> getPath(int currentVertex) {
		return new ArrayList<Integer>(Arrays.asList(path));
	}

	@Override
	public double getShortestPath(int location) {
		if (location == source)
			return 0;
		switch (location) {
			case 1:
				return 51;
			case 2:
				return 25;
			case 3:
				return 101;
			case 4:
				return 50;
			case 5:
				return 60;
			case 6:
				return 10;
			case 7:
				return 10;
			case 8:
				return 10;
			case 9:
				return 10;
			case 10:
				return 10;
			case 11:
				return 10;
			case 12:
				return 50;
			default:
				return 20;

		}
	}

	@Override
	public void setSource(int source) {
		this.source = source;
	}

	@Override
	public void printSolution(int destination) {
		return;
	}
}
