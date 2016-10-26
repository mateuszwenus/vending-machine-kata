package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineSellingState implements VendingMachineState {

	private final Shelve selectedShelve;
	private final Money amountToPay;
	private Money insertedAmount = Money.ZERO;
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
		insertedAmount = insertedAmount.add(coin.toMoney());
		if (coveredProductPrice()) {
			dispenseProductAndTryToGiveChangeIfNecessary(vendingMachine);
		} else {
			updateDisplay(vendingMachine);
		}
	}

	private boolean coveredProductPrice() {
		return insertedAmount.compareTo(amountToPay) >= 0;
	}

	private void dispenseProductAndTryToGiveChangeIfNecessary(VendingMachine vendingMachine) {
		try {
			dispenseProductAndGiveChangeIfNecessary(vendingMachine);
		} catch (GiveChangeNotPossibleException e) {
			handleChangeNotPossible(vendingMachine);
		}
	}

	private void dispenseProductAndGiveChangeIfNecessary(VendingMachine vendingMachine) {
		Money changeAmount = calculateChange();
		if (isGiveChangeNecessary(changeAmount)) {
			ChangeGiver changeGiver = new ChangeGiver();
			List<Coin> changeCoins = changeGiver.giveChange(changeAmount, vendingMachine.getCoinsForChangeGiving());
			vendingMachine.addReturnedCoins(changeCoins);
			vendingMachine.removeCoinsForChangeGiving(changeCoins);
		}
		vendingMachine.addCoinsForChangeGiving(insertedCoins);
		vendingMachine.dispenseProduct(selectedShelve.takeProduct());
		transitionToIdleState(vendingMachine);
	}

	private Money calculateChange() {
		return insertedAmount.subtract(amountToPay);
	}

	private boolean isGiveChangeNecessary(Money changeAmount) {
		return changeAmount.compareTo(Money.ZERO) > 0;
	}

	private void handleChangeNotPossible(VendingMachine vendingMachine) {
		transitionToIdleState(vendingMachine);
		vendingMachine.updateDisplay(VendingMachine.UNABLE_TO_GIVE_CHANGE_MSG);
		vendingMachine.addReturnedCoins(insertedCoins);
	}

	private void updateDisplay(VendingMachine vendingMachine) {
		Money amountToDisplay = amountToPay.subtract(insertedAmount);
		vendingMachine.updateDisplay(amountToDisplay.toString());
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
