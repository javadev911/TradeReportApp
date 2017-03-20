package helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.Report;
import models.Trade;
import enums.TradeActionE;

public class ReportHelper {

	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * This method creates/generates Report with given List of Trades grouped by InstructionDate.
	 * 
	 */
	public Map<Long, Report> generateReport(Map<Long, List<Trade>> tradeMap) {
		final double DELTA = 0.0;
		Map<Long, Report> reportMap = new HashMap<>();
		Report report = null;
		for (Map.Entry<Long, List<Trade>> tradesEntry : tradeMap.entrySet()) {
			report = new Report();
			double usdIncoming = 0.0;
			double usdOutgoing = 0.0;

			for (Trade trade : tradesEntry.getValue()) {
				if (TradeActionE.BUY == trade.getTradeAction()) {
					usdOutgoing += trade.getUSDAmountOfTrade();
				} else {
					usdIncoming += trade.getUSDAmountOfTrade();

				}
			}

			report.setUsdIncoming(usdIncoming);
			report.setUsdOutgoing(usdOutgoing);
			
			List<Trade> buyTrades = tradesEntry.getValue().stream().filter(map -> TradeActionE.BUY == map.getTradeAction()).collect(Collectors.toList());
			buyTrades.sort((t1, t2) -> Double.compare(t2.getUSDAmountOfTrade() - t1.getUSDAmountOfTrade(), DELTA));
			
			List<Trade> sellTrades = tradesEntry.getValue().stream().filter(map -> TradeActionE.SELL == map.getTradeAction()).collect(Collectors.toList());
			sellTrades.sort((t1, t2) -> Double.compare(t2.getUSDAmountOfTrade() - t1.getUSDAmountOfTrade(), DELTA));

			report.setRankedBuyTrades(buyTrades);
			report.setRankedSellTrades(sellTrades);

			reportMap.put(tradesEntry.getKey(), report);
		}

		return reportMap;
	}

}