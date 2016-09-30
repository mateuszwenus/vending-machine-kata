package tdd.vendingMachine;

public class Product {

	public Product(String name, Money price) {
		if (name == null) {
			throw new NullPointerException("name must not be null");
		}
		if (name.matches("\\s*")) {
			throw new IllegalArgumentException("name must not be blank");
		}
		if (price == null) {
			throw new NullPointerException("price must not be null");
		}
	}
}
