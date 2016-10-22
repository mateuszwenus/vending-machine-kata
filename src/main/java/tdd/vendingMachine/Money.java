package tdd.vendingMachine;

public class Money {

	private final int amountGr;
	
	public Money(int amountGr) {
		if (amountGr < 0) {
			throw new IllegalArgumentException("amountGr must not be < 0");
		}
		this.amountGr = amountGr;
	}
	
	public int getAmountGr() {
		return amountGr;
	}
	
	public Money minus(Money other) {
		return new Money(amountGr - other.amountGr);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountGr;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (amountGr != other.amountGr)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%d.%02d", amountGr / 100, amountGr % 100);
	}
}
