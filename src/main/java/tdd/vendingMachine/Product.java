package tdd.vendingMachine;

public class Product {

	private final String name;
	private final Money price;

	public Product(String name, Money price) {
		if (name == null) {
			throw new NullPointerException("name must not be null");
		}
		if (name.matches("\\s*")) {
			throw new IllegalArgumentException("name must not be blank");
		}
		if (price == null) {
			throw new NullPointerException("price must not be null");
		}
		this.name = name;
		this.price = price;
	}
	
	public Money getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}
