package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import itinerary.BFS;

import org.junit.Before;
import org.junit.Test;

import structure.Graph;
import structure.Station;
import tools.Couple;

public class TestGraphBFS {
	private Graph gs;
	LinkedList<Couple<Integer, Station>> hm;

	@Before
	public void setUp() throws Exception {
		gs = new Graph();
		hm = gs.getStations();
		assertNotNull(gs);
		assertNotNull(hm);
	}

	/**
	 * test finished in 1900.431s 100 times (with k)
	 * test finished in 18.327s average on single BFS
	 */
	@Test
	public void test() {
		int i,j,k;
		int length = 1;
		for (k=0;k<length;k++) {
			int cpt = 0;
			Object[] tab = hm.toArray();
			for (i=0;i<tab.length;i++){
				Couple<Integer,Station> c1 = (Couple<Integer, Station>) tab[i];
				for (j=0;j<tab.length;j++){
					Couple<Integer,Station> c2 = (Couple<Integer, Station>) tab[j];
					BFS parcours = new BFS(gs, c1.second().getId(), c2.second().getId());
					cpt++;
					assertNotNull(parcours);
					assertNotNull(parcours.getPath());
				}
			}
			assertEquals(301*301, cpt);
		}
	}
}