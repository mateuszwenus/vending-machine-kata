package tdd.vendingMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChangeGiver {

	public List<Coin> giveChange(Money amountToGive, List<Coin> availableCoins) {
		if (amountToGive.getAmountGr() <= 0) {
			throw new IllegalArgumentException();
		}
		for (Coin coin : availableCoins) {
			if (coin.toMoney().equals(amountToGive)) {
				return new ArrayList<>(Arrays.asList(coin));
			} else if (coin.toMoney().isLessThan(amountToGive)) {
				Money newAmountToGive = amountToGive.minus(coin.toMoney());
				List<Coin> newAvailableCoins = new ArrayList<>(availableCoins);
				newAvailableCoins.remove(coin);
				try {
					List<Coin> change = giveChange(newAmountToGive, newAvailableCoins);
					change.add(coin);
					return change;
				} catch (GiveChangeNotPossibleException ignored) {
				}
			}
		}
		throw new GiveChangeNotPossibleException();
	}
}
