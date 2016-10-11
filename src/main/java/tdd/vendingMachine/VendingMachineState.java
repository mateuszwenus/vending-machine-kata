package tdd.vendingMachine;

public interface VendingMachineState {

	void selectShelve(int shelveNumber, VendingMachine vendingMachine);

	void insertCoin(Coin coin, VendingMachine vendingMachine);

	void pressCancel(VendingMachine vendingMachine);

}
