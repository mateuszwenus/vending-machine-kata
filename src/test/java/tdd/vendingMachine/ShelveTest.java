package tdd.vendingMachine;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ShelveTest {

	@Test
	public void Shelve_contains_multiple_products_of_the_same_type() {
		// when
		new Shelve(5, new Product("Mineral water", new Money(100)));
	}
	
	@Test
	public void Shelve_should_throw_exception_when_number_of_products_is_negative() {
		try {
			// when
			new Shelve(-1, new Product("Mineral water", new Money(100)));
			fail();
		} catch (IllegalArgumentException expected) {
			// then
		}
	}
}
