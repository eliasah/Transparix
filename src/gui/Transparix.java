package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
	private JMenuItem btnTreeSearch;

	private TreeSelectionFrame frmTreeSearch;
	private ComboSelectionFrame frmComboSearch;
	private JMenuItem btnComboSearch;

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
		fichier.setMnemonic(KeyEvent.VK_F);
		recherche = new JMenu("Recherche");
		recherche.setMnemonic(KeyEvent.VK_R);

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

		recherche.add(btnTreeSearch);
		recherche.add(btnComboSearch);
		fichier.add(quitter);

		menubar.add(fichier);
		menubar.add(recherche);

		// plan de metro
		map = new Map(this, this.graph, 600, 600);

		// panel informations
		// TODO ajouter une classe InformationsStation qui herite de JPanel
		informations = new JPanel();// FIXME
		informations.setPreferredSize(new Dimension(200, 500));
		stationName = new JLabel();
		informations.add(stationName);

		// panel principal
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(600, 600));
		map.setPreferredSize(panel.getMaximumSize());
		panel.add(map, BorderLayout.CENTER);
		// FIXME
		// panel.add(informations, BorderLayout.EAST);

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

	public Map getMap() {
		return map;
	}

	public Dimension getDimension() {
		return frame.getSize();
	}

}
