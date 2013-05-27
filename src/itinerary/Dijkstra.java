package itinerary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import structure.Edge;
import structure.Graph;
import structure.Station;
import tools.Couple;

/**
 * 
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 *
 */
public class Dijkstra {

	private final List<Couple<Integer, Station>> nodes;
	private final List<Edge> edges;
	private Set<Station> settledNodes;
	private Set<Station> unSettledNodes;
	private Map<Station, Station> predecessors;
	private Map<Station, Integer> distance;

	public Dijkstra(Graph graph) {
		this.nodes = graph.getStations();
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Station source) {
		settledNodes = new HashSet<Station>();
		unSettledNodes = new HashSet<Station>();
		distance = new HashMap<Station, Integer>();
		predecessors = new HashMap<Station, Station>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Station node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Station node) {
		List<Station> adjacentNodes = getNeighbors(node);
		for (Station target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target, getShortestDistance(node)
						+ getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private int getDistance(Station node, Station target){
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Station> getNeighbors(Station node) {
		List<Station> neighbors = new ArrayList<Station>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node)
					&& !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Station getMinimum(Set<Station> Stationes) {
		Station minimum = null;
		for (Station Station : Stationes) {
			if (minimum == null) {
				minimum = Station;
			} else {
				if (getShortestDistance(Station) < getShortestDistance(minimum)) {
					minimum = Station;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Station Station) {
		return settledNodes.contains(Station);
	}

	private int getShortestDistance(Station destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}


	public LinkedList<Station> getPath(Station target) {
		LinkedList<Station> path = new LinkedList<Station>();
		Station step = target;

		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}

		Collections.reverse(path);
		return path;
	}

}
