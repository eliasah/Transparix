package isabelle;

public class Couple<A, B> {

	private A a;
	private B b;

	public Couple (A a0, B b0) {
		a = a0;
		b = b0;
	}

	public A first() {
		return a;
	}

	public B second() {
		return b;
	}

}