package gui;

import structure.Graph;
import structure.Line;
import structure.Station;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Cette classe représente le panneau d'affichage du plan du métro.
 * 
 * @author isabelle
 * 
 */
public class Map extends JPanel {

	private Transparix parent;
	private Hashtable<Integer, Station> stations;
	private Hashtable<String, Line> lines;
	private LinkedList<Integer> path;
	private BufferedImage image;
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
	public Map(Transparix p, Hashtable<Integer, Station> stations,
			Hashtable<String, Line> lines, int width, int height) {
		this.parent = p;
		this.stations = stations;
		this.lines = lines;
		this.path = new LinkedList<Integer>();
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// FIXME afficher le nom dans un Panel
				int x = arg0.getX();
				int y = arg0.getY();
				Station s = stationPressed(x, y);
				if (s != null)
					parent.getStationName().setText(s.getName());
			}
		});
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;
		g.drawImage(image, 0, 0, null);
		Dimension dim = getSize();
		BasicStroke stroke = new BasicStroke(2);

		// coloration du fond de la carte
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

		// itération sur l'ensemble des stations
		Iterator<Entry<Integer, Station>> it = this.stations.entrySet()
				.iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			int[] coords = this.convertCoordinatesStation(s.getLatitude(),
					s.getLongitude(), this.width, this.height);

			// affichage de la station
			g.setColor(Color.BLACK);
			g.fillRect(coords[0] - STATION_SIZE / 2, coords[1] - STATION_SIZE
					/ 2, STATION_SIZE, STATION_SIZE);

			// tracé des segments reliant la station à chacun de ses voisins
			g.setStroke(stroke);
			
			// FIXME ajouter le graphe dans le constructeur ?
			Graph graph = new Graph();
			HashMap<Integer, String> nList = s.getNeighbours();
			Iterator<Integer> itS = nList.keySet().iterator();
			Iterator<String> itL = nList.values().iterator();
			while (itS.hasNext() && itL.hasNext()) {
				int idSN = itS.next();
				Station sN = this.stations.get(idSN);
				int[] coordsSN = this.convertCoordinatesStation(
						sN.getLatitude(), sN.getLongitude(), this.width,
						this.height);
				String idLN = itL.next();
				Line lN = this.lines.get(idLN);
				Color colorLN = lN.getColor();
				g.setColor(colorLN);
				g.drawLine(coords[0], coords[1], coordsSN[0], coordsSN[1]);
			}
		}
		
		// tracé de l'itinéraire actuellement calculé
		g.setColor(Color.yellow);
		stroke = new BasicStroke(5);
		g.setStroke(stroke);
		Station tmp = null;
		Iterator<Integer> itP = path.iterator();
		while (itP.hasNext()) {
			Station s = this.stations.get(itP.next());
			if (tmp == null) {
				tmp = s;
			} else {
				int[] coords1 = this.convertCoordinatesStation(tmp.getLatitude(),
						tmp.getLongitude(), this.width, this.height);
				if (!itP.hasNext()) // s1 est la station d'arrivée
					break;
				Station s2 = this.stations.get(itP.next());
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
	 * Détermine si le point (x,y) correspond à une station et renvoie cette
	 * dernière, ou null dans le cas contraire.
	 * 
	 * @param x
	 *            L'abscisse du point.
	 * @param y
	 *            L'ordonnée du point.
	 * @return La station sur laquelle le clic de souris a eu lieu. Si aucune
	 *         station n'est trouvée, renvoie null.
	 */
	public Station stationPressed(int x, int y) {
		Iterator<Entry<Integer, Station>> it = this.stations.entrySet()
				.iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			int[] coords = this.convertCoordinatesStation(s.getLatitude(),
					s.getLongitude(), this.width, this.height);
			int xS = coords[0];
			int yS = coords[1];
			if (x >= xS - STATION_SIZE / 2 && x <= xS + STATION_SIZE / 2
					&& y >= yS - STATION_SIZE / 2 && y <= yS + STATION_SIZE / 2) {
				return s;
			}
		}
		return null;
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
		Iterator<Entry<Integer, Station>> it = this.stations.entrySet()
				.iterator();
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
		Iterator<Entry<Integer, Station>> it = this.stations.entrySet()
				.iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			// java.util.Iterator<Station> it = this.stations.iterator();
			// while (it.hasNext()) {
			// Station s = it.next();
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
		Iterator<Entry<Integer, Station>> it = this.stations.entrySet()
				.iterator();
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
		Iterator<Entry<Integer, Station>> it = this.stations.entrySet()
				.iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			if (s.getLongitude() < min)
				min = s.getLongitude();
		}
		return min;
	}

}
