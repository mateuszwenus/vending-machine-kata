package tdd.vendingMachine;

import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Test;

public class ChangeGiverTest {

	@Test
	public void ChangeGiver_should_throw_exception_when_amount_is_nonpositive() {
		// given
		ChangeGiver changeGiver = new ChangeGiver();
		try {
			// when
			changeGiver.giveChange(0, Collections.emptyList());
			fail();
		} catch (IllegalArgumentException expected) {
			// then
		}
	}
}
