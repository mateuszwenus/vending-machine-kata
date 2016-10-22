package tdd.vendingMachine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChangeGiver {

	public List<Coin> giveChange(Money amountToGive, List<Coin> availableCoins) {
		if (amountToGive.getAmountGr() <= 0) {
			throw new IllegalArgumentException();
		}
		for (Coin coin : availableCoins) {
			if (coin.toMoney().equals(amountToGive)) {
				return Arrays.asList(coin);
			}
		}
		return Collections.emptyList();
	}
}
