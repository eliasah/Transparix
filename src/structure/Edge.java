package structure;

public class Edge  {
	private final String id; 
	private final Station source;
	private final Station destination;
	private final int weight; 
	
	public Edge(String id, Station source, Station destination, int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public String getId() {
		return id;
	}
	public Station getDestination() {
		return destination;
	}

	public Station getSource() {
		return source;
	}
	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return source + " " + destination;
	}
	
	
}
