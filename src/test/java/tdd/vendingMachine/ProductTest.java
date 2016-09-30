package tdd.vendingMachine;

import static org.junit.Assert.fail;

import org.junit.Test;

public class ProductTest {

	@Test
	public void Product_has_a_name_and_a_price() {
		// when
		new Product("Mineral water", new Money(100));
	}

	@Test
	public void Product_should_throw_exception_when_name_is_null() {
		try {
			// when
			new Product(null, new Money(100));
			fail();
		} catch (NullPointerException expected) {
			// then
		}
	}
	
	@Test
	public void Product_should_throw_exception_when_price_is_null() {
		try {
			// when
			new Product("Mineral water", null);
			fail();
		} catch (NullPointerException expected) {
			// then
		}
	}
	
	@Test
	public void Product_should_throw_exception_when_name_is_blank() {
		try {
			// when
			new Product(" 	", new Money(100));
			fail();
		} catch (IllegalArgumentException expected) {
			// then
		}
	}
}
