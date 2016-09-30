package tdd.vendingMachine;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ProductTest {

	@Test
	public void Product_has_a_name() {
		// when
		new Product("Mineral water");
	}

	@Test
	public void Product_should_throw_exception_when_name_is_null() {
		try {
			// when
			new Product(null);
			fail();
		} catch (NullPointerException expected) {
			// then
		}
	}
}
