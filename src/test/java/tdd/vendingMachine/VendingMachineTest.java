package tdd.vendingMachine;

import java.util.Arrays;

import org.junit.Test;

public class VendingMachineTest {

    @Test
	public void VendingMachine_has_shelves() {
		// when
    	new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(100)))));
	}
}
