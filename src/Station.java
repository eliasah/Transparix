import java.util.LinkedList;

/**
 * Cette classe permet de sauvegarder les informations sur une 
 * station de métro, à savoir son identifiant dans la base de données 
 * de la RATP, son nom, la ville où elle se situe et ses coordonnées 
 * géographiques, les lignes auxquelles elle appartient et les stations
 * qui lui sont directement accessibles. 
 * 
 * @author isabelle
 *
 */
public class Station {

	private int id;
	private String name, city;
	private float latitude, longitude;
	private LinkedList<String> lines;
	private LinkedList<Couple<String,Integer>> neighbours;
	
	/**
	 * Constructeur d'une station.
	 * @param id L'identifiant de la station.
	 * @param name Le nom de la station.
	 * @param city La ville où se situe la station.
	 * @param latitude La latitude où se situe la station.
	 * @param longitude La longitude où se situe la station.
	 * @param lines Les lignes auxquelles appartient la station.
	 * @param neighbours Les stations voisines de la station.
	 */
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

	/**
	 * Renvoie l'identifiant de la station.
	 * @return L'identifiant de la station.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Renvoie le nom de la station.
	 * @return Le nom de la station.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Renvoie la ville où se situe la station.
	 * @return La ville où se situe la station.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Renvoie la latitude de la station.
	 * @return La latitude de la station.
	 */
	public float getLatitude() {
		return latitude;
	}

	/**
	 * Renvoie la longitude de la station.
	 * @return La longitude de la station.
	 */
	public float getLongitude() {
		return longitude;
	}

	/**
	 * Renvoie la liste des lignes auxquelles appartient la station.
	 * @return La liste des lignes auxquelles appartient la station.
	 */
	public LinkedList<String> getLines() {
		return lines;
	}

	/**
	 * Renvoie la liste des voisins de la station.
	 * @return La liste des voisins de la station.
	 */
	public LinkedList<Couple<String, Integer>> getNeighbours() {
		return neighbours;
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
}
