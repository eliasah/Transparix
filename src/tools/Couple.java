package tools;

public class Couple<K,V>{

	private K key;
	private V val;

	public Couple (K k, V v) {
		key = k;
		val = v;
	}

	public K first() {
		return key;
	}

	public V second() {
		return val;
	}

	@Override
	public String toString() {
		return "(" + key + ", " + val + ")";
	}
	
	

}