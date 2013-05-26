package test;

import java.util.LinkedList;

import structure.Graph;
import structure.Line;
import tools.Couple;

public class TestInfosLines {
	public static void main(String[] args) {
		Graph graph = new Graph();
		LinkedList<Couple<String, Line>> lines = graph.getLines();
		for (Couple<String,Line> c : lines) {
			Line l = c.second();
			System.out.println("Nombre de stations de la ligne " + l.getId()
					+ ": " + l.getNbStations());
			System.out.println("Nombre d'intersections de la ligne " + l.getId()
					+ ": " + l.getNbIntersections());
			System.out.println("Longueur de la ligne " + l.getId()
					+ ": " + l.getLength());
		}
	}
}
