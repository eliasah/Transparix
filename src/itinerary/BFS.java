package itinerary;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import structure.Graph;
import structure.Station;

public class BFS {

	private LinkedList<Integer> queue;
	private Graph graph;
	private Station start;
	private Station end;
	private LinkedList<Integer> tmppath;
	private LinkedList<Integer> path;
	private HashMap<Integer, Integer> list;

	public BFS(Graph g, int d, int a) {
		// 1 initialisation
		this.graph = g;
		this.tmppath = new LinkedList<Integer>();
		// 2 create a queue Q
		this.queue = new LinkedList<Integer>();
		this.start = g.getStation(d);
		this.end = g.getStation(a);
		this.list = new HashMap<Integer, Integer>();
		path = new LinkedList<Integer>();

		// 3 enqueue source onto Q
		queue.add(start.getId());
		list.put(start.getId(), 0);

		// 4 mark source
		this.start.mark(true);

		// 5 while queue is not empty:
		while (!queue.isEmpty()) {
			// 6 dequeue an item from Q into v
			int v = queue.poll();
			Station tmp = graph.getStation(v);
			if (tmp == null) {
				queue.add(v);
				continue;
			}
			// FIXME
			//System.out.println("tmp " + tmp.toString());
			// FIXME
			//if (!tmp.isAvailable())
			//	continue;
			HashMap<Integer, String> voisins = tmp.getNeighbours();
			Iterator<Map.Entry<Integer, String>> it = voisins.entrySet()
					.iterator();

			// 7 for each edge e incident on v in Graph: For all neighbors
			while (it.hasNext()) {
				Map.Entry<Integer, String> pairs = it.next();
				Station stmp = graph.getStation(pairs.getKey());
				// FIXME
				if (stmp == null)
					continue;
				if (!stmp.isMarked()) {
					stmp.mark(true);
					queue.add(stmp.getId());
					tmppath.add(stmp.getId());
					list.put(stmp.getId(), v);
					// System.out.println("De " + graph.getStation(v).getNom() +
					// " vers " + stmp.getNom());
				}

				if (stmp.getId() == end.getId()) {
					// System.out.println(path);
					listToPath(a);
					// System.out.println(list.toString());
					// System.out.println("trouvé!");
					graph.resetMarks();
					return;
				}

			}
		}
	}

	private void listToPath(int a) {
		int val = list.get(a);
		if (path == null) path = new LinkedList<Integer>();
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
