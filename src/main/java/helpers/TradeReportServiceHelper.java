package helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import models.Trade;
import util.DateHelper;

public class TradeReportServiceHelper {
	
	@Inject
	private DateHelper dateHelper;
	@Inject
	private CurrencyHelper currencyHelper;
	
	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * This method updates settlement dates for given trades based on their currency and checks 
	 * if those settlement date has fallen on a weekend, if so this update settlement date to next working day.
	 * 
	 */
	public void updateSettlementDates(List<Trade> trades) {
		for (Trade trade : trades) {
			if (currencyHelper.isAEDOrSAR(trade.getCurrency())) {
				if (!isAEDOrSARNormalWeekday(trade.getSettlementDate())) {
					updateSettlementDayToNextWorkingDayForAEDOrSAR(trade);
				}
			} else {
				if (!isResOfWorldNormalWeekday(trade.getSettlementDate())) {
					updateSettlementDayToNextWorkingDayForOtherCurrencies(trade);
				}
			}
		}
	}
	
	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * This method groups given trades by InstructionDate and returns a Map.
	 * 
	 */
	public Map<Long, List<Trade>> groupTradesByInstructionDate(List<Trade> trades) {
		Map<Long, List<Trade>> tradeMap = new HashMap<>();

		for (Trade trade : trades) {

			long instructionDateInMillis = trade.getInstructionDate().getTime();

			if (!tradeMap.containsKey(instructionDateInMillis)) {
				tradeMap.put(instructionDateInMillis, new ArrayList<Trade>());
			}
			
			List<Trade> foundTradesForTheDay = tradeMap.get(instructionDateInMillis);
			foundTradesForTheDay.add(trade);			

		}
		return tradeMap;
	}
		
	private boolean isAEDOrSARNormalWeekday(Date settlementDate) {
		int dayOfWeek = dateHelper.getDayOfWeek(settlementDate);
		return ((dayOfWeek >= Calendar.SUNDAY) && (dayOfWeek <= Calendar.THURSDAY));
	}
	
	private boolean isResOfWorldNormalWeekday(Date settlementDate) {
		int dayOfWeek = dateHelper.getDayOfWeek(settlementDate);
		return ((dayOfWeek >= Calendar.MONDAY) && (dayOfWeek <= Calendar.FRIDAY));
	}
	
	private void updateSettlementDayToNextWorkingDayForAEDOrSAR(Trade trade) {
		int dayOfWeek = dateHelper.getDayOfWeek(trade.getSettlementDate());
		int daysToAdd = (dayOfWeek == Calendar.FRIDAY ? 2 : 1);
		updateSettlementDate(trade, daysToAdd);
	}

	private void updateSettlementDayToNextWorkingDayForOtherCurrencies(Trade trade) {
		int dayOfWeek = dateHelper.getDayOfWeek(trade.getSettlementDate());
		int daysToAdd = (dayOfWeek == Calendar.SATURDAY ? 2 : 1);
		updateSettlementDate(trade, daysToAdd);
	}

	private void updateSettlementDate(Trade trade, int daysToAdd) {
		Calendar c = Calendar.getInstance();
		c.setTime(trade.getSettlementDate());
		c.add(Calendar.DATE, daysToAdd);
		trade.setSettlementDate(c.getTime());
	}


}
