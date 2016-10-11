package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine {

	public static final String EMPTY_SHELVE_MSG = "empty shelve";
	public static final String INVALID_SHELVE_NUMBER_MSG = "invalid shelve number";
	private final List<Shelve> shelves;
	private final List<Coin> returnedCoins = new ArrayList<>();
	private String display = "";
	private Product dispensedProduct;

	private VendingMachineState state = new VendingMachineIdleState();

	public VendingMachine(List<Shelve> shelves) {
		if (shelves == null) {
			throw new NullPointerException("shelves must not be null");
		}
		if (shelves.isEmpty()) {
			throw new IllegalArgumentException("shelves list must not be empty");
		}
		this.shelves = shelves;
	}

	public String getDisplay() {
		return display;
	}

	public void selectShelve(int shelveNumber) {
		state.selectShelve(shelveNumber, this);
	}

	public void insertCoin(Coin coin) {
		state.insertCoin(coin, this);
	}

	public void pressCancel() {
		state.pressCancel(this);
	}

	void setState(VendingMachineState newState) {
		this.state = newState;
	}

	void updateDisplay(String msgToDisplay) {
		this.display = msgToDisplay;
	}

	public List<Coin> getReturnedCoins() {
		return returnedCoins;
	}

	public Product takeDispensedProduct() {
		Product result = dispensedProduct;
		dispensedProduct = null;
		return result;
	}

	public int getNumberOfShelves() {
		return shelves.size();
	}

	Shelve getShelve(int idx) {
		return shelves.get(idx);
	}

	void addReturnedCoin(Coin coinToReturn) {
		returnedCoins.add(coinToReturn);
	}

	void addReturnedCoins(List<Coin> coinsToReturn) {
		returnedCoins.addAll(coinsToReturn);
	}

	void dispenseProduct(Product dispensedProduct) {
		this.dispensedProduct = dispensedProduct;
	}
}
