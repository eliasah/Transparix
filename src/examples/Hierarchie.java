package examples;

import isabelle.Line;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

import subway.Station;

public class Hierarchie extends JPanel implements TreeSelectionListener {
	JTree tree;
	private final String FILE_LINES = "data/data_v1/lignes.txt";
	private Hashtable<String, Line> lines;

	public Hierarchie() {
		setLayout(null);
		lines = new Hashtable<String, Line>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 0, 426, 404);
		add(scrollPane);

		DefaultMutableTreeNode dlines = new DefaultMutableTreeNode();
		

		tree = new JTree(dlines);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
			}
		});
		scrollPane.setViewportView(tree);
		extractLines(FILE_LINES);
		System.out.println("TEST");
		System.out.println(lines.toString());
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Extrait les informations sur les lignes qui composent le Métro Parisien.
	 * 
	 * @param filePath
	 *            Le chemin du fichier contenant les lignes.
	 * @throws IOException
	 */
	public void extractLines(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] args = line.split("#");
				String id = args[0];
				LinkedList<Integer> idDepartures = new LinkedList<Integer>();
				String[] idDeparturesLine = args[1].split(";");
				for (String s : idDeparturesLine) {
					idDepartures.add(Integer.parseInt(s));
				}
				LinkedList<Integer> idArrivals = new LinkedList<Integer>();
				String[] idArrivalsLine = args[2].split(";");
				for (String s : idArrivalsLine) {
					idArrivals.add(Integer.parseInt(s));
				}
				String[] rgb = args[3].split(",");
				int r = Integer.parseInt(rgb[0]);
				int g = Integer.parseInt(rgb[1]);
				int b = Integer.parseInt(rgb[2]);
				this.lines.put(id, new Line(id, idDepartures, idArrivals,
						new Color(r, g, b)));
				System.out.println(id);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}