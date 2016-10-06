package tdd.vendingMachine;

import java.util.List;

public class VendingMachine {

	public static final String INVALID_SHELVE_NUMBER_MSG = "invalid shelve number";
	private final List<Shelve> shelves;
	private String display;
	private Shelve selectedShelve;
	private Money amountToPay;
	
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
		if (shelveNumber < 0 || shelveNumber >= shelves.size()) {
			display = INVALID_SHELVE_NUMBER_MSG;
		} else {
			selectedShelve = shelves.get(shelveNumber);
			amountToPay = selectedShelve.getProductPrice();
			display = amountToPay.toString();
		}
	}

	public String getDisplay() {
		return display;
	}

	public void insertCoin(Coin coin) {
		amountToPay = amountToPay.minus(coin.toMoney());
		display = amountToPay.toString();
	}
}
