package gui;

import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import structure.Graph;

/**
 * 
 * @author Isabelle Richard - Univ. Paris Denis Diderot
 *
 */
public class ItineraryFrame extends JFrame {

	private Graph graph;
	private Map map;
	private LinkedList<Integer> path;

	public ItineraryFrame(Graph graph, Map map, LinkedList<Integer> path) {
		this.graph = graph;
		this.map = map;
		this.path = path;

		this.setTitle("Itinéraire");
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.showItinerary();

		this.pack();
	}

	public void showItinerary() {
		System.out.println(path);
		for (int i : this.path) {
			this.add(new JLabel(this.graph.getStation(i).getName()));
		}
		this.map.drawPath(this.path);
	}
}
