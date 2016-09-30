package tdd.vendingMachine;

public class Shelve {

	public Shelve(int numberOfProducts, Product product) {
		if (numberOfProducts < 0) {
			throw new IllegalArgumentException("number of products must not be < 0");
		}
		if (product == null) {
			throw new NullPointerException("product must not be null");
		}
	}
}
