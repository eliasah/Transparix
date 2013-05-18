import java.util.LinkedList;

public class Station {

	int id;
	String name, city;
	float latitude, longitude;
	LinkedList<String> lines;
	LinkedList<Couple<String,Integer>> neighbours;
	
	public Station(int id, String name, String city, float latitude, float longitude,
			LinkedList<String> lines, LinkedList<Couple<String,Integer>> neighbours) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.latitude = latitude;
		this.longitude = longitude;
		this.lines = lines;
		this.neighbours = neighbours;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public LinkedList<String> getLines() {
		return lines;
	}

	public void setLines(LinkedList<String> lines) {
		this.lines = lines;
	}

	public LinkedList<Couple<String, Integer>> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(LinkedList<Couple<String, Integer>> neighbours) {
		this.neighbours = neighbours;
	}
	
	public String toString() {
		String str = "";
		str += this.id + ", ";
		str += this.name + ", ";
		str += this.city + ", ";
		str += this.latitude + ", ";
		str += this.longitude + ", ";
		// TODO afficher lignes et voisins 
		return str;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name;
	}
}
