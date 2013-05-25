package itinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Abou Haydar Elias - Univ. Paris Denis Diderot
 *
 */
public class Station{
	int id;
	int distance;
	String name;
	String  city;
	float latitude;
	float longitude;
	ArrayList<String> lines;
	HashMap<Integer,String> neighbours; // ligne , nom
	boolean visited;
	boolean marked;

	public boolean isMarked() {
		return marked;
	}

	public void mark(boolean marked) {
		this.marked = marked;
	}

	public Station(String nom) {
		this.name = nom;
		this.distance = 1;
	}
	
	public Station(int id, String nom, String ville, float latitude,
			float longitude, ArrayList<String> lignes,
			HashMap<Integer, String> voisins, boolean visited) {
		super();
		this.id = id;
		this.name = nom;
		this.city = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lines = lignes;
		this.neighbours = voisins;
		this.visited = visited;
		this.distance = 1;
	}

	public Station(String nom, int distance) {
		this.name = nom;
		this.distance = distance;
	}

	public Station() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVille() {
		return city;
	}

	public void setVille(String ville) {
		this.city = ville;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public ArrayList<String> getLignes() {
		return lines;
	}

	public void setLignes(ArrayList<String> lignes) {
		this.lines = lignes;
	}

	public HashMap<Integer, String> getVoisins() {
		return neighbours;
	}

	public void setVoisins(HashMap<Integer, String> voisins) {
		this.neighbours = voisins;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return name + ", " + lines;// + " , voisins = " + voisins;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}
