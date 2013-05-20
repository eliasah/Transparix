import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * TODO
 * 
 * @author isabelle
 *
 */
public class Transparix {

	private final String FILE_STATIONS = "data/data_v1/stations.txt";
	private final String FILE_LINES = "data/data_v1/lignes.txt";	

	private LinkedList<Station> stations;
	private LinkedList<Line> lines;

	/**
	 * Constructeur qui charge les données et génère la fenêtre principale
	 * de l'application TransParix.
	 * @throws IOException
	 */
	public Transparix() throws IOException {
		this.stations = new LinkedList<Station>();
		this.lines = new LinkedList<Line>();
		
		this.extractStations(FILE_STATIONS);
		this.extractLines(FILE_LINES);
		
		this.createGUI();
	}
	
	/**
	 * Extrait les informations sur les stations qui composent 
	 * le Métro Parisien.
	 * @param filePath Le chemin du fichier contenant les stations.
	 * @throws IOException
	 */
	public void extractStations(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
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
			LinkedList<Couple<String,Integer>> neighbours = 
					new LinkedList<Couple<String,Integer>>();
			String[] neighboursLinesStations = args[6].split(";");
			for (String s : neighboursLinesStations) {
				String[] tmp = s.split(",");
				neighbours.add(new Couple<String,Integer>(tmp[0], 
						Integer.parseInt(tmp[1])));
			}
			this.stations.add(new Station(id, name, city, latitude, longitude,
					lines, neighbours));
		}
		br.close();
	}

	/**
	 * Extrait les informations sur les lignes qui composent 
	 * le Métro Parisien.
	 * @param filePath Le chemin du fichier contenant les lignes.
	 * @throws IOException
	 */
	public void extractLines(String filePath) throws IOException {
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
			this.lines.add(new Line(id, idDepartures, idArrivals, new Color(r,g,b)));
		}
		br.close();
	}
	
	
	/**
	 * Crée et affiche la fenêtre principale de TransParix.
	 */
	public void createGUI() {
		JLabel label = new JLabel("== TransParix ==");
		PMap map = new PMap(stations, lines, 600, 600);
		
		// panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(label, BorderLayout.NORTH);
		mainPanel.add(map, BorderLayout.SOUTH);

		// fenêtre principale
		JFrame f = new JFrame("TransParix");
		f.setPreferredSize(new Dimension(700, 700));
		f.setMinimumSize(new Dimension(700, 700));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(mainPanel);
		f.pack();
		f.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			new Transparix();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
