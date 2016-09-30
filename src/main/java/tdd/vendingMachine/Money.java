package tdd.vendingMachine;

public class Money {

	public Money(int amountGr) {
		if (amountGr < 0) {
			throw new IllegalArgumentException("amountGr must not be < 0");
		}
	}
}
