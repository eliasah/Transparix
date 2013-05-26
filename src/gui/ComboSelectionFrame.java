package gui;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import structure.Graph;
import structure.Station;
import tools.Couple;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ComboSelectionFrame extends JFrame {

	private JPanel contentPane;
	private StationSelectionCombo sscStart;
	private StationSelectionCombo sscEnd;
	private JPanel pnlStart;
	private JPanel pnlEnd;
	private JButton btnChercher;
	private JPanel pnlButton;

	private Map map;
	private Graph graph;

	public ComboSelectionFrame(Map m, Graph g) {
		map = m;

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.graph = g;
		LinkedList<Couple<Integer, Station>> stations = g.getStations();

		sscStart = new StationSelectionCombo(stations);
		sscEnd = new StationSelectionCombo(stations);

		pnlStart = new JPanel();
		pnlStart.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Depart",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlStart.setLayout(new BorderLayout(0, 0));
		pnlStart.add(sscStart);

		btnChercher = new JButton("Chercher");
		btnChercher.setMnemonic(KeyEvent.VK_C);
		btnChercher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object start = sscStart.getSelectedItem();
				Object end = sscEnd.getSelectedItem();
				if (start != null && end != null) {
					// System.out.println("start :" + start.toString()
					// + ", end : " + end.toString());
					Station sStart = graph.getStation(start.toString());
					// System.out.println("sStart "
					// + graph.getStation(start.toString()));
					Station sEnd = graph.getStation(end.toString());
					// System.out.println("sEnd "
					// + graph.getStation(end.toString()));
					if (sStart == null || sEnd == null) {
						showErrorDialog();
					} else {
						BFS bfs = new BFS(graph, sStart.getId(), sEnd.getId());
						graph.resetMarks();
						// FIXME
						// System.out.println(bfs.getPath().toString());

						// affichage de l'itinéraire
						ItineraryFrame itineraryFrame = new ItineraryFrame(
								graph, map, bfs.getPath());
						itineraryFrame.setLocationByPlatform(true);
						itineraryFrame.setVisible(true);

						map.drawPath(bfs.getPath());
					}
				} else {
					showErrorDialog();
				}
			}
		});

		pnlButton = new JPanel();
		pnlButton.setBorder(null);
		pnlButton.setLayout(new BorderLayout(0, 0));
		pnlButton.add(btnChercher, BorderLayout.CENTER);

		pnlEnd = new JPanel();
		pnlEnd.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Arrivee",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlEnd.setLayout(new BorderLayout(0, 0));
		pnlEnd.add(sscEnd, BorderLayout.CENTER);

		// contentPane initialized
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(3, 1));

		contentPane.add(pnlStart);
		contentPane.add(pnlEnd);
		contentPane.add(pnlButton);

		setContentPane(contentPane);
	}

	public void showErrorDialog() {
		JOptionPane.showMessageDialog(null,
				"Vous n'avez pas sélectionné les stations correctement.",
				"Attention!", JOptionPane.WARNING_MESSAGE);
	}
}
