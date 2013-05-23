package subway;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
	Map<String,Integer> dist;
	Graph gs;
	
	public Dijkstra(Graph g,String depart) {
		this.gs = g;
		init(g,depart);
	}
	
	private void init(Graph g, String depart) {
		dist = new LinkedHashMap<String, Integer>();

		Iterator<Station> it = g.getMap().iterator();

		while (it.hasNext()) {
			String act = it.next().getNom();
			if (act.equals(depart)){
				dist.put(act, 0);
				// System.out.println("départ : " + act);
			} else {
				dist.put(act, Integer.MAX_VALUE);
			}
		}
		System.out.println("distance : " + dist.toString());
	}
	
}
