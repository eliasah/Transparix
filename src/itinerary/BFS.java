package itinerary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class BFS {

	private LinkedList<Integer> queue;
	private Graph graph;
	private Station depart;
	private Station arrivee;
	private LinkedList<Integer> tmppath;
	private LinkedList<Integer> path;
	private HashMap<Integer, Integer> list;

	public BFS(Graph g, int d, int a) {
		// 1 initialisation
		this.graph = g;
		this.tmppath = new LinkedList<Integer>();
		// 2 create a queue Q
		this.queue = new LinkedList<Integer>();
		this.depart = g.getStation(d);
		this.arrivee = g.getStation(a);
		this.list = new HashMap<Integer, Integer>();

		// 3 enqueue source onto Q
		queue.add(depart.getId());
		list.put(depart.getId(), 0);

		// 4 mark source
		this.depart.mark(true);

		// 5 while queue is not empty:
		while (!queue.isEmpty()) {
			// 6 dequeue an item from Q into v
			int v = queue.poll();
			Station tmp = graph.getStation(v);
			HashMap<Integer, String> voisins = tmp.getVoisins();
			Iterator<Map.Entry<Integer, String>> it = voisins.entrySet()
					.iterator();

			// 7 for each edge e incident on v in Graph: For all neighbors
			while (it.hasNext()) {
				Map.Entry<Integer, String> pairs = it.next();
				Station stmp = graph.getStation(pairs.getKey());
				if (!stmp.isMarked()) {
					stmp.mark(true);
					queue.add(stmp.getId());
					tmppath.add(stmp.getId());
					list.put(stmp.getId(), v);
					// System.out.println("De " + graph.getStation(v).getNom() +
					// " vers " + stmp.getNom());
				}

				if (stmp.getId() == arrivee.getId()) {
					// System.out.println(path);
					listtopath(a);
					// System.out.println(list.toString());
					System.out.println("trouvé!");
					return;
				}

			}
		}
	}

	private void listtopath(int a) {
		int val = list.get(a);
		path = new LinkedList<Integer>();
		path.add(val);
		path.add(a);
		while (val != 0) {
			val = list.get(val);
			if (val != 0)
				path.addFirst(val);
		}
	}

	public LinkedList<Integer> getPath() {
		return path;
	}

	public void printPath() {
		Iterator<Integer> it = path.iterator();
		while (it.hasNext()) {
			int tmp = it.next();
			System.out.println(graph.getStation(tmp));
		}
	}

}
