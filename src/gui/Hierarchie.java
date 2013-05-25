package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import structure.Graph;
import structure.Line;
import structure.Station;
import tools.Couple;
import tools.Utilities;
import java.awt.GridLayout;
import javax.swing.JLabel;

public class Hierarchie extends JPanel {
	private DefaultMutableTreeNode dmlines;
	private JTree treestart;
	private JTree treeend;
	private JScrollPane scrollPanestart, scrollPaneend;

	private final Graph graph;
	private Object start, end;
	private StationTreeSelectionListener tsl;


	public Object getStart() {
		return start;
	}

	public Object getEnd() {
		return end;
	}

	public Hierarchie(Graph g) {
		setLayout(new GridLayout(4, 1));
		graph = g;

		start = new Object();
		end = new Object();

		dmlines = new DefaultMutableTreeNode("Lignes");
		treestart = new JTree(dmlines);
		treeend = new JTree(dmlines);
		scrollPaneend = new JScrollPane();
		scrollPanestart = new JScrollPane();

		treestart.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		filltrees();

		treeend.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tsl = new StationTreeSelectionListener();

		treestart.addTreeSelectionListener(tsl);
		treeend.addTreeSelectionListener(tsl);

		scrollPanestart.setViewportView(treestart);
		scrollPaneend.setViewportView(treeend);

		add(new JLabel("Depart"));
		add(scrollPanestart);
		add(new JLabel("Arrivee"));
		add(scrollPaneend);
	}

	private void filltrees() {
		Iterator<Couple<String, Line>> it1 = graph.getLines().iterator();
		while (it1.hasNext()) {
			Couple<String, Line> ctmp = it1.next();
			// System.out.println(ctmp.first());
			DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(ctmp
					.second().getId());
			Iterator<Couple<Integer, Station>> it2 = graph.getStations()
					.iterator();
			while (it2.hasNext()) {
				Station stmp = it2.next().second();
				for (String s : stmp.getLines()) {
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
	}

	class StationTreeSelectionListener implements TreeSelectionListener {
		@Override
		public void valueChanged(TreeSelectionEvent e) {
			DefaultMutableTreeNode node = null;
			if (e.getSource().equals(treestart))
				node = (DefaultMutableTreeNode) treestart
						.getLastSelectedPathComponent();
			else if (e.getSource().equals(treeend)){
				node = (DefaultMutableTreeNode) treeend.getLastSelectedPathComponent();
			}
			/* if nothing is selected */
			if (node == null)
				return;
			Object nodeInfo = node.getUserObject();
			/* retrieve the node that was selected */
			if (e.getSource().equals(treeend) && node.isLeaf()) {
				end = nodeInfo;
				/* React to the node selection. */
				System.out.println("end changed -> start : " + start.toString()
						+ ", end : " + end.toString());

			}

			if (e.getSource().equals(treestart) && node.isLeaf()) {
				start = nodeInfo;
				/* React to the node selection. */
				System.out.println("start change -> start : "
						+ start.toString() + ", end : " + end.toString());
			}
		}
	}
}