package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import structure.Station;

public class StationListener implements ActionListener {

	private Station station;
	private JLabel label;

	public StationListener(Station station, JLabel label) {
		this.station = station;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// FIXME
		System.out.println(this.station.getName());
		this.label.setText(this.station.getName());
	}
}
