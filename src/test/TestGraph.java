package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import structure.Graph;

public class TestGraph {

	@Test
	public void test() {
		int i = 0;
		Graph g;
		for (i = 0; i < 1000; i++) {
			g = new Graph();
			assertNotNull(g);
			assertNotNull(g.getLines());
			assertNotNull(g.getStations());
		}
	}

}
