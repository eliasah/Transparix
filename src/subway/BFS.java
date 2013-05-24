package subway;

import isabelle.Couple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import structures.In;

public class BFS {

	ArrayList<String> order;
	PriorityQueue<Couple> queue;
	private Graph graph;
	private String path;
	private Station depart;
	private Station arrivee;
	private HashMap<Integer, Integer> visite;
	private LinkedList<Couple> avisite;

	public BFS(Graph g, int d, int a) {
		// initialisation
		this.graph = g;
		this.order = new ArrayList();
		this.queue = new PriorityQueue<Couple>();
		this.depart = g.getMap().get(d);
		this.arrivee = g.getMap().get(a);
		this.visite = new HashMap<Integer, Integer>();
		this.avisite = new LinkedList<Couple>();

		HashMap<Integer, String> v;
		Couple c = new Couple<Integer, Integer>(d, 0);
		queue.add(c);
		this.depart.setVisited(true);
		Station temporaire = null;

		// while queue not empty
		while (!queue.isEmpty()) {
			c = queue.poll();
			visite.put((Integer) c.first(), (Integer) c.second());
			if ((Integer) c.first() == a) {
				System.out.println("trouvé!");
				return;
			}

			// For all neighbors
			v = g.getMap().get(c.first()).getVoisins();
			System.out.println("On est a " + g.getMap().get(c.first()).getNom()
					+ " neighbors : " + v.toString());
			Iterator it = v.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pairs = (Map.Entry) it.next();
				temporaire = graph.getMap().get((int) pairs.getKey());
				System.out.println("station : " + temporaire.getNom()
						+ ", ligne : " + pairs.getValue());

				if (temporaire.getId() == (int) arrivee.getId()) {
					System.out.println("trouvé! c'est juste a cote");
					return;
				}

				Couple<Integer, Integer> ctmp = new Couple<Integer, Integer>(
						temporaire.getId(), (Integer) c.first());
				// if (visite.containsKey((Integer) ctmp.first()) == false)
				queue.add(ctmp);

			}
		}
	}
}
