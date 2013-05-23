package subway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFS {

	/**
	 * 1  procedure BFS(G,v):
	 * 2      create a queue Q
	 * 3      enqueue v onto Q
	 * 4      mark v
	 * 5      while Q is not empty:
	 * 6          t ← Q.dequeue()
	 * 7          if t is what we are looking for:
	 * 8              return t
	 * 9          for all edges e in G.adjacentEdges(t) do
	 * 10             u ← G.adjacentVertex(t,e)
	 * 11             if u is not marked:
	 * 12                  mark u
	 * 13                  enqueue u onto Q
	 * 14     return none
	 */

	ArrayList<String> order;
	PriorityQueue<Integer> queue;
	Graph graph;
	String path;

	public BFS(Graph g,int depart,int arr){
		graph = g;
		order = new ArrayList();
		queue = new PriorityQueue<Integer>();
		HashMap<Integer, String> v;

		queue.add(depart);

		Station s = g.getMap().get(depart);
		Station a = g.getMap().get(arr);
		s.setVisited(true);
		int tmp;
		Station temporaire = null;
		while (!queue.isEmpty()){
			tmp = queue.poll();
			System.out.println(tmp);
			if (tmp == arr){
				System.out.println("trouvé!");
				return;
			}
			v = s.getVoisins();
			System.out.println("neighbors : " + v.toString());

			Iterator it = v.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry)it.next();
				temporaire = graph.getMap().get((int)pairs.getKey());
				System.out.println("station : " + temporaire.getNom() + ", ligne : " + pairs.getValue());

				if (temporaire.getId() == (int) a.getId()) {
					System.out.println("trouvé! c'est juste a cote");
					return;
				}
			}
			temporaire.setVisited(true);
			queue.add(temporaire.getId());
		}

	}
}
