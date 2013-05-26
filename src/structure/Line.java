package structure;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Cette classe permet de sauvegarder les informations sur une ligne de metro, a
 * savoir son numero, ses stations de depart et ses stations d'arrivee.
 * 
 * @author isabelle
 * 
 */
public class Line {

	private String id;
	private LinkedList<Integer> idDepartures, idArrivals;
	private Color color;
	private int nbStations = -1;
	private int nbIntersections = -1;
	private double length = -1;

	/**
	 * Constructeur d'une ligne.
	 * 
	 * @param id
	 *            L'identitfiant de la ligne.
	 * @param idDepartures
	 *            La liste des stations au depart de la ligne.
	 * @param idArrivals
	 *            La liste des stations a l'arrivee de la ligne.
	 * @param color
	 */
	public Line(String id, LinkedList<Integer> idDepartures,
			LinkedList<Integer> idArrivals, Color color) {
		this.id = id;
		this.idDepartures = idDepartures;
		this.idArrivals = idArrivals;
		this.color = color;
	}

	/**
	 * Renvoie l'identifiant de la ligne.
	 * 
	 * @return L'identifiant de la ligne.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Renvoie la liste des stations au depart de la ligne.
	 * 
	 * @return La liste des stations au depart de la ligne.
	 */
	public LinkedList<Integer> getIdDepartures() {
		return idDepartures;
	}

	/**
	 * Renvoie la liste des stations a l'arrivee de la ligne.
	 * 
	 * @return La liste des stations a l'arrivee de la ligne.
	 */
	public LinkedList<Integer> getIdArrivals() {
		return idArrivals;
	}

	/**
	 * Renvoie la couleur de la ligne.
	 * 
	 * @return La couleur de la ligne.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Calcule le nombre de stations de la ligne.
	 * 
	 * @return Le nombre de stations de la ligne.
	 */
	public int getNbStations() {
		if (this.nbStations == -1) {
			// calculer le nombre de stations
			this.nbStations = 0;
			Graph graph = new Graph();
			Hashtable<Integer, Station> stations = graph.stationsToHashtable();
			Iterator<Station> it = stations.values().iterator();
			while (it.hasNext()) {
				Station s = it.next();
				// si s fait partie de la ligne, incrémenter nbStations
				if (s.getLines().contains(this.id)) {
					this.nbStations++;
				}
			}
		}
		return this.nbStations;
	}

	/**
	 * Calcule le nombre d'intersections de la ligne.
	 * 
	 * @return Le nombre d'intersections de la ligne.
	 */
	public int getNbIntersections() {
		if (this.nbIntersections == -1) {
			// calculer le nombre d'intersections
			this.nbIntersections = 0;
			Graph graph = new Graph();
			Hashtable<Integer, Station> stations = graph.stationsToHashtable();
			Iterator<Station> it = stations.values().iterator();
			while (it.hasNext()) {
				Station s = it.next();
				if (s.getLines().contains(this.id)) {
					if (this.getIdArrivals().contains(s.getId())
							|| this.getIdDepartures().contains(s.getId())) {
						// la station est un terminus, un seul voisin
						if (s.getNeighbours().size() > 1)
							this.nbIntersections++;
					} else {
						// la station n'est pas un terminus, deux voisins
						if (s.getNeighbours().size() > 2) {
							this.nbIntersections++;
						}
					}
				}
			}
		}
		return this.nbIntersections;
	}

	/**
	 * Calcule la longueur approximative de la ligne. Le calcul se base sur la
	 * distance entre les deux stations terminus de la ligne.
	 * 
	 * @return La longueur calculée.
	 */
	public double getLength() {
		if (this.length == -1) {
			// calculer la longueur
			Graph graph = new Graph();
			Station sD = graph.getStation(this.idDepartures.getFirst());
			Station sA = graph.getStation(this.idArrivals.getFirst()); // FIXME
			double latD = Math.toRadians(sD.getLatitude());
			double latA = Math.toRadians(sA.getLatitude());
			double lonD = Math.toRadians(sD.getLongitude());
			double lonA = Math.toRadians(sA.getLongitude());
			double r = 6371; // km
			this.length = r
					* Math.acos(Math.sin(latD) * Math.sin(latA)
							+ Math.cos(latD) * Math.cos(latA)
							* Math.cos(lonD - lonA));
		}
		return this.length;
	}

}
