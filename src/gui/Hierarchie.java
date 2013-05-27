package gui;


import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import structure.Graph;
import structure.Line;
import structure.Station;
import tools.Couple;
import java.awt.GridLayout;
import javax.swing.JLabel;
/**
 * 
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 *
 */
public class Hierarchie extends JPanel {
	private DefaultMutableTreeNode dmlines;
	private JTree treestart;
	private JTree treeend;
	private JScrollPane scrollPanestart, scrollPaneend;

	private final Graph graph;
	private Object start = null, end = null;
	private StationTreeSelectionListener tsl;
	private JLabel lblDepart;
	private JLabel lblArrivee;
	private DefaultMutableTreeNode dmstations;


	public Object getStart() {
		return start;
	}

	public Object getEnd() {
		return end;
	}

	public Hierarchie(Graph g) {
		setLayout(new GridLayout(2, 1));
		graph = g;

		dmlines = new DefaultMutableTreeNode("Lignes");
		dmstations = new DefaultMutableTreeNode("Stations");
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

		add(scrollPanestart);
		
		lblDepart = new JLabel("Depart");
		scrollPanestart.setColumnHeaderView(lblDepart);
		add(scrollPaneend);
		
		lblArrivee = new JLabel("Arrivee");
		scrollPaneend.setColumnHeaderView(lblArrivee);
	}

	/**
	 * Fill the JTree
	 */
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
//						if (ctmp.second().getIdArrivals()
//								.contains(stmp.getId())
//								|| ctmp.second().getIdDepartures()
//										.contains(stmp.getId()))
//							s += " - Terminus";
						treenode.add(new DefaultMutableTreeNode(s));
					}
				}
			}
			dmlines.add(treenode);
		}
		
		// Toutes les stations
		dmlines.add(new DefaultMutableTreeNode("Toutes"));
		DefaultMutableTreeNode lf = dmlines.getLastLeaf();
		for (Couple<Integer,Station> c : graph.getStations()){
			lf.add((new DefaultMutableTreeNode(c.second().getName())));
		}
		
		// Par Zone
		// TODO
		
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
				// System.out.println("end changed -> start : " + start.toString()	+ ", end : " + end.toString());

			}

			if (e.getSource().equals(treestart) && node.isLeaf()) {
				start = nodeInfo;
				/* React to the node selection. */
				// System.out.println("start change -> start : "
				//		+ start.toString() + ", end : " + end.toString());
			}
		}
	}
}