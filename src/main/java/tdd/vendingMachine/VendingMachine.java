package tdd.vendingMachine;

import java.util.List;

public class VendingMachine {

	public VendingMachine(List<Shelve> shelves) {
		if (shelves == null) {
			throw new NullPointerException("shelves must not be null");
		}
	}

}
