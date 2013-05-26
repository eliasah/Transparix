package gui;

import structure.Graph;
import structure.Line;
import structure.Station;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.Icon;
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
	private boolean zoom = false;

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
		this.setLayout(new BorderLayout());
		this.graph = graph;
		this.parent = p;
		this.path = new LinkedList<Integer>();
		if (width < height) {
			this.width = width;
			this.height = width;
		} else {
			this.width = height;
			this.height = height;
		}
		Dimension dim = new Dimension(this.width, this.height);
		this.setPreferredSize(dim);
		this.setMinimumSize(dim);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	public void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;

		if (!this.zoom) {
			// mise a� jour des nouvelles dimensions
			Dimension dim = getSize();
			this.width = dim.getWidth() < dim.getHeight() ? (int) dim
					.getWidth() : (int) dim.getHeight();// FIXME
			this.height = dim.getWidth() < dim.getHeight() ? (int) dim
					.getWidth() : (int) dim.getHeight();// FIXME
		} else {
			// les dimensions doivent etre celles calculees dans increaseSize()
			// et decreaseSize()
			this.zoom = false;
		}

		BasicStroke stroke = new BasicStroke(3);

		// coloration du fond de la carte
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.width, this.height);

		// suppression des anciens boutons (en cas de redimensionnement)
		for (Component c : this.getComponents())
			if (c instanceof JButton)
				this.remove(c);

		// ajout des boutons de zoom
		JButton bPlus = new JButton("+");
		bPlus.setSize(44, 25);
		bPlus.setLocation(50, 40);
		bPlus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				increaseSize();
			}
		});
		this.add(bPlus);
		JButton bMinus = new JButton("-");
		bMinus.setSize(44, 25);
		bMinus.setLocation(50, 60);
		bMinus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				decreaseSize();
			}
		});
		this.add(bMinus);

		// itération sur l'ensemble des stations
		Iterator<Entry<Integer, Station>> it = this.graph.stationsToHashtable()
				.entrySet().iterator();
		while (it.hasNext()) {
			Station s = it.next().getValue();
			int[] coords = this.convertCoordinatesStation(s.getLatitude(),
					s.getLongitude(), this.width, this.height);

			// affichage des stations
			JButton bStation = new JButton(new ColoredSquare(Color.black,
					STATION_SIZE));
			bStation.setPreferredSize(new Dimension(STATION_SIZE, STATION_SIZE));
			bStation.setBounds(coords[0] - STATION_SIZE / 2, coords[1]
					- STATION_SIZE / 2, STATION_SIZE, STATION_SIZE);
			StationListener sl = new StationListener(s, this.parent);
			bStation.addActionListener(sl);
			this.add(bStation);

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
		// trace de l'itineraire actuellement calcule
		g.setColor(Color.RED);
		stroke = new BasicStroke(7);
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

	public void increaseSize() {
		this.zoom = true;
		this.width += 30 * this.width / 100;
		this.height += 30 * this.height / 100;
		this.repaint();
	}

	public void decreaseSize() {
		this.zoom = true;
		this.width -= 30 * this.width / 100;
		this.height -= 30 * this.width / 100;
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

class ColoredSquare implements Icon {
	private Color color;
	private int length;

	public ColoredSquare(Color color, int length) {
		this.color = color;
		this.length = length;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.drawRect(x, y, getIconWidth(), getIconHeight());
	}

	@Override
	public int getIconWidth() {
		return this.length;
	}

	@Override
	public int getIconHeight() {
		return this.length;
	}
}