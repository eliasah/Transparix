package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import structure.Station;

public class StationListener implements ActionListener {

	private Station station;
	private Transparix parent;

	public StationListener(Station station, Transparix parent) {
		this.station = station;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// FIXME
		parent.getStationName().setText(station.getName());

	}
}
