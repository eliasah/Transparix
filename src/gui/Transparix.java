package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import structure.Graph;
import structure.Station;
import tools.Couple;

/**
 * Cette classe est la classe principale du projet TransParix. Elle contrôle
 * l'affichage de la fenêtre principale contenant le plan du métro.
 * 
 * @author Isabelle Richard, Abou Haydar Elias
 * 
 */
public class Transparix {

	private Graph graph;
	private JFrame frame;
	private JMenuBar menubar;
	private JMenu fichier;
	private JMenuItem quitter;
	private Map map;
	private JPanel panel;

	private JMenu recherche;
	private JMenuItem btnTreeSearch;

	private TreeSelectionFrame frmTreeSearch;
	private ComboSelectionFrame frmComboSearch;
	private JMenuItem btnComboSearch;
	private JMenu itineraire;
	private JMenu derniersItineraires;

	private ItineraryFrame itineraryFrame;

	private LinkedList<Integer> currentList;
	private JMenuItem rechercheLigne;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Transparix window = new Transparix();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructeur qui charge les donnees de l'application TransParix.
	 */
	public Transparix() {
		this.graph = new Graph();
		initialize();
	}

	/**
	 * Crée l'interface graphique de TransParix.
	 */
	public void initialize() {
		// barre de menu
		menubar = new JMenuBar();
		fichier = new JMenu("Fichier");
		fichier.setMnemonic(KeyEvent.VK_F);
		recherche = new JMenu("Recherche");
		recherche.setMnemonic(KeyEvent.VK_R);
		itineraire = new JMenu("Itineraire");
		itineraire.setMnemonic(KeyEvent.VK_I);

		rechercheLigne = new JMenuItem("Rechercher une ligne");
		rechercheLigne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<String> comboLines = new JComboBox<String>();
				Vector<String> values = new Vector<String>();
				Iterator<Couple<Integer, Station>> it = graph.getStations()
						.iterator();
				for (Couple<Integer, Station> c : graph.getStations()) {
					values.addElement(c.second().getName());
				}
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
						values);
				comboLines.setModel(model);
				// FIXME ajouter la combobox quelque part !
			}
		});

		btnTreeSearch = new JMenuItem("TreeSearch");
		btnTreeSearch.setMnemonic(KeyEvent.VK_T);
		btnTreeSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frmTreeSearch = new TreeSelectionFrame(map, graph);
				frmTreeSearch.setLocationByPlatform(true);
				frmTreeSearch.setVisible(true);
			}
		});

		btnComboSearch = new JMenuItem("ComboSearch");
		btnComboSearch.setMnemonic(KeyEvent.VK_C);
		btnComboSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frmComboSearch = new ComboSelectionFrame(map, graph);
				frmComboSearch.setLocationByPlatform(true);
				frmComboSearch.setVisible(true);
			}
		});

		quitter = new JMenuItem("Quitter", KeyEvent.VK_Q);
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				Event.CTRL_MASK));
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		derniersItineraires = new JMenu("Derniers itinéraires");
		derniersItineraires.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent arg0) {
				updateItineraries();
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
			}

			@Override
			public void menuCanceled(MenuEvent arg0) {
			}
		});

		fichier.add(quitter);
		recherche.add(rechercheLigne);
		itineraire.add(btnTreeSearch);
		itineraire.add(btnComboSearch);
		itineraire.add(derniersItineraires);

		menubar.add(fichier);
		menubar.add(recherche);
		menubar.add(itineraire);

		// plan de metro
		map = new Map(this, this.graph, 600, 600);

		// panel principal
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(600, 600));
		map.setPreferredSize(panel.getMaximumSize());
		panel.add(map, BorderLayout.CENTER);

		// fenetre principale
		frame = new JFrame("TransParix");
		Point p = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint();
		frame.setLocation((int) (p.getX() - 300), (int) (p.getY() - 300));

		frame.setJMenuBar(menubar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Renvoie le plan du métro.
	 * 
	 * @return Le Panel qui contient le plan.
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Renvoie les dimensions de la fenêtre principale de TransParix.
	 * 
	 * @return Les dimensions de la fenêtre.
	 */
	public Dimension getDimension() {
		return frame.getSize();
	}

	/**
	 * Affiche la liste des itinéraires dans le menu "Derniers itinéraires...".
	 */
	public void updateItineraries() {
		this.derniersItineraires.removeAll();
		for (LinkedList<Integer> list : this.graph.getLastItineraries()) {
			this.currentList = list;
			Station sD = this.graph.getStation(this.currentList.getFirst());
			Station sA = this.graph.getStation(this.currentList.getLast());
			String sDName = sD.getName();
			String sAName = sA.getName();
			
			// System.out.println(currentList.toString());
			
			String path = "De " + sDName + " à " + sAName + "...";
			JMenuItem pathL = new JMenuItem(path);
			pathL.setActionCommand(sDName+";"+sAName);
			pathL.addActionListener(new Listener(itineraryFrame,map,graph,sD, sA));
			this.derniersItineraires.add(pathL);
		}
	}

}

class Listener implements ActionListener {
	private Station d;
	private Station a;
	private Graph g;
	private Map m;
	private ItineraryFrame it;
	
	public Listener(ItineraryFrame it,Map m,Graph g,Station d, Station a) {
		this.d = d;
		this.a = a;
		this.g = g;
		this.it = it;
		this.m = m;
		
	}
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(d.getName());
		System.out.println(a.getName());
		BFS bfs = new BFS(g, d.getId(), a.getId());
		g.resetMarks();
		// FIXME le meme itineraire s'affiche toujours (le dernier)
		it = new ItineraryFrame(g, m, bfs.getPath());
		it.setLocationByPlatform(true);
		it.setVisible(true);
	}
}
