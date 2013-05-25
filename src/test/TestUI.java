package test;

import gui.Hierarchie;

import itinerary.Graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import structure.Line;

public class TestUI {

	private final Graph graph = new Graph();
	private JFrame frame;
	private Hashtable<String,Line> lines;
	
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

		JButton quit = new JButton("Quit");
		quit.setBounds(336, 159, 100, 100);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		Hierarchie h = new Hierarchie(graph);
		frame.getContentPane().add(h,BorderLayout.CENTER);
		frame.getContentPane().add(quit,BorderLayout.EAST);
	}
}
