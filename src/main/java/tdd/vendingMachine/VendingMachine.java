package tdd.vendingMachine;

import java.util.List;

public class VendingMachine {

	public static final String INVALID_SHELVE_NUMBER_MSG = "invalid shelve number";
	private final List<Shelve> shelves;
	private String display;
	
	public VendingMachine(List<Shelve> shelves) {
		if (shelves == null) {
			throw new NullPointerException("shelves must not be null");
		}
		if (shelves.isEmpty()) {
			throw new IllegalArgumentException("shelves list must not be empty");
		}
		this.shelves = shelves;
	}

	public void selectShelve(int shelveNumber) {
		if (shelveNumber >= shelves.size()) {
			display = INVALID_SHELVE_NUMBER_MSG;
		} else {
			display = shelves.get(shelveNumber).getProductPrice().toString();
		}
	}

	public String getDisplay() {
		return display;
	}
}
