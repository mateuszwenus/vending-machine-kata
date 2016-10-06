package tdd.vendingMachine;

public class Shelve {

	private final Product product;
	private int numberOfProducts;
	
	public Shelve(int numberOfProducts, Product product) {
		if (numberOfProducts < 0) {
			throw new IllegalArgumentException("number of products must not be < 0");
		}
		if (product == null) {
			throw new NullPointerException("product must not be null");
		}
		this.product = product;
		this.numberOfProducts = numberOfProducts;
	}

	public Money getProductPrice() {
		return product.getPrice();
	}

	public Product takeProduct() {
		if (numberOfProducts > 0) {
			numberOfProducts--;
			return product;
		} else {
			return null;
		}
	}

	public int getNumberOfProducts() {
		return numberOfProducts;
	}

	public boolean isEmpty() {
		return numberOfProducts <= 0;
	}
}
