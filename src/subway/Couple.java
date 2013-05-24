package subway;

public class Couple {

	private int id;
	private Station station;

	public Couple (int i, Station s) {
		id = i;
		station = s;
	}

	public int first() {
		return id;
	}

	public Station second() {
		return station;
	}

}