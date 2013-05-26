package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import structure.Graph;
import structure.Station;

public class TreeSelectionFrame extends JFrame {

	private JPanel contentPane;
	private JPanel panelButtons;
	private JButton btnChercher;
	private Hierarchie selectionh;
	private Map map;
	private Graph graph;

	public TreeSelectionFrame(Map m, Graph g) {
		setTitle("Hierarchie");
		graph = g;
		this.map = m;
		selectionh = new Hierarchie(graph);
		btnChercher = new JButton("Chercher");
		btnChercher.setMnemonic(KeyEvent.VK_C);
		btnChercher.addActionListener(new ActionListener() {
			@Override
			synchronized public void actionPerformed(ActionEvent e) {
				Object start = selectionh.getStart();
				Object end = selectionh.getEnd();
				if (start != null && end != null) {
					// System.out.println("start :" + start.toString() +
					// ", end : " + end.toString());
					Station sStart = graph.getStation(start.toString());
					// System.out.println("sStart " +
					// graph.getStation(start.toString()));
					Station sEnd = graph.getStation(end.toString());
					// System.out.println("sEnd " +
					// graph.getStation(end.toString()));

					BFS bfs = new BFS(graph, sStart.getId(), sEnd.getId());
					graph.resetMarks();
					// FIXME
//					System.out.println(bfs.getPath().toString());
					
					// affichage de l'itinéraire
					ItineraryFrame itineraryFrame = new ItineraryFrame(map,
							graph);
					itineraryFrame.setLocationByPlatform(true);
					itineraryFrame.setVisible(true);
					
					map.drawPath(bfs.getPath());
				}
			}
		});

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());

		panelButtons.add(btnChercher, BorderLayout.SOUTH);

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(panelButtons, BorderLayout.EAST);
		contentPane.add(selectionh, BorderLayout.CENTER);
		setContentPane(contentPane);
	}
}