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
	private Model model;
	private Map map;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TreeSelectionFrame frame = new TreeSelectionFrame(new Graph(),new Map());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public TreeSelectionFrame(Map m) {
		setTitle("Hierarchie");
		model = new Model(new Graph());
		this.map = m;
		selectionh = new Hierarchie(model.getGraph());
		btnChercher = new JButton("Chercher");
		btnChercher.addActionListener(new ActionListener() {
			@Override
			synchronized public void actionPerformed(ActionEvent e) {
				Object start = selectionh.getStart();
				Object end = selectionh.getEnd();
				if (start != null && end != null) {
					Graph graph = model.getGraph();
					model.setsStart(graph.getStation(start.toString()));
					model.setsEnd(graph.getStation(end.toString()));
					// System.out.println("sStart " + sStart.toString() + "sEnd" + sEnd.toString());
					BFS bfs = new BFS(graph,model.getsStart().getId(),model.getsEnd().getId());
					graph.resetMarks();	
					System.out.println(bfs.getPath().toString());
					map.drawPath(bfs.getPath());
				}	
				//else {
					// System.out.println("ERREUR");
				//}
			}
		});

		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		
		panelButtons.add(btnChercher,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(panelButtons,BorderLayout.EAST);
		contentPane.add(selectionh,BorderLayout.CENTER);
		setContentPane(contentPane);
	}
}