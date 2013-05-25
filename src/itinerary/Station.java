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
	
	private boolean available;
	private String  city;
	private int distance;
	private int id;
	private float latitude;
	private ArrayList<String> lines;
	private float longitude;
	private boolean marked;
	private String name;
	private HashMap<Integer,String> neighbours; // ligne , nom
	private boolean visited;

	public Station() {
		// TODO Auto-generated constructor stub
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

	public Station(String nom) {
		this.name = nom;
		this.distance = 1;
	}
	
	public Station(String nom, int distance) {
		this.name = nom;
		this.distance = distance;
	}

	public int getDistance() {
		return distance;
	}

	public int getId() {
		return id;
	}

	public float getLatitude() {
		return latitude;
	}

	public ArrayList<String> getLignes() {
		return lines;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public String getVille() {
		return city;
	}

	public HashMap<Integer, String> getVoisins() {
		return neighbours;
	}

	public boolean isAvailable() {
		return available;
	}

	public boolean isMarked() {
		return marked;
	}

	public boolean isVisited() {
		return visited;
	}

	public void mark(boolean marked) {
		this.marked = marked;
	}

	public void setAvailable(boolean b){
		available = b;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLignes(ArrayList<String> lignes) {
		this.lines = lignes;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVille(String ville) {
		this.city = ville;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setVoisins(HashMap<Integer, String> voisins) {
		this.neighbours = voisins;
	}

	@Override
	public String toString() {
		return name + ", " + lines;// + " , voisins = " + voisins;
	}
}
