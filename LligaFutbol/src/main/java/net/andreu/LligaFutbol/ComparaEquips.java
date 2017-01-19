package net.andreu.LligaFutbol;

import java.util.Comparator;

public class ComparaEquips implements Comparator<Equip> {
	@Override
	public int compare(Equip e1, Equip e2) {
		int r = 0;
		if (e1.calculaPunts() > e2.calculaPunts()) {
			r = -1;
		} else if (e1.calculaPunts() < e2.calculaPunts()) {
			r = 1;
		}
		return r;
	}
}