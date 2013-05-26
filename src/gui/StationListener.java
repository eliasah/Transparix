package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

import structure.Station;

public class StationListener implements ActionListener {

	private Station station;

	public StationListener(Station station) {
		this.station = station;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// FIXME
		System.out.println(this.station.getName());
	}
}
