package subway;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
	Map<String, Integer> dist;
	Graph gs;

	public Dijkstra(Graph g, String depart) {
		this.gs = g;
		init(g, depart);
	}

	private void init(Graph g, String depart) {
		// TODO
	}

}
