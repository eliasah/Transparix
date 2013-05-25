package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;

import examples.PanelStation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class MainUI {

	ArrayList<String> stations;
	
	private JFrame frmTransparix;
	private PanelStation cbStationDepart;
	private PanelStation cbStationArrivee;
	private JPanel pSelection;
	private JPanel pBouton;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frmTransparix.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainUI() {
		stations = new ArrayList();
		try {
			BufferedReader bf = new BufferedReader(new FileReader("data/data_v1/stations.txt"));
			try {
				String[] line;
				String str = bf.readLine();
				while(str!=null){
					line = str.split("#");
					// System.out.println(line[1]);
					stations.add(line[1]);
					str = bf.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found");
			e.printStackTrace();
		}
		initialize();
	}

	private void initialize() {
		frmTransparix = new JFrame();
		pSelection = new JPanel();
		pSelection.setLayout(new BorderLayout());
		pBouton = new JPanel();
		pBouton.setLayout(new BorderLayout());
		
		frmTransparix.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		frmTransparix.setTitle("Transparix");
		//frmTransparix.setPreferredSize(new Dimension(500, 320));
		frmTransparix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		cbStationDepart = new PanelStation("Depart", stations);
		cbStationArrivee = new PanelStation("Arrivee", stations);
		
		JButton search = new JButton("Chercher");
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Reaffect action listener
				System.exit(0);
				
			}
		});
		
		JButton quit = new JButton("Quitter");
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		pSelection.add(cbStationDepart,BorderLayout.NORTH);
		pSelection.add(cbStationArrivee,BorderLayout.SOUTH);
	
		pBouton.add(search,BorderLayout.WEST);
		pBouton.add(quit,BorderLayout.EAST);
	
		
		frmTransparix.getContentPane().add(pSelection,BorderLayout.NORTH);
		frmTransparix.getContentPane().add(pBouton,BorderLayout.SOUTH);
		frmTransparix.pack();
		frmTransparix.setVisible(true);
	}
}
