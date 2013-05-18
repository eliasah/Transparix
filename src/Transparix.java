import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;


public class Transparix {

	private final String FILE_STATIONS = "data/data_v1/stations.txt";
	private final String FILE_LINES = "data/data_v1/lignes.txt";	

	private LinkedList<Station> stations;
	private LinkedList<Line> lines;

	public Transparix() throws IOException {
		this.stations = new LinkedList<Station>();
		this.lines = new LinkedList<Line>();
		
		this.extractStations(FILE_STATIONS);
		this.extractLines(FILE_LINES);
		
		// FIXME déplacer le code suivant
		JFrame f = new JFrame("TransParix");
		f.setContentPane(new PMap(stations, lines, 800, 800));
		f.pack();
		f.setVisible(true);
	}

	public void extractStations(String filePath) throws IOException {
		System.out.println("Extracting data");
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
		System.out.println(stations.toString());
	}

	public void extractLines(String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line = "";
		while ((line = br.readLine()) != null){
			// TODO
		}
		br.close();
	}

	public static void main(String[] args) {
		try {
			Transparix t = new Transparix();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
