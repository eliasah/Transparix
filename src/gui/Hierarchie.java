package gui;

import itinerary.Station;

import java.awt.Color;
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

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;



public class Hierarchie extends JPanel {
	private DefaultMutableTreeNode dmlines;
	private JTree tree;
	private JScrollPane scrollPane;
	private final String FILE_LINES = "data/data_v1/lignes.txt";
	private LinkedList<Couple<String, Line>> lines;

	public Hierarchie() {
		setLayout(null);
		lines = new LinkedList<Couple<String, Line>>();
		dmlines = new DefaultMutableTreeNode("Lignes");
		tree = new JTree(dmlines);
		scrollPane = new JScrollPane();
		lines = Utilities.extractLines(FILE_LINES);

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			}
		});

		Iterator<Couple<String, Line>> it = lines.iterator();
		while (it.hasNext()) {
			Couple<String, Line> ctmp = it.next();
			// System.out.println(ctmp.first());
			DefaultMutableTreeNode treenode = new DefaultMutableTreeNode(ctmp.second().getId());
			treenode.add(new DefaultMutableTreeNode(ctmp.second().getIdArrivals()));
			treenode.add(new DefaultMutableTreeNode(ctmp.second().getIdDepartures()));
			dmlines.add(treenode);
			
		}

		scrollPane.setBounds(12, 0, 426, 404);
		scrollPane.setViewportView(tree);
		add(scrollPane);

		// System.out.println(lines.toString());
	}
}