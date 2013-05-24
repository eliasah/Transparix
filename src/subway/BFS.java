package subway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS {

	ArrayList<String> order;
	LinkedList<Integer> queue;
	private Graph graph;
	private Station depart;
	private Station arrivee;
	private HashMap<Integer, Integer> visite;
	private LinkedList<Couple> avisite;
	private LinkedList<Integer> path;

	public BFS(Graph g, int d, int a) {
		// initialisation
		this.graph = g;
		this.order = new ArrayList();
		this.path = new LinkedList<Integer>();
		// 2 create a queue Q
		this.queue = new LinkedList<Integer>();
		this.depart = g.getStation(d);
		this.arrivee = g.getStation(a);

		// 3 enqueue source onto Q
		queue.add(depart.getId());

		// 4 mark source
		this.depart.mark(true);

		// 5 while queue is not empty:
		while (!queue.isEmpty()) {
			// 6 dequeue an item from Q into v
			int v = queue.poll();
			Station tmp = graph.getStation(v);
			HashMap<Integer, String> voisins = tmp.getVoisins();
			Iterator it = voisins.entrySet().iterator();

			// 7 for each edge e incident on v in Graph: For all neighbors
			while (it.hasNext()) {
				/**
				 * 8 let w be the other end of e 9 if w is not marked: 10 mark w
				 * 11 enqueue w onto Q
				 */
				Map.Entry pairs = (Map.Entry) it.next();
				Station stmp = graph.getStation((int) pairs.getKey());
				if (!stmp.isMarked()) {
					stmp.mark(true);
					queue.add(stmp.getId());
					// System.out.println(stmp.getNom() + ", " + stmp.getId());
					path.add(stmp.getId());
				}

				if (stmp.getId() == (int) arrivee.getId()) {
					System.out.println(path);
					System.out.println("trouvé!");
					return;
				}

			}
		}
	}

	public LinkedList<Integer> getPath() {
		return path;
	}
}
