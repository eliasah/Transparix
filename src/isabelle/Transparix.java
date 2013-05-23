package isabelle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * TODO
 * 
 * @author isabelle
 *
 */
public class Transparix implements Runnable {

	private final String FILE_STATIONS = "data/data_v1/stations.txt";
	private final String FILE_LINES = "data/data_v1/lignes.txt";	

	private Hashtable<Integer,Station> stations;
	private Hashtable<String,Line> lines;
	
	private JFrame frame;
	private JMenuBar menubar;
	private JMenu fichier;
	private JMenuItem quitter;
	private Map map;
	private JPanel panel, informations;
	private JLabel stationName;

	/**
	 * Constructeur qui charge les données de l'application TransParix.
	 * @throws IOException
	 */
	public Transparix() {
		this.stations =	Extraction.extractStations(FILE_STATIONS); 
		this.lines = Extraction.extractLines(FILE_LINES);
	}
	
	/**
	 * Retourne le label qui contient le nom de la station sur lequel
	 * l'utilisateur a cliqué.
	 * @return Le nom de la station.
	 */
	public JLabel getStationName() {
		return stationName;
	}

	@Override
	public void run() {
		// barre de menu
		menubar = new JMenuBar();
		fichier = new JMenu("Fichier");
		quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		fichier.add(quitter);
		menubar.add(fichier);

		// plan de métro
		map = new Map(this, stations, lines, 500, 500);
		
		// panel informations
		informations = new JPanel();
		informations.setPreferredSize(new Dimension(200, 500));
		stationName = new JLabel();
		informations.add(stationName);
		
		// panel recherche station de métro
		// TODO
		
		// panel recherche itinéraire
		// TODO

		// panel principal
		// TODO ajouter une classe InformationsStation qui hérite de JPanel
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(map, BorderLayout.WEST);
		panel.add(informations, BorderLayout.EAST);

		// fenêtre principale
		frame = new JFrame("TransParix");
		frame.setJMenuBar(menubar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Transparix());
	}

}
