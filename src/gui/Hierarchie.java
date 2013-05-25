package gui;

import itinerary.Graph;
import itinerary.Station;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import structure.Line;
import tools.Couple;
import tools.Utilities;

public class Hierarchie extends JPanel {
	private DefaultMutableTreeNode dmlines;
	private JTree tree;
	private JScrollPane scrollPane;
	private final String FILE_LINES = "data/data_v1/lignes.txt";
	private final String FILE_STATIONS = "data/data_v1/stations.txt";
	private final Graph graph;

	public Hierarchie(Graph g) {
		graph = g;

		dmlines = new DefaultMutableTreeNode("Lignes");
		tree = new JTree(dmlines);
		scrollPane = new JScrollPane();

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			}
		});

		Iterator<Couple<String, Line>> it1 = g.getLines().iterator();
		while (it1.hasNext()) {
			Couple<String, Line> ctmp = it1.next();
			// System.out.println(ctmp.first());
			DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(ctmp
					.second().getId());
			Iterator<Couple<Integer, Station>> it2 = g.getStations().iterator();
			while (it2.hasNext()) {
				Station stmp = it2.next().second();
				for (String s : stmp.getLignes()) {
					if (ctmp.first().equals(s)) {
						s = stmp.getName();
						if (ctmp.second().getIdArrivals()
								.contains(stmp.getId())
								|| ctmp.second().getIdDepartures()
										.contains(stmp.getId()))
							s += " - Terminus";
						treenode.add(new DefaultMutableTreeNode(s));
					}
				}
			}
			dmlines.add(treenode);

		}
		setLayout(new BorderLayout());
		scrollPane.setViewportView(tree);
		add(scrollPane);

	}
}