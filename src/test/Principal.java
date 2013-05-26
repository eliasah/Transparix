package test;

import gui.Model;
import gui.StationSelectionCombo;
import gui.TreeSelectionFrame;
import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import structure.Graph;

public class Principal {

	private JMenu fichier;
	private JFrame frame;
	private Model model;
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
		model = new Model(new Graph());
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

		// plan de metro
		map = new Map(this, model.getGraph(), 500, 500);
		// FIXME ajout d'un itineraire bidon pour test
		//for (int i = 0; i < 10; i++) {
			BFS parcours = new BFS(model.getGraph(), 1953, 1793);
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

		// panel recherche station de metro
		// TODO
		stationSelection = new StationSelectionCombo(model.getGraph()
				.stationsToHashtable().values());

		// panel recherche itineraire
		// TODO

		// panel principal
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(stationSelection, BorderLayout.NORTH);
		panel.add(map, BorderLayout.WEST);
		panel.add(informations, BorderLayout.EAST);

		// fenetre principale
		frame = new JFrame("TransParix");
		frame.setJMenuBar(menubar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);

	}
}
