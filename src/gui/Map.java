package gui;

import structure.Graph;
import structure.Line;
import structure.Station;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Cette classe représente le panneau d'affichage du plan du métro.
 * 
 * @author isabelle
 * 
 */
public class Map extends JPanel {

	private Transparix parent;
	private Graph graph;
	private LinkedList<Integer> path;
	private int width, height;
	private final int STATION_SIZE = 5;

	/**
	 * Constructeur d'un plan de métro.
	 * 
	 * @param stations
	 *            La liste des stations à afficher.
	 * @param lines
	 *            La liste des lignes à afficher.
	 * @param width
	 *            La largeur de la carte.
	 * @param height
	 *            La longueur de la carte.
	 */
	public Map(Transparix p, Graph graph, int width, int height) {
		this.setLayout(null);
		this.graph = graph;
		this.parent = p;
		this.path = new LinkedList<Integer>();
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		// affichage des stations
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			int[] coords = this.convertCoordinatesStation(s.getLatitude(),
					s.getLongitude(), this.width, this.height);
			// FIXME créer une classe StationButton
			JButton bStation = new JButton(new ColoredSquare(Color.green));
			bStation.setBounds(coords[0], coords[1], STATION_SIZE, STATION_SIZE);
			bStation.addActionListener(new ButtonListener(s, this.parent));
			this.add(bStation);
		}

		this.repaint();
	}

	@Override
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;

		Dimension dim = getSize();
		BasicStroke stroke = new BasicStroke(2);

		// coloration du fond de la carte
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

		// itération sur l'ensemble des stations
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			int[] coords = this.convertCoordinatesStation(s.getLatitude(),
					s.getLongitude(), this.width, this.height);

			// tracé des segments reliant la station à chacun de ses voisins
			g.setStroke(stroke);

			HashMap<Integer, String> nList = s.getNeighbours();
			Iterator<Integer> itS = nList.keySet().iterator();
			Iterator<String> itL = nList.values().iterator();
			while (itS.hasNext() && itL.hasNext()) {
				int idSN = itS.next();
				Station sN = this.graph.stationsToHashtable().get(idSN);
				int[] coordsSN = this.convertCoordinatesStation(
						sN.getLatitude(), sN.getLongitude(), this.width,
						this.height);
				String idLN = itL.next();
				Line lN = this.graph.linesToHashtable().get(idLN);
				Color colorLN = lN.getColor();
				g.setColor(colorLN);
				g.drawLine(coords[0], coords[1], coordsSN[0], coordsSN[1]);
			}
		}

		// FIXME
		// tracé de l'itinéraire actuellement calculé
		g.setColor(Color.yellow);
		stroke = new BasicStroke(5);
		g.setStroke(stroke);
		Station tmp = null;
		Iterator<Integer> itP = path.iterator();
		while (itP.hasNext()) {
			Station s = this.graph.stationsToHashtable().get(itP.next());
			if (tmp == null) {
				tmp = s;
			} else {
				int[] coords1 = this.convertCoordinatesStation(
						tmp.getLatitude(), tmp.getLongitude(), this.width,
						this.height);
				if (!itP.hasNext()) // s1 est la station d'arrivée
					break;
				Station s2 = this.graph.stationsToHashtable().get(itP.next());
				int[] coords2 = this.convertCoordinatesStation(
						s2.getLatitude(), s2.getLongitude(), this.width,
						this.height);
				g.drawLine(coords1[0], coords1[1], coords2[0], coords2[1]);
				tmp = s2;
			}
		}

	}

	public void drawPath(LinkedList<Integer> path) {
		this.path = path;
		this.repaint();
	}

	/**
	 * Calcule les coordonnées x et y dans un plan de width par height pixels.
	 * 
	 * @param latitude
	 *            La latitude du point à afficher.
	 * @param longitude
	 *            La longitude du point à afficher.
	 * @param width
	 *            La largeur de la carte.
	 * @param height
	 *            La hauteur de la carte.
	 * @return Les coordonnées x et y.
	 */
	public int[] convertCoordinatesStation(double latitude, double longitude,
			int width, int height) {
		int[] tab = new int[2];
		double margin = 0.01;
		double latitudeMin = this.getLatitudeMin() - margin;
		double latitudeMax = this.getLatitudeMax() + margin;
		double longitudeMin = this.getLongitudeMin() - margin;
		double longitudeMax = this.getLongitudeMax() + margin;
		double x = ((latitude - latitudeMin)
				* (1 / (latitudeMax - latitudeMin)) * height);
		double y = (Math.abs(1 - (longitude - longitudeMin)
				* (1 / (longitudeMax - longitudeMin))) * width);
		tab[0] = (int) Math.round(x);
		tab[1] = (int) Math.round(y);
		return tab;
	}

	/**
	 * Renvoie la plus grande latitude des stations.
	 * 
	 * @return La plus grande latitude des stations.
	 */
	public double getLatitudeMax() {
		float max = Float.MIN_VALUE;
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			if (s.getLatitude() > max)
				max = s.getLatitude();
		}
		return max;
	}

	/**
	 * Renvoie la plus petite latitude des stations.
	 * 
	 * @return La plus petite latitude des stations.
	 */
	public double getLatitudeMin() {
		float min = Float.MAX_VALUE;
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			if (s.getLatitude() < min)
				min = s.getLatitude();
		}
		return min;
	}

	/**
	 * Renvoie la plus grande longitude des stations.
	 * 
	 * @return La plus grande longitude des stations.
	 */
	public double getLongitudeMax() {
		float max = Float.MIN_VALUE;
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			if (s.getLongitude() > max)
				max = s.getLongitude();
		}
		return max;
	}

	/**
	 * Renvoie la plus petite longitude des stations.
	 * 
	 * @return La plus petite longitude des stations.
	 */
	public double getLongitudeMin() {
		float min = Float.MAX_VALUE;
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			if (s.getLongitude() < min)
				min = s.getLongitude();
		}
		return min;
	}

}
