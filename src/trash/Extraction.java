package trash;


import java.awt.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import structure.Line;
import structure.Station;
import tools.Couple;


/**
 * Les méthodes fournies dans cette classe permettent de lire et d'enregistrer
 * les données fournies par la RATP.
 * 
 * @author isabelle
 * 
 */
public class Extraction {

	/**
	 * Extrait les informations sur les stations qui composent le Métro
	 * Parisien.
	 * 
	 * @param filePath
	 *            Le chemin du fichier contenant les stations.
	 * @throws IOException
	 */
	public static Hashtable<Integer, Station> extractStations(String filePath) {
		Hashtable<Integer, Station> stations = new Hashtable<Integer, Station>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					String[] args = line.split("#");
					int id = Integer.parseInt(args[0]);
					String name = args[1];
					float latitude = Float.parseFloat(args[2]);
					float longitude = Float.parseFloat(args[3]);
					String city = args[4];
					LinkedList<String> lines = new LinkedList<String>();
					String[] linesStations = args[5].split(";");
					for (String s : linesStations) {
						lines.add(s);
					}
					LinkedList<Couple<String, Integer>> neighbours = new LinkedList<Couple<String, Integer>>();
					String[] neighboursLinesStations = args[6].split(";");
					for (String s : neighboursLinesStations) {
						String[] tmp = s.split(",");
						neighbours.add(new Couple<String, Integer>(tmp[0],
								Integer.parseInt(tmp[1])));
					}
					stations.put(id, new Station(id, name, city, latitude,
							longitude, lines, neighbours));
				}
				br.close();
			} catch (NumberFormatException e) {
				// TODO afficher un message graphique
				System.err.println("Erreur lors de la lecture d'un entier");
			} catch (IOException e) {
				// TODO afficher un message graphique
				System.err.println("Erreur lors de la lecture du fichier"
						+ "de données concernant les lignes du métro parisien");
			}
		} catch (FileNotFoundException e1) {
			// TODO afficher un message graphique
			System.err.println("Impossible de trouver le fichier de données"
					+ "concernant les lignes du métro parisien");
		}
		return stations;
	}

	/**
	 * Extrait les informations sur les lignes qui composent le Métro Parisien.
	 * 
	 * @param filePath
	 *            Le chemin du fichier contenant les lignes.
	 * @throws IOException
	 */
	public static Hashtable<String, Line> extractLines(String filePath) {
		Hashtable<String, Line> lines = new Hashtable<String, Line>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line = "";
			try {
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
					lines.put(id, new Line(id, idDepartures, idArrivals,
							new Color(r, g, b)));
				}
				br.close();
			} catch (NumberFormatException e) {
				// TODO afficher un message graphique
				System.err.println("Erreur lors de la lecture d'un entier");
			} catch (IOException e) {
				// TODO afficher un message graphique
				System.err.println("Erreur lors de la lecture du fichier"
						+ "de données concernant les lignes du métro parisien");
			}
		} catch (FileNotFoundException e1) {
			// TODO afficher un message graphique
			System.err.println("Impossible de trouver le fichier de données"
					+ "concernant les lignes du métro parisien");
		}
		return lines;
	}

}
