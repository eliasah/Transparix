
public class Couple<A, B> extends java.lang.Object {

	private A a;
	private B b;

	Couple (A a0, B b0) {
		a = a0;
		b = b0;
	}

	A premier () {
		return a;
	}

	B second () {
		return b;
	}

}