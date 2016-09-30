package tdd.vendingMachine;

import org.junit.Test;

public class ProductTest {

	@Test
	public void Product_has_a_name() {
		// when
		new Product("Mineral water");
	}
}
