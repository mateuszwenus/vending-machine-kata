package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineSellingState implements VendingMachineState {

	private final Shelve selectedShelve;
	private Money amountToPay;
	private final List<Coin> insertedCoins = new ArrayList<>();

	public VendingMachineSellingState(Shelve selectedShelve, VendingMachine vendingMachine) {
		this.selectedShelve = selectedShelve;
		this.amountToPay = selectedShelve.getProductPrice();
		updateDisplay(vendingMachine);
	}

	@Override
	public void selectShelve(int shelveNumber, VendingMachine vendingMachine) {
	}

	@Override
	public void insertCoin(Coin coin, VendingMachine vendingMachine) {
		insertedCoins.add(coin);
		amountToPay = amountToPay.minus(coin.toMoney());
		if (amountToPay.equals(new Money(0))) {
			vendingMachine.dispenseProduct(selectedShelve.takeProduct());
			transitionToIdleState(vendingMachine);
		}
		updateDisplay(vendingMachine);
	}

	private void updateDisplay(VendingMachine vendingMachine) {
		vendingMachine.updateDisplay(amountToPay.toString());
	}

	@Override
	public void pressCancel(VendingMachine vendingMachine) {
		vendingMachine.addReturnedCoins(insertedCoins);
		transitionToIdleState(vendingMachine);
	}
	
	private void transitionToIdleState(VendingMachine vendingMachine) {
		vendingMachine.setState(new VendingMachineIdleState());
		vendingMachine.updateDisplay("");
	}
}
