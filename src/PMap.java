import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

public class PMap extends JPanel {

	private LinkedList<Station> stations;
	private LinkedList<Line> lines;
	private int width, height;
	private final int RADIUS_STATION = 5;

	public PMap(LinkedList<Station> stations, LinkedList<Line> lines, int width,
			int height) {
		this.stations = stations;
		this.lines = lines;
		this.width = width;
		this.height = height;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		for (Station s : stations) {
			int[] coords = convertCoordonneesStation(s.getLatitude(), 
					s.getLongitude(), this.width, this.height);
			g.drawOval(coords[0], coords[1], RADIUS_STATION, RADIUS_STATION);
			g.drawString(s.getName(), coords[0], coords[1]);
		}
	}

	public int[] convertCoordonneesStation(double latitude, double longitude, 
			int width, int height) {
		int[] tab = new int[2];
		double x = ((latitude - getLatitudeMin())
				* (1/(getLatitudeMax() - getLatitudeMin())) * height);
		double y = (Math.abs(1 - (longitude - getLongitudeMin())
				* (1/(getLongitudeMax() - getLongitudeMin())))* width);
		tab[0] = (int) Math.round(x);
		tab[1] = (int) Math.round(y);
		return tab;
	}
	
	public double getLatitudeMax() {
		float max = Float.MIN_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLatitude() > max) max = tmp.getLatitude();
		}
		return max;
	}

	public double getLatitudeMin() {
		float min = Float.MAX_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLatitude() < min) min = tmp.getLatitude();
		}
		return min;
	}

	public double getLongitudeMax() {
		float max = Float.MIN_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLongitude() > max) max = tmp.getLongitude();
		}
		return max;
	}

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
