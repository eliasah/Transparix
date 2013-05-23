package subway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph{
	// ID # NOM # LAT # LONG # VILLE # LIGNES # VOISIN ((ligne,id);)*
	private int v;
	private int e;
	private ArrayList<Station> map;

	public Graph() {
		v = 0;
		e = 0;
		map = new ArrayList<Station>();
	}

	public void fillwithStations(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = br.readLine()) != null) {
			String[] args = line.split("#");

			// ID 			
			int id = Integer.parseInt(args[0]);
			// NAME
			String name = args[1];
			// LAT 
			float latitude = Float.parseFloat(args[2]);
			// LONG
			float longitude = Float.parseFloat(args[3]);
			// VILLE
			String city = args[4];
			// LIGNES
			ArrayList<String> lines = new ArrayList<String>();
			String[] linesStations = args[5].split(";");
			for (String s : linesStations) {
				lines.add(s);
			}
			// VOISIN couple num ligne, id  station)
			HashMap<Integer,String> neighbours = new HashMap<Integer, String>();
			String[] neighboursLinesStations = args[6].split(";");
			for (String s : neighboursLinesStations) {
				String[] tmp = s.split(",");
				neighbours.put(Integer.parseInt(tmp[1]),tmp[0]);
			}

			Station s = new Station(id, name, city, latitude, longitude, lines, neighbours, false);
			this.map.add(s);
			v++;
			// System.out.println(s);
		}
		br.close();
	}

	public void getShortestPath(String dep,String arr){
		new Dijkstra(this, dep);
	}

	public ArrayList<Station> getMap() {
		return map;
	}

	public static void main(String[] args) {
		String stations = "data/data_v1/stations.txt";
		Graph gs = new Graph();
		try {
			gs.fillwithStations(stations);
		} catch (IOException e) {
			System.out.println("File not found");
			// e.printStackTrace();
		}
		BFS parcours = new BFS(gs);
		//gs.getShortestPath("Villiers","b");
	}

	

}
