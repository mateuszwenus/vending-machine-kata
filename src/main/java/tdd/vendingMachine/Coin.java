package tdd.vendingMachine;

public enum Coin {

	FIVE_ZL(new Money(500)), TWO_ZL(new Money(200)), ONE_ZL(new Money(100)), 
	FIFTY_GR(new Money(50)), TWENTY_GR(new Money(20)), TEN_GR(new Money(10));
	
	private final Money value;
	
	private Coin(Money money) {
		this.value = money;
	}
	
	public Money toMoney() {
		return value;
	}
}
