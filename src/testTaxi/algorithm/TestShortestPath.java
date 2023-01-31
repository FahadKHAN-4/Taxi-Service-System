package testTaxi.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import taxi.algorithm.ShortestPath;
import taxi.map.Graph;
import taxi.models.Road;

public class TestShortestPath {
	private Road[][] distanceMatrix1 = new Road[][] {
			{ null, new Road(25.0), new Road(45.0), null, new Road(34.0), new Road(22.0), new Road(36.0), new Road(44.0),
					new Road(21.0), null },
			{ new Road(25.0), null, new Road(21.0), new Road(3.0), null, new Road(9.0), new Road(20.0), new Road(13.0),
					new Road(42.0), new Road(16.0) },
			{ new Road(45.0), new Road(21.0), null, new Road(15.0), null, new Road(15.0), new Road(49.0), new Road(6.0),
					new Road(39.0), new Road(29.0) },
			{ null, new Road(3.0), new Road(15.0), null, new Road(14.0), null, new Road(28.0), null, null, null },
			{ new Road(34.0), null, null, new Road(14.0), null, null, new Road(37.0), new Road(25.0), new Road(23.0),
					new Road(28.0) },
			{ new Road(22.0), new Road(9.0), new Road(15.0), null, null, null, new Road(47.0), new Road(20.0), new Road(5.0),
					new Road(13.0) },
			{ new Road(36.0), new Road(20.0), new Road(49.0), new Road(28.0), new Road(37.0), new Road(47.0), null,
					new Road(47.0), new Road(38.0), new Road(25.0) },
			{ new Road(44.0), new Road(13.0), new Road(6.0), null, new Road(25.0), new Road(20.0), new Road(47.0), null, null,
					new Road(32.0) },
			{ new Road(21.0), new Road(42.0), new Road(39.0), null, new Road(23.0), new Road(5.0), new Road(38.0), null, null,
					new Road(35.0) },
			{ null, new Road(16.0), new Road(29.0), null, new Road(28.0), new Road(13.0), new Road(25.0), new Road(32.0),
					new Road(35.0), null },
	};

	@Test
	public void testShortestPath1() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(1, g);
		int path = (int) sp.getShortestPath(2);
		assertEquals(18, path);
	}

	@Test
	public void testShortestPath2() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(1, g);
		int path = (int) sp.getShortestPath(4);
		assertEquals(17, path);
	}

	@Test
	public void testShortestPath3() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(1, g);
		int path = (int) sp.getShortestPath(6);
		assertEquals(20, path);
	}

	@Test
	public void testShortestPath4() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(1, g);
		int path = (int) sp.getShortestPath(8);
		assertEquals(14, path);
	}

	@Test
	public void testShortestPath5() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(1, g);
		int path = (int) sp.getShortestPath(0);
		assertEquals(25, path);
	}

	@Test
	public void testShortestPath6() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(0);
		assertEquals(28, path);
	}

	@Test
	public void testShortestPath7() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(2);
		assertEquals(15, path);
	}

	@Test
	public void testShortestPath8() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(4);
		assertEquals(14, path);
	}

	@Test
	public void testShortestPath9() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(5);
		assertEquals(12, path);
	}

	@Test
	public void testShortestPath10() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(9);
		assertEquals(19, path);
	}

	@Test
	public void testShortestPath11() {
		Graph g = new Graph(distanceMatrix1);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(3);
		assertEquals(0, path);
	}

	private Road[][] distanceMatrix2 = new Road[][] {
			{ null, null, null, new Road(34.0), null, null, null, new Road(28.0), null, null },
			{ null, null, null, null, null, null, null, null, new Road(30.0), null },
			{ null, null, null, null, null, new Road(20.0), null, null, new Road(45.0), null },
			{ new Road(34.0), null, null, null, null, null, null, null, null, null },
			{ null, null, null, null, null, null, new Road(37.0), null, null, null },
			{ null, null, new Road(20.0), null, null, null, new Road(23.0), null, new Road(27.0), new Road(2.0) },
			{ null, null, null, null, new Road(37.0), new Road(23.0), null, null, new Road(1.0), new Road(42.0) },
			{ new Road(28.0), null, null, null, null, null, null, null, new Road(34.0), null, },
			{ null, new Road(30.0), new Road(45.0), null, null, new Road(27.0), new Road(1.0), new Road(34.0), null,
					new Road(11.0) },
			{ null, null, null, null, null, new Road(2.0), new Road(42.0), null, new Road(11.0), null },
	};

	@Test
	public void testShortestPath12() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(0);
		assertEquals(34, path);
	}

	@Test
	public void testShortestPath13() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(2);
		assertEquals(129, path);
	}

	@Test
	public void testShortestPath14() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(4);
		assertEquals(134, path);
	}

	@Test
	public void testShortestPath15() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(5);
		assertEquals(109, path);
	}

	@Test
	public void testShortestPath16() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(9);
		assertEquals(107, path);
	}

	@Test
	public void testShortestPath17() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(4);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 0, 7, 8, 6, 4 }), path);
	}

	@Test
	public void testShortestPath18() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		int path = (int) sp.getShortestPath(3);
		assertEquals(0, path);
	}

	@Test
	public void testShortestPath19() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(2);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 0, 7, 8, 9, 5, 2 }), path);
	}

	@Test
	public void testShortestPath20() {
		Graph g = new Graph(distanceMatrix2);
		ShortestPath sp = new ShortestPath(0, g);
		ArrayList<Integer> path = sp.getPath(0);
		assertIterableEquals(Arrays.asList(new Integer[] { 0 }), path);
	}

	private Road[][] distanceMatrix3 = new Road[][] {
			{ null, new Road(30.0), new Road(8.0), new Road(11.0), new Road(34.0), new Road(21.0), new Road(5.0), null,
					new Road(36.0), new Road(8.0) },
			{ new Road(30.0), null, new Road(34.0), new Road(38.0), new Road(18.0), new Road(16.0), new Road(44.0),
					new Road(3.0), new Road(0.0), new Road(26.0) },
			{ new Road(8.0), new Road(34.0), null, new Road(41.0), null, new Road(6.0), new Road(5.0), new Road(9.0), null,
					null },
			{ new Road(11.0), new Road(38.0), new Road(41.0), null, new Road(11.0), new Road(2.0), new Road(46.0), null,
					new Road(42.0), new Road(15.0) },
			{ new Road(34.0), new Road(18.0), null, new Road(11.0), null, new Road(21.0), new Road(3.0), new Road(7.0),
					new Road(28.0), new Road(18.0) },
			{ new Road(21.0), new Road(16.0), new Road(6.0), new Road(2.0), new Road(21.0), null, new Road(43.0),
					new Road(43.0), new Road(34.0), new Road(45.0) },
			{ new Road(5.0), new Road(44.0), new Road(5.0), new Road(46.0), new Road(3.0), new Road(43.0), null,
					new Road(45.0), new Road(29.0), new Road(38.0) },
			{ null, new Road(3.0), new Road(9.0), null, new Road(7.0), new Road(43.0), new Road(45.0), null, new Road(4.0),
					new Road(49.0), },
			{ new Road(36.0), new Road(0.0), null, new Road(42.0), new Road(28.0), new Road(34.0), new Road(29.0),
					new Road(4.0), null, new Road(23.0) },
			{ new Road(8.0), new Road(26.0), null, new Road(15.0), new Road(18.0), new Road(45.0), new Road(38.0),
					new Road(49.0), new Road(23.0), null }
	};

	@Test
	public void testShortestPath21() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(0);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 0 }), path);
	}

	@Test
	public void testShortestPath22() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(2);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 5, 2 }), path);
	}

	@Test
	public void testShortestPath23() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(4);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 4 }), path);
	}

	@Test
	public void testShortestPath24() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(5);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 5 }), path);
	}

	@Test
	public void testShortestPath25() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(3, g);
		ArrayList<Integer> path = sp.getPath(9);
		assertIterableEquals(Arrays.asList(new Integer[] { 3, 9 }), path);
	}

	@Test
	public void testShortestPath26() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(7, g);
		ArrayList<Integer> path = sp.getPath(0);
		assertIterableEquals(Arrays.asList(new Integer[] { 7, 4, 6, 0 }), path);
	}

	@Test
	public void testShortestPath27() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(7, g);
		ArrayList<Integer> path = sp.getPath(2);
		assertIterableEquals(Arrays.asList(new Integer[] { 7, 2 }), path);
	}

	@Test
	public void testShortestPath28() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(7, g);
		ArrayList<Integer> path = sp.getPath(4);
		assertIterableEquals(Arrays.asList(new Integer[] { 7, 4 }), path);
	}

	@Test
	public void testShortestPath29() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(7, g);
		ArrayList<Integer> path = sp.getPath(5);
		assertIterableEquals(Arrays.asList(new Integer[] { 7, 2, 5 }), path);
	}

	@Test
	public void testShortestPath30() {
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(7, g);
		ArrayList<Integer> path = sp.getPath(9);
		assertIterableEquals(Arrays.asList(new Integer[] { 7, 4, 6, 0, 9 }), path);
	}

	@Test
	public void testShortestPath31() {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		Graph g = new Graph(distanceMatrix3);
		ShortestPath sp = new ShortestPath(0, g);
		sp.printSolution(5);
		String print = "Route\t\t\t Distance\t\t Path\n" + "0 -> 5 \t\t\t 13.0 \t\t\t 0 3 5 \n";
		assertEquals(print, out.toString());
	}

}
