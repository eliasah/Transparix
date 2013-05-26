package structure;
import java.awt.Color;
import java.util.LinkedList;

/**
 * Cette classe permet de sauvegarder les informations sur une ligne de metro, 
 * a savoir son numero, ses stations de depart et ses stations d'arrivee.
 * 
 * @author isabelle
 *
 */
public class Line {
	
	private String id;
	private LinkedList<Integer> idDepartures, idArrivals;
	private Color color;
	
	/**
	 * Constructeur d'une ligne.
	 * @param id L'identitfiant de la ligne.
	 * @param idDepartures La liste des stations au depart de la ligne.
	 * @param idArrivals La liste des stations a l'arrivee de la ligne.
	 * @param color
	 */
	public Line(String id, LinkedList<Integer> idDepartures,
			LinkedList<Integer> idArrivals, Color color) {
		this.id = id;
		this.idDepartures = idDepartures;
		this.idArrivals = idArrivals;
		this.color = color;
	}

	/**
	 * Renvoie l'identifiant de la ligne.
	 * @return L'identifiant de la ligne.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Renvoie la liste des stations au depart de la ligne.
	 * @return La liste des stations au depart de la ligne.
	 */
	public LinkedList<Integer> getIdDepartures() {
		return idDepartures;
	}

	/**
	 * Renvoie la liste des stations a l'arrivee de la ligne.
	 * @return La liste des stations a l'arrivee de la ligne.
	 */
	public LinkedList<Integer> getIdArrivals() {
		return idArrivals;
	}

	/**
	 * Renvoie la couleur de la ligne.
	 * @return La couleur de la ligne.
	 */
	public Color getColor() {
		return color;
	}

}
