package tdd.vendingMachine;

import java.util.List;

public class VendingMachine {

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
			display = "invalid shelve number";
		} else {
			display = shelves.get(shelveNumber).getProductPrice().toString();
		}
	}

	public String getDisplay() {
		return display;
	}
}
