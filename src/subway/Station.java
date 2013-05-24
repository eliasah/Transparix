package subway;

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
	String nom;
	String  ville;
	float latitude;
	float longitude;
	ArrayList<String> lignes;
	HashMap<Integer,String> voisins; // ligne , nom
	boolean visited;
	boolean marked;

	public boolean isMarked() {
		return marked;
	}

	public void mark(boolean marked) {
		this.marked = marked;
	}

	public Station(String nom) {
		this.nom = nom;
		this.distance = 1;
	}
	
	public Station(int id, String nom, String ville, float latitude,
			float longitude, ArrayList<String> lignes,
			HashMap<Integer, String> voisins, boolean visited) {
		super();
		this.id = id;
		this.nom = nom;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lignes = lignes;
		this.voisins = voisins;
		this.visited = visited;
		this.distance = 1;
	}

	public Station(String nom, int distance) {
		this.nom = nom;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
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
		return lignes;
	}

	public void setLignes(ArrayList<String> lignes) {
		this.lignes = lignes;
	}

	public HashMap<Integer, String> getVoisins() {
		return voisins;
	}

	public void setVoisins(HashMap<Integer, String> voisins) {
		this.voisins = voisins;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return nom + ", " + lignes + ", voisins = " + voisins;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}