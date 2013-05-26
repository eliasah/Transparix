package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import structure.Graph;
import structure.Station;

public class ItineraryFrame extends JFrame {

	private Map map;
	private Graph graph;

	public ItineraryFrame(Map map, Graph graph) {
		this.map = map;
		this.graph = graph;

		this.setTitle("Itinéraire");
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

		this.getContentPane().setLayout(
				new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		this.showItinerary();

		this.pack();
	}

	public void showItinerary() {
		LinkedList<Integer> itinerary = this.graph.getLastItineraries()
				.getLast();
		for (int i : itinerary) {
			Station s = this.graph.getStation(i);
			this.add(new JLabel(s.getName()));
		}
	}
}
