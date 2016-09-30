package tdd.vendingMachine;

import static org.junit.Assert.fail;

import org.junit.Test;

public class MoneyTest {

	@Test
	public void Money_should_throw_exception_when_amount_is_negative() {
		try {
			// when
			new Money(-1);
			fail();
		} catch (IllegalArgumentException expected) {
			// then
		}
	}
}
