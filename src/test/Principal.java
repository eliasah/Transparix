package test;

import gui.StationSelectionCombo;
import gui.TreeSelectionFrame;
import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import structure.Graph;

public class Principal {

	private JMenu fichier;
	private JFrame frame;
	private Graph graph;
	private Map map;
	private JMenuBar menubar;
	private JPanel panel, informations;
	private JMenuItem quitter;
	private JLabel stationName;

	private StationSelectionCombo stationSelection;
	private JMenu chercher;
	private JMenuItem arbre;
	protected TreeSelectionFrame tsf;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Principal prog = new Principal();
			}
		});
	}


	public Principal() {
		initialize();
	}

	void initialize() {
		tsf = new TreeSelectionFrame();
		
		this.graph = new Graph();
		// barre de menu
		menubar = new JMenuBar();
		fichier = new JMenu("Fichier");
		chercher = new JMenu("Chercher");
		arbre = new JMenuItem("Arbre");
		arbre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tsf.setVisible(true);
			}
		});
		quitter = new JMenuItem("Quitter");
		quitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		chercher.add(arbre);
		fichier.add(quitter);
		menubar.add(fichier);
		menubar.add(chercher);

		// plan de métro
		map = new Map(this, this.graph, 500, 500);
		// FIXME ajout d'un itinéraire bidon pour test
		//for (int i = 0; i < 10; i++) {
			BFS parcours = new BFS(this.graph, 1953, 1793);
			LinkedList<Integer> list = parcours.getPath();
			// map.drawPath(list);
			System.out.println(list.toString());
		// }

		// panel informations
		// TODO ajouter une classe InformationsStation qui hérite de JPanel
		informations = new JPanel();
		informations.setPreferredSize(new Dimension(200, 500));
		stationName = new JLabel();
		informations.add(stationName);

		// panel recherche station de métro
		// TODO
		stationSelection = new StationSelectionCombo(this.graph
				.stationsToHashtable().values());

		// panel recherche itinéraire
		// TODO

		// panel principal
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(stationSelection, BorderLayout.NORTH);
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
}
