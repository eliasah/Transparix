package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JButton;

public class StationButton extends JButton {

	public StationButton(ColoredSquare cs) {
		super(cs);
		this.setSize(new Dimension(20, 20));
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(5, 5, 20, 20);
	}
}

class ColoredSquare implements Icon {
	Color color;

	public ColoredSquare(Color color) {
		this.color = color;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fill3DRect(x, y, getIconWidth(), getIconHeight(), true);
		g.setColor(oldColor);
	}

	@Override
	public int getIconWidth() {
		return 12;
	}

	@Override
	public int getIconHeight() {
		return 12;
	}
}