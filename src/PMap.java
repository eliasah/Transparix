import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Cette classe représente le panneau d'affichage du plan du métro.
 * 
 * @author isabelle
 *
 */
public class PMap extends JPanel {

	private LinkedList<Station> stations;
	private LinkedList<Line> lines;
	private int width, height;

	/**
	 * Constructeur d'un plan de métro.
	 * @param stations La liste des stations à afficher.
	 * @param lines La liste des lignes à afficher.
	 * @param width La largeur de la carte.
	 * @param height La longueur de la carte.
	 */
	public PMap(LinkedList<Station> stations, LinkedList<Line> lines, int width,
			int height) {
		this.stations = stations;
		this.lines = lines;
		this.width = width;
		this.height = height;
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		for (Station s : this.stations) {
			int[] coords = this.convertCoordonneesStation(s.getLatitude(), 
					s.getLongitude(), this.width, this.height);
			g.fillRect(coords[0], coords[1], 5, 5);
			g.drawString(s.getName(), coords[0], coords[1]);
		}
	}

	/**
	 * Calcule les coordonnées x et y dans un plan de width par height pixels.
	 * @param latitude La latitude du point à afficher.
	 * @param longitude La longitude du point à afficher.
	 * @param width La largeur de la carte.
	 * @param height La hauteur de la carte.
	 * @return Les coordonnées x et y.
	 */
	public int[] convertCoordonneesStation(double latitude, double longitude, 
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
	 * @return La plus grande latitude des stations.
	 */
	public double getLatitudeMax() {
		float max = Float.MIN_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLatitude() > max) max = tmp.getLatitude();
		}
		return max;
	}

	/**
	 * Renvoie la plus petite latitude des stations.
	 * @return La plus petite latitude des stations.
	 */
	public double getLatitudeMin() {
		float min = Float.MAX_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLatitude() < min) min = tmp.getLatitude();
		}
		return min;
	}

	/**
	 * Renvoie la plus grande longitude des stations.
	 * @return La plus grande longitude des stations.
	 */
	public double getLongitudeMax() {
		float max = Float.MIN_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLongitude() > max) max = tmp.getLongitude();
		}
		return max;
	}

	/**
	 * Renvoie la plus petite longitude des stations.
	 * @return La plus petite longitude des stations.
	 */
	public double getLongitudeMin() {
		float min = Float.MAX_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLongitude() < min) min = tmp.getLongitude();
		}
		return min;
	}

}
