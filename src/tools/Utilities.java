package tools;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import structure.*;


public class Utilities {

	/**
	 * This method fills the Graph with from a file path
	 * 
	 * @param filePath
	 */
	public static LinkedList<Couple<Integer, Station>> fillwithStations(
			String filePath) {
		LinkedList<Couple<Integer, Station>> map = new LinkedList<Couple<Integer, Station>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] args = line.split("#");

				int id = Integer.parseInt(args[0]);
				String name = args[1];
				float latitude = Float.parseFloat(args[2]);
				float longitude = Float.parseFloat(args[3]);
				String city = args[4];
				ArrayList<String> lines = new ArrayList<String>();
				String[] linesStations = args[5].split(";");
				for (String s : linesStations) {
					lines.add(s);
				}
				HashMap<Integer, String> neighbours = new HashMap<Integer, String>();
				String[] neighboursLinesStations = args[6].split(";");
				// FIXME
				// System.out.println(neighboursLinesStations.toString());
				for (String s : neighboursLinesStations) {
					String[] tmp = s.split(",");
					neighbours.put(Integer.parseInt(tmp[1]), tmp[0]);
				}

				Station s = new Station(id, name, city, latitude, longitude,
						lines, neighbours, false);
				Couple<Integer, Station> tmp = new Couple<Integer, Station>(
						s.getId(), s);
				map.add(tmp);
				s.setAvailable(true);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Extrait les informations sur les lignes qui composent le Métro Parisien.
	 * 
	 * @param filePath
	 *            Le chemin du fichier contenant les lignes.
	 * @throws IOException
	 */
	public static LinkedList<Couple<String, Line>> extractLines(String filePath) {
		LinkedList<Couple<String, Line>> lines = new LinkedList<Couple<String, Line>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] args = line.split("#");
				String id = args[0];
				LinkedList<Integer> idDepartures = new LinkedList<Integer>();
				String[] idDeparturesLine = args[1].split(";");
				for (String s : idDeparturesLine) {
					idDepartures.add(Integer.parseInt(s));
				}
				LinkedList<Integer> idArrivals = new LinkedList<Integer>();
				String[] idArrivalsLine = args[2].split(";");
				for (String s : idArrivalsLine) {
					idArrivals.add(Integer.parseInt(s));
				}
				String[] rgb = args[3].split(",");
				int r = Integer.parseInt(rgb[0]);
				int g = Integer.parseInt(rgb[1]);
				int b = Integer.parseInt(rgb[2]);

				lines.add(new Couple<String, Line>(id, new Line(id,
						idDepartures, idArrivals, new Color(r, g, b))));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
}
