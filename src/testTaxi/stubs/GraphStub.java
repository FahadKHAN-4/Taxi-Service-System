package testTaxi.stubs;

import taxi.map.Graph;
import taxi.models.Road;

public class GraphStub extends Graph {
	public GraphStub() {
		super(new Road[][] {});
	}

	@Override
	public int getNodes() {
		return 10;
	}
}
