package tdd.vendingMachine;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class VendingMachineTest {

    @Test
    public void just_a_stupid_passing_test_to_ensure_that_tests_are_run() {
        Assertions.assertThat(new VendingMachine()).isNotNull();
    }
    
    @Test
	public void VendingMachine_has_shelves() {
		// when
    	new VendingMachine(Arrays.asList(new Shelve(1, new Product("Mineral water", new Money(100)))));
	}
}
