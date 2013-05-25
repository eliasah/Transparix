package test;

import gui.Hierarchie;

import itinerary.BFS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import structure.Graph;
import structure.Line;
import structure.Station;

public class TestUI {

	private final Graph graph = new Graph();
	private JFrame frame;
	private Hashtable<String, Line> lines;
	private Hierarchie h;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TestUI window = new TestUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		h = new Hierarchie(graph);
			
		JButton quit = new JButton("Quit");
		quit.setBounds(336, 159, 100, 100);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JButton calcul = new JButton("Calcul");
		calcul.addActionListener(new ActionListener() {
			
			@Override
			synchronized public void actionPerformed(ActionEvent arg0) {
				Object start = h.getStart();
				Object end = h.getEnd();
				if (start != null && end != null) {
					Station sStart = graph.getStation(start.toString());
					Station sEnd = graph.getStation(end.toString());
					System.out.println("sEnd " + sEnd.toString());
					System.out.println("sStart " + sStart.toString());
					BFS parcours = new BFS(graph,sStart.getId(),sEnd.getId());
					System.out.println(parcours.getPath());
					parcours.printPath();
					graph.resetMarks();
					
				}
			}
		});
		
		frame.getContentPane().add(h,BorderLayout.CENTER);
		frame.getContentPane().add(quit,BorderLayout.EAST);
		frame.getContentPane().add(calcul,BorderLayout.WEST);
	}
}
