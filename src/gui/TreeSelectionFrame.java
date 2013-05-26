package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import structure.Graph;
import structure.Station;
import javax.swing.JLabel;

public class TreeSelectionFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panelButtons;
	private JButton btnChercher;
	private Hierarchie selectionh;
	// private Model model;
	private Map map;
	private Graph graph;

	public TreeSelectionFrame(Map m, Graph g) {
		setTitle("Hierarchie");
		// model = new Model(new Graph());
		graph = g;
		this.map = m;
		selectionh = new Hierarchie(graph);
		btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			@Override
			synchronized public void actionPerformed(ActionEvent e) {
				Object start = selectionh.getStart();
				Object end = selectionh.getEnd();
				if (start != null && end != null) {
					// Graph graph = model.getGraph();
					System.out.println("start :" +  start.toString() + ", end : " + end.toString());
					Station sStart = graph.getStation(start.toString());
					System.out.println("sStart " + graph.getStation(start.toString()));
					Station sEnd = graph.getStation(end.toString());
					System.out.println("sEnd " + graph.getStation(end.toString()));

					BFS bfs = new BFS(graph,sStart.getId(),sEnd.getId());
					graph.resetMarks();
					System.out.println(bfs.getPath().toString());
					map.drawPath(bfs.getPath());
				}
				// else {
				// System.out.println("ERREUR");
				// }
			}
		});

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());

		panelButtons.add(btnChercher, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(panelButtons, BorderLayout.EAST);
		contentPane.add(selectionh, BorderLayout.CENTER);
		setContentPane(contentPane);
	}
}