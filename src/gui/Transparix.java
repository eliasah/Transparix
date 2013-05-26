package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import structure.Graph;

/**
 * TODO
 * 
 * @author isabelle
 * 
 */
public class Transparix {

	private Graph graph;
	private JFrame frame;
	private JMenuBar menubar;
	private JMenu fichier;
	private JMenuItem quitter;
	private Map map;
	private JPanel panel, informations;
	private JLabel stationName;

	private StationSelectionCombo stationSelection;

	private JMenu recherche;
	private JMenuItem btnhierarchie;

	private TreeSelectionFrame frmTreeSearch;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
	 * Retourne le label qui contient le nom de la station sur lequel
	 * l'utilisateur a clique.
	 * 
	 * @return Le nom de la station.
	 */
	public JLabel getStationName() {
		return stationName;
	}

	public void initialize() {

		// barre de menu
		menubar = new JMenuBar();
		fichier = new JMenu("Fichier");
		recherche = new JMenu("Recherche");

		btnhierarchie = new JMenuItem("TreeSearch");
		btnhierarchie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frmTreeSearch = new TreeSelectionFrame(map,graph);
				frmTreeSearch.setLocationByPlatform(true);
				frmTreeSearch.setVisible(true);
			}
		});

		quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		recherche.add(btnhierarchie);
		fichier.add(quitter);

		menubar.add(fichier);
		menubar.add(recherche);

		// plan de metro
		map = new Map(this, this.graph, 600, 600);
		
		// FIXME ajout d'un itineraire bidon pour test
		// BFS parcours = new BFS(this.graph, 1953, 1793);
		// LinkedList<Integer> list = parcours.getPath();
		
		// map.drawPath(list);
		// System.out.println(list.toString());

		// panel informations
		// TODO ajouter une classe InformationsStation qui herite de JPanel
		informations = new JPanel();
		informations.setPreferredSize(new Dimension(200, 500));
		stationName = new JLabel();
		informations.add(stationName);

		// panel recherche station de metro
		// TODO
		stationSelection = new StationSelectionCombo(this.graph
				.stationsToHashtable().values());

		// panel recherche itineraire
		// TODO

		// panel principal
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(600,600));
		panel.add(stationSelection, BorderLayout.NORTH);
		map.setPreferredSize(panel.getMaximumSize());
		panel.add(map, BorderLayout.WEST);
		panel.add(informations, BorderLayout.EAST);

		// fenetre principale
		frame = new JFrame("TransParix");
		// frame.setLocationRelativeTo(null);
		Point p = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getCenterPoint();
		frame.setLocation((int) (p.getX() - 300), (int) (p.getY() - 300));
		
		frame.setJMenuBar(menubar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
	
	public Map getMap(){
		return map;
	}

	public Dimension getDimension(){
		return frame.getSize();
	}
	
}
