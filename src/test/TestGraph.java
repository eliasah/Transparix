package test;

import static org.junit.Assert.*;
import itinerary.BFS;
import itinerary.Graph;

import org.junit.Before;
import org.junit.Test;

public class TestGraph {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Graph gs = new Graph();
		BFS parcours = new BFS(gs, 1953, 1793);
		System.out.println(parcours.getPath());
		parcours.printPath();
	}
}
