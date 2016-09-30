package tdd.vendingMachine;

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
}
