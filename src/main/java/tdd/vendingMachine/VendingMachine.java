package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

	public static final String INVALID_SHELVE_NUMBER_MSG = "invalid shelve number";
	private final List<Shelve> shelves;
	private final List<Coin> returnedCoins = new ArrayList<>();
	private final List<Coin> insertedCoins = new ArrayList<>();
	private String display = "";
	private Shelve selectedShelve;
	private Money amountToPay;
	private Product dispensedProduct;
	
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
		if (selectedShelve != null) {
			insertedCoins.add(coin);
			amountToPay = amountToPay.minus(coin.toMoney());
			if (amountToPay.equals(new Money(0))) {
				dispensedProduct = selectedShelve.takeProduct();
				insertedCoins.clear();
			}
			display = amountToPay.toString();
		} else {
			returnedCoins.add(coin);
		}
	}

	public List<Coin> getReturnedCoins() {
		return returnedCoins;
	}

	public Product takeDispensedProduct() {
		Product result = dispensedProduct;
		dispensedProduct = null;
		return result;
	}

	public int getNumberOfProductsOnShelve(int shelveNumber) {
		return shelves.get(shelveNumber).getNumberOfProducts();
	}

	public void pressCancel() {
		returnedCoins.addAll(insertedCoins);
		insertedCoins.clear();
	}
}
