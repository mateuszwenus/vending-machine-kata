package tdd.vendingMachine;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class ChangeGiverTest {

	@Test
	public void ChangeGiver_should_throw_exception_when_amount_is_nonpositive() {
		// given
		ChangeGiver changeGiver = new ChangeGiver();
		try {
			// when
			changeGiver.giveChange(new Money(0), Collections.emptyList());
			fail();
		} catch (IllegalArgumentException expected) {
			// then
		}
	}

	@Test
	public void should_give_change_using_one_coin() {
		// given
		ChangeGiver changeGiver = new ChangeGiver();
		// when
		List<Coin> change = changeGiver.giveChange(new Money(200), Arrays.asList(Coin.ONE_ZL, Coin.TWO_ZL));
		// then
		assertThat(change, notNullValue());
		assertThat(change, contains(Coin.TWO_ZL));
	}

	@Test
	public void should_throw_exception_if_not_possible_to_give_change() {
		// given
		ChangeGiver changeGiver = new ChangeGiver();
		try {
			// when
			changeGiver.giveChange(new Money(200), Arrays.asList(Coin.ONE_ZL));
			fail();
		} catch (GiveChangeNotPossibleException expected) {
			// then
		}
	}

	@Test
	public void should_give_change_using_multiple_coins() {
		// given
		ChangeGiver changeGiver = new ChangeGiver();
		// when
		List<Coin> change = changeGiver.giveChange(new Money(200), Arrays.asList(Coin.ONE_ZL, Coin.FIVE_ZL, Coin.FIFTY_GR, Coin.ONE_ZL));
		// then
		assertThat(change, notNullValue());
		assertThat(change, contains(Coin.ONE_ZL, Coin.ONE_ZL));
	}
}
