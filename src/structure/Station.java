package structure;

import java.util.ArrayList;
import java.util.HashMap;

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
	private HashMap<Integer,String> neighbours; // nom, ligne
	private boolean visited;

	public Station() {
		// TODO Auto-generated constructor stub
	}

	public Station(int id, String name, String city, float latitude,
			float longitude, ArrayList<String> lines,
			HashMap<Integer, String> neighbours, boolean visited) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lines = lines;
		this.neighbours = neighbours;
		this.visited = visited;
		this.distance = 1;
	}

	public Station(String name) {
		this.name = name;
		this.distance = 1;
	}
	
	public Station(String name, int distance) {
		this.name = name;
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

	public ArrayList<String> getLines() {
		return lines;
	}

	public float getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public HashMap<Integer, String> getNeighbours() {
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

	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setNeighbours(HashMap<Integer, String> neighbours) {
		this.neighbours = neighbours;
	}

	@Override
	public String toString() {
		return name + ", " + lines;// + " , voisins = " + voisins;
	}
}
