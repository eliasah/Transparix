package subway;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	// ID # NOM # LAT # LONG # VILLE # LIGNES # VOISIN ((ligne,id);)*
	private int v; // nombre de station
	private int e; // edges
	private LinkedList<Couple<Integer, Station>> map;

	public Graph() {
		v = 0;
		e = 0;
		map = new LinkedList<Couple<Integer, Station>>();
	}

	public Graph(String filepath) {
		this();
		this.fillwithStations(filepath);
	}

	public void fillwithStations(String filePath) {
		try {
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
				// VOISIN couple num ligne, id station)
				HashMap<Integer, String> neighbours = new HashMap<Integer, String>();
				String[] neighboursLinesStations = args[6].split(";");
				for (String s : neighboursLinesStations) {
					String[] tmp = s.split(",");
					neighbours.put(Integer.parseInt(tmp[1]), tmp[0]);
				}

				Station s = new Station(id, name, city, latitude, longitude,
						lines, neighbours, false);
				Couple<Integer, Station> tmp = new Couple<Integer, Station>(
						s.getId(), s);
				this.map.add(tmp);
				v++;
				// System.out.println(s);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		BFS parcours = new BFS(gs, 1953, 1793);
		System.out.println(parcours.getPath());
		parcours.printPath();
	}

}
