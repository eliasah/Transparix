package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class Transparix implements Runnable {

	private Graph graph;

	private JFrame frame;
	private JMenuBar menubar;
	private JMenu fichier;
	private JMenuItem quitter;
	private Map map;
	private JPanel panel, informations;
	private JLabel stationName;
	private StationSelectionCombo stationSelection;

	/**
	 * Constructeur qui charge les données de l'application TransParix.
	 * 
	 * @throws IOException
	 */
	public Transparix() {
		this.graph = new Graph();
	}

	/**
	 * Retourne le label qui contient le nom de la station sur lequel
	 * l'utilisateur a cliqué.
	 * 
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
		recherche = new JMenu("Recherche");
		
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
		map = new Map(this, this.graph, 600, 600);
		// FIXME ajout d'un itinéraire bidon pour test
		BFS parcours = new BFS(this.graph, 1953, 1793);
		LinkedList<Integer> list = parcours.getPath();
		// map.drawPath(list);
		System.out.println(list.toString());

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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Transparix());
	}

}
