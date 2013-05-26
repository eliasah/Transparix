package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import itinerary.BFS;

import org.junit.Before;
import org.junit.Test;

import structure.Graph;
import structure.Station;
import tools.Couple;

public class TestGraph {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * test finished in 1900.431s 100 times (with k)
	 * test finished in 18.327s average on single BFS
	 */
	@Test
	public void test() {
		int i,j,k;
		for (k=0;k<100;k++) {
			int cpt = 0;
			Graph gs = new Graph();
			LinkedList<Couple<Integer, Station>> hm = gs.getStations();
			// System.out.println(hm.toString());

			Object[] tab = hm.toArray();
			for (i=0;i<tab.length;i++){
				Couple<Integer,Station> c1 = (Couple<Integer, Station>) tab[i];
				for (j=0;j<tab.length;j++){
					Couple<Integer,Station> c2 = (Couple<Integer, Station>) tab[j];
					//System.out.println(c1.second().getName() + "->" + c2.second().getName());
					BFS parcours = new BFS(gs, c1.second().getId(), c2.second().getId());
					//System.out.println(parcours.getPath());
					//parcours.printPath();
					//gs.resetMarks();
					cpt++;
					assertNotNull(parcours);
					assertNotNull(parcours.getPath());
				}
			}
			// System.out.println("--THE END? :P--");
			assertEquals(301*301, cpt);
		}
	}
}