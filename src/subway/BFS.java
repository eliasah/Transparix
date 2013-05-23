package subway;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.management.Query;

public class BFS {
	
	ArrayList<String> order;
	PriorityQueue<String> queue;
	Graph graph;
	public BFS(Graph g){
		graph = g;
		order = new ArrayList();
		queue = new PriorityQueue<String>();
		
		Iterator it = g.getMap().iterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
	}
	
}
