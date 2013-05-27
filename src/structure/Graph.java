package structure;

import itinerary.Dijkstra;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import tools.Couple;
import tools.Utilities;

/**
 * This class creates a Graph G = (V,E) with V as its vertices and its egdes We
 * consider here that vertices are stations
 * 
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 * 
 */
public class Graph {

	private int v; // |V|
	private int e; // |E|
	private final String FILE_LINES = "data/data_v1/lignes.txt";
	private final String FILE_STATIONS = "data/data_v1/stations.txt";

	private LinkedList<Couple<String, Line>> lines;
	private LinkedList<Couple<Integer, Station>> stations;
	private LinkedList<Edge> edges;

	private LinkedList<LinkedList<Integer>> lastItineraries; // dix derniers
															// itinéraires

	public Graph() {
		v = 0;
		e = 0;
		lines = Utilities.extractLines(FILE_LINES);
		stations = Utilities.fillwithStations(FILE_STATIONS);
		this.lastItineraries = new LinkedList<LinkedList<Integer>>();
	}

	public LinkedList<Couple<Integer, Station>> getStations() {
		return stations;
	}

	public void getShortestPath(String dep, String arr) {
		new Dijkstra(this);
	}

	public Station getStation(int id) {
		Iterator<Couple<Integer, Station>> it = this.stations.iterator();
		while (it.hasNext()) {
			Couple<Integer, Station> c = it.next();
			if (id == c.first())
				return c.second();
		}
		return null;
	}

	public Station getStation(String s) {
		for (Couple<Integer, Station> c : stations) {
			if (c.second().getName().equals(s))
				// System.out.println(c.second());
				return c.second();
		}
		return null;
	}

	public Line getLine(String id) {
		Iterator<Couple<String, Line>> it = this.lines.iterator();
		while (it.hasNext()) {
			Couple<String, Line> c = it.next();
			if (id == c.first())
				return c.second();
		}
		return null;
	}

	public int getV() {
		return v;
	}

	public LinkedList<Couple<String, Line>> getLines() {
		return lines;
	}

	public void resetMarks() {
		Iterator<Couple<Integer, Station>> it = this.stations.iterator();
		while (it.hasNext()) {
			Couple<Integer, Station> c = it.next();
			c.second().mark(false);
		}
	}

	public void resetAvailable() {
		Iterator<Couple<Integer, Station>> it = this.stations.iterator();
		while (it.hasNext()) {
			Couple<Integer, Station> c = it.next();
			c.second().setAvailable(true);
		}
	}

	/**
	 * 
	 * @return Hashtable of lines
	 */
	public Hashtable<String, Line> linesToHashtable() {
		Hashtable<String, Line> t = new Hashtable<String, Line>();
		for (Couple<String, Line> c : this.lines) {
			t.put(c.first(), c.second());
		}
		return t;
	}

	/**
	 * 
	 * @return Hashtable of stations
	 */
	public Hashtable<Integer, Station> stationsToHashtable() {
		Hashtable<Integer, Station> t = new Hashtable<Integer, Station>();
		for (Couple<Integer, Station> c : this.stations) {
			t.put(c.first(), c.second());
		}
		return t;
	}

	public Collection<? extends Edge> getEdges() {
		// FIXME
		return null;
	}
	
	public LinkedList<LinkedList<Integer>> getLastItineraries() {
		return this.lastItineraries;		
	}
}
