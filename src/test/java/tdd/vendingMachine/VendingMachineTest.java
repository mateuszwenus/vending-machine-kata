package tdd.vendingMachine;

import static org.hamcrest.CoreMatchers.hasItems;
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
		assertThat(vendingMachine.getDisplay(), is(VendingMachine.INVALID_SHELVE_NUMBER_MSG));
	}
	
	@Test
	public void should_display_warning_when_negative_shelve_number_is_selected() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(100)))));
		// when
		vendingMachine.selectShelve(-1);
		// then
		assertThat(vendingMachine.getDisplay(), is(VendingMachine.INVALID_SHELVE_NUMBER_MSG));
	}
	
	@Test
	public void should_display_remaining_amount_after_selecting_a_shelve_and_inserting_a_coin() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		// when
		vendingMachine.insertCoin(Coin.ONE_ZL);
		// then
		assertThat(vendingMachine.getDisplay(), is("1.00"));
	}
	
	@Test
	public void should_return_inserted_coin_when_shelve_has_not_been_selected() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		// when
		vendingMachine.insertCoin(Coin.ONE_ZL);
		// then
		assertThat(vendingMachine.getReturnedCoins(), hasItems(Coin.ONE_ZL));
	}
	
	@Test
	public void should_dispense_product_after_inserting_exact_amount() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		vendingMachine.insertCoin(Coin.ONE_ZL);
		// when
		vendingMachine.insertCoin(Coin.ONE_ZL);
		// then
		assertThat(vendingMachine.takeDispensedProduct(), is(new Product("Mineral water", new Money(200))));
	}
}
