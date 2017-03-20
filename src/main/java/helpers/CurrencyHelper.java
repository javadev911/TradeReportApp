package helpers;

import enums.CurrencyE;

public class CurrencyHelper {

	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * This method determines if currency type is AED or SAR, and if so returns true else false.
	 * 
	 */
	public boolean isAEDOrSAR(CurrencyE tradeCurrency) {
		return (CurrencyE.AED == tradeCurrency || CurrencyE.SAR == tradeCurrency);
	}
}
