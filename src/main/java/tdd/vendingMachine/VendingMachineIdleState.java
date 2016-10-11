package tdd.vendingMachine;

public class VendingMachineIdleState implements VendingMachineState {

	@Override
	public void selectShelve(int shelveNumber, VendingMachine vendingMachine) {
		if (shelveNumber < 0 || shelveNumber >= vendingMachine.getNumberOfShelves()) {
			vendingMachine.updateDisplay(VendingMachine.INVALID_SHELVE_NUMBER_MSG);
			return;
		}
		Shelve selectedShelve = vendingMachine.getShelve(shelveNumber);
		if (selectedShelve.isEmpty()) {
			vendingMachine.updateDisplay(VendingMachine.EMPTY_SHELVE_MSG);
		} else {
			vendingMachine.setState(new VendingMachineSellingState(selectedShelve, vendingMachine));
		}
	}

	@Override
	public void insertCoin(Coin coin, VendingMachine vendingMachine) {
		vendingMachine.addReturnedCoin(coin);
	}

	@Override
	public void pressCancel(VendingMachine vendingMachine) {
		vendingMachine.updateDisplay("");
	}

}
