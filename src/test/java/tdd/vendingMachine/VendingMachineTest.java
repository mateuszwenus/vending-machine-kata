package tdd.vendingMachine;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		assertThat(vendingMachine.takeReturnedCoins(), hasItems(Coin.ONE_ZL));
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
	
	@Test
	public void should_decrease_number_of_products_on_shelve_after_dispensing_a_product() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		// when
		vendingMachine.insertCoin(Coin.TWO_ZL);
		// then
		assertThat(vendingMachine.getShelve(0).getNumberOfProducts(), is(0));
	}
	
	@Test
	public void should_return_inserted_money_after_pressing_cancel() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		vendingMachine.insertCoin(Coin.TEN_GR);
		vendingMachine.insertCoin(Coin.TWENTY_GR);
		// when
		vendingMachine.pressCancel();
		// then
		assertThat(vendingMachine.takeReturnedCoins(), hasItems(Coin.TEN_GR, Coin.TWENTY_GR));
	}
	
	@Test
	public void should_return_inserted_money_only_once_after_pressing_cancel() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		vendingMachine.insertCoin(Coin.TEN_GR);
		vendingMachine.insertCoin(Coin.TWENTY_GR);
		vendingMachine.pressCancel();
		vendingMachine.takeReturnedCoins();
		// when
		vendingMachine.pressCancel();
		// then
		assertThat(vendingMachine.takeReturnedCoins(), not(hasItem(any(Coin.class))));
	}
	
	@Test
	public void should_not_be_possible_to_return_inserted_money_after_dispensing_a_product() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		vendingMachine.insertCoin(Coin.TWO_ZL);
		// when
		vendingMachine.pressCancel();
		// then
		assertThat(vendingMachine.takeReturnedCoins(), not(hasItem(any(Coin.class))));
	}
	
	@Test
	public void display_should_initially_be_empty() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		// when
		String displayedText = vendingMachine.getDisplay();
		// then
		assertThat(displayedText, is(""));
	}
	
	@Test
	public void should_return_to_initial_state_after_pressing_cancel() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200)))));
		vendingMachine.selectShelve(0);
		// when
		vendingMachine.pressCancel();
		// then
		assertThat(vendingMachine.getDisplay(), is(""));
	}
	
	@Test
	public void should_do_nothing_when_shelve_is_already_selected_and_user_selects_another_shelve() {
		// given
		Shelve mineralWaterShelve = new Shelve(1, new Product("Mineral water", new Money(200)));
		Shelve chocolateShelve = new Shelve(1, new Product("Chocolate", new Money(300)));
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(mineralWaterShelve, chocolateShelve));
		vendingMachine.selectShelve(0);
		// when
		vendingMachine.selectShelve(1);
		// then
		assertThat(vendingMachine.getDisplay(), is("2.00"));
	}
	
	@Test
	public void should_allow_to_select_another_shelve_after_pressing_cancel() {
		// given
		Shelve mineralWaterShelve = new Shelve(1, new Product("Mineral water", new Money(200)));
		Shelve chocolateShelve = new Shelve(1, new Product("Chocolate", new Money(300)));
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(mineralWaterShelve, chocolateShelve));
		vendingMachine.selectShelve(0);
		vendingMachine.pressCancel();
		// when
		vendingMachine.selectShelve(1);
		// then
		assertThat(vendingMachine.getDisplay(), is("3.00"));
	}
	
	@Test
	public void should_display_warning_when_empty_shelve_is_selected() {
		// given
		VendingMachine vendingMachine = new VendingMachine(Arrays.asList(new Shelve(0, new Product("Mineral water", new Money(200)))));
		// when
		vendingMachine.selectShelve(0);
		// then
		assertThat(vendingMachine.getDisplay(), is(VendingMachine.EMPTY_SHELVE_MSG));
	}
	
	@Test
	public void should_give_change_using_coins_supplied_at_start() {
		// given
		List<Shelve> shelves = Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(200))));
		List<Coin> startingCoins = Arrays.asList(Coin.ONE_ZL, Coin.ONE_ZL, Coin.FIFTY_GR, Coin.FIFTY_GR);
		VendingMachine vendingMachine = new VendingMachine(shelves, startingCoins);
		vendingMachine.selectShelve(0);
		// when
		vendingMachine.insertCoin(Coin.FIVE_ZL);
		// then
		assertThat(vendingMachine.takeDispensedProduct(), is(new Product("Mineral water", new Money(200))));
		List<Coin> returnedCoins = vendingMachine.takeReturnedCoins();
		assertThat(returnedCoins.size(), is(4));
		assertThat(returnedCoins, hasItems(Coin.ONE_ZL, Coin.ONE_ZL, Coin.FIFTY_GR, Coin.FIFTY_GR));
	}
	
	@Test
	public void should_give_change_using_coins_from_previous_buyers() {
		// given
		List<Shelve> shelves = Arrays.asList(new Shelve(2, new Product("Mineral water", new Money(100))));
		VendingMachine vendingMachine = new VendingMachine(shelves);
		vendingMachine.selectShelve(0);
		vendingMachine.insertCoin(Coin.ONE_ZL);
		vendingMachine.selectShelve(0);
		// when
		vendingMachine.insertCoin(Coin.TWO_ZL);
		// then
		assertThat(vendingMachine.takeDispensedProduct(), is(new Product("Mineral water", new Money(100))));
		List<Coin> returnedCoins = vendingMachine.takeReturnedCoins();
		assertThat(returnedCoins.size(), is(1));
		assertThat(returnedCoins, hasItem(Coin.ONE_ZL));
	}
}
