package tdd.vendingMachine;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
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

	@Test
	public void Shelve_should_throw_exception_when_product_is_null() {
		try {
			// when
			new Shelve(5, null);
			fail();
		} catch (NullPointerException expected) {
			// then
		}
	}
	
	@Test
	public void should_not_take_product_from_empty_shelve() {
		// given
		Shelve shelve = new Shelve(0, new Product("Mineral water", new Money(100)));
		// when
		Product product = shelve.takeProduct();
		// then
		assertThat(product, is(nullValue()));
		assertThat(shelve.getNumberOfProducts(), is(0));
	}
}
