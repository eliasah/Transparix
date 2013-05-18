import java.util.LinkedList;


public class Line {
	
	String id;
	LinkedList<Integer> idDepartures, idArrivals;
	
	public Line(String id, LinkedList<Integer> idDepartures,
			LinkedList<Integer> idArrivals) {
		this.id = id;
		this.idDepartures = idDepartures;
		this.idArrivals = idArrivals;
	}

}
