package tdd.vendingMachine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangeGiver {

	public List<Coin> giveChange(int amountToGive, List<Coin> availableCoins) {
		if (amountToGive <= 0) {
			throw new IllegalArgumentException();
		}
		for (Coin coin : availableCoins) {
			if (coin.toMoney().equals(new Money(amountToGive))) {
				return Arrays.asList(coin);
			}
		}
		return Collections.emptyList();
	}
}
