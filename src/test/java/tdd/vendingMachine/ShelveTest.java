package tdd.vendingMachine;

import org.junit.Test;

public class ShelveTest {

	@Test
	public void Shelve_contains_multiple_products_of_the_same_type() {
		// when
		new Shelve(5, new Product("Mineral water", new Money(100)));
	}
}
