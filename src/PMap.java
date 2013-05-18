import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;

public class PMap extends JPanel {

	private LinkedList<Station> stations;
	private LinkedList<Line> lines;
	private int width, height;

	public PMap(LinkedList<Station> stations, LinkedList<Line> lines, int width,
			int heigth) {
		this.stations = stations;
		this.lines = lines;
		this.width = width;
		this.height = height;
		
		this.drawStations();
	}
	
	public void drawStations() {
		for (Station s : stations) {
			double[] coords = convertCoordonneesStation(s.getLatitude(), 
					s.getLongitude(), this.width, this.height);
			this.add(new JButton());
		}
	}

	public double[] convertCoordonneesStation(double latitude, double longitude, 
			int width, int height) {
		double[] tab = new double[2];
		double x = (Math.abs((getLatitudeMax() - latitude) 
				/ (getLatitudeMax() - getLatitudeMin())) * height);
		double y = (Math.abs((longitude - getLongitudeMin()) 
				/ (getLongitudeMax() - getLongitudeMin())) * width);
		tab[0] = x;
		tab[1] = y;
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
			if (tmp.getLatitude() > max) max = tmp.getLongitude();
		}
		return max;
	}

	public double getLongitudeMin() {
		float min = Float.MAX_VALUE;
		java.util.Iterator<Station> it = this.stations.iterator();
		while (it.hasNext()) {
			Station tmp = it.next();
			if (tmp.getLatitude() < min) min = tmp.getLongitude();
		}
		return min;
	}

}
