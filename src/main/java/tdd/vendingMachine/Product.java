package tdd.vendingMachine;

public class Product {

	public Product(String name, Money price) {
		if (name == null) {
			throw new NullPointerException("name must not ne null");
		}
	}
}
