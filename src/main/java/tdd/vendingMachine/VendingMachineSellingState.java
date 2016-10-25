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
		Money coinValue = coin.toMoney();
		if (coinValue.lessThan(amountToPay)) {
			amountToPay = amountToPay.minus(coinValue);
		} else {
			try {
				List<Coin> change = new ArrayList<>();
				if (amountToPay.lessThan(coinValue)) {
					Money amountToGive = coinValue.minus(amountToPay);
					ChangeGiver changeGiver = new ChangeGiver();
					change.addAll(changeGiver.giveChange(amountToGive, vendingMachine.getCoinsForChangeGiving()));
				}
				vendingMachine.addCoinsForChangeGiving(insertedCoins);
				vendingMachine.addReturnedCoins(change);
				vendingMachine.dispenseProduct(selectedShelve.takeProduct());
				transitionToIdleState(vendingMachine);
			} catch (GiveChangeNotPossibleException e) {
				transitionToIdleState(vendingMachine);
				vendingMachine.updateDisplay("unable to give change");
				return;
			}
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
