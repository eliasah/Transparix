package itinerary;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import structure.Line;
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
	private LinkedList<Couple<Integer, Station>> map;
	private LinkedList<Couple<String,Line>> lines;
	private final String FILE_LINES = "data/data_v1/lignes.txt";

	
	public Graph() {
		v = 0;
		e = 0;
		map = new LinkedList<Couple<Integer, Station>>();
		lines = new LinkedList<Couple<String,Line>>();
	}

	/**
	 * Graph constructor from file path
	 * 
	 * @param filepath
	 */
	public Graph(String filepath) {
		this();
		lines = Utilities.extractLines(FILE_LINES);
		map = Utilities.fillwithStations(filepath);
	}


	public int getV() {
		return v;
	}

	public void getShortestPath(String dep, String arr) {
		new Dijkstra(this, dep);
	}

	public LinkedList<Couple<Integer, Station>> getMap() {
		return map;
	}

	public Station getStation(int id) {
		Iterator<Couple<Integer, Station>> it = this.map.iterator();
		while (it.hasNext()) {
			Couple<Integer, Station> c = it.next();
			if (id == c.first())
				return c.second();
		}
		it.remove();
		return null;
	}
	
	public static void main(String[] args) {
		
		String stations = "data/data_v1/stations.txt";
		Graph gs = new Graph(stations);
		// BFS parcours = new BFS(gs, 1953, 1793);
		// System.out.println(parcours.getPath());
		// parcours.printPath();
	}

}
