package tdd.vendingMachine;

public class Money {

	private final int amountGr;
	
	public Money(int amountGr) {
		if (amountGr < 0) {
			throw new IllegalArgumentException("amountGr must not be < 0");
		}
		this.amountGr = amountGr;
	}
	
	@Override
	public String toString() {
		return String.format("%d.%02d", amountGr / 100, amountGr % 100);
	}
}
