package examples;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainUI {

	private JFrame frmTransparix;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		initialize();
	}

	private void initialize() {
		frmTransparix = new JFrame();
		frmTransparix.setTitle("Transparix");
		frmTransparix.setBounds(100, 100, 450, 300);
		frmTransparix.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComboBox comboBox = new JComboBox(new Object[] {"Ester", "Jordi", "Jordina", "Jorge", "Sergi"});
		AutoCompletion.enable(comboBox);
		frmTransparix.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frmTransparix.getContentPane().add(comboBox);
		
		JButton hello = new JButton("Hello");
		
		JButton quit = new JButton("Quitter");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		frmTransparix.getContentPane().add(hello);
		frmTransparix.getContentPane().add(quit);
	}

}
