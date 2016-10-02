package tdd.vendingMachine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void VendingMachine_has_shelves() {
		// when
		new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(100)))));
	}

	@Test
	public void VendingMachine_should_throw_exception_when_shelves_are_null() {
		try {
			// when
			new VendingMachine(null);
			fail();
		} catch (NullPointerException expected) {
			// then
		}
	}

	@Test
	public void VendingMachine_should_throw_exception_when_shelves_is_empty_list() {
		try {
			// when
			new VendingMachine(Collections.emptyList());
			fail();
		} catch (IllegalArgumentException expected) {
			// then
		}
	}

	@Test
	public void should_display_product_price_when_valid_shelve_number_is_selected() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(100)))));
		// when
		vendingMachine.selectShelve(0);
		// then
		assertThat(vendingMachine.getDisplay(), is("1.00"));
	}

	@Test
	public void should_display_warning_when_invalid_shelve_number_is_selected() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(100)))));
		// when
		vendingMachine.selectShelve(10);
		// then
		assertThat(vendingMachine.getDisplay(), is("invalid shelve number"));
	}
}
