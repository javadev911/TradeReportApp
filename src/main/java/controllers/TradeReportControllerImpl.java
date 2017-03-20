package controllers;

import java.text.DateFormat;
import java.util.Map;

import javax.inject.Inject;

import models.Report;
import services.TradeReportService;
import stubs.TradesStub;

public class TradeReportControllerImpl implements TradeReportController {

	@Inject
	private TradesStub tradesStub;
	@Inject
	private TradeReportService tradeReportService;

	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * (non-Javadoc)
	 * 
	 * @see controllers.TradeReportController#reportTrades() This method calls
	 * service.reportTrades to return a map of report. Each Report contains
	 * usdIncoming, usdOutgoing, List of HighToLow ranked Buy and Sell Entities
	 * grouped by Instruction Date
	 */
	@Override
	public Map<Long, Report> reportTrades() {
		Map<Long, Report> reports = tradeReportService.reportTrades(tradesStub
				.getTrades());
		printReport(reports);
		return reports;
	}

	private void printReport(Map<Long, Report> reports) {
		for (Map.Entry<Long, Report> reportEntry : reports.entrySet()) {
			System.out.println("####### Trade Report for day: "
					+ millisToDate(reportEntry.getKey()) + " ########");
			System.out.println("UsdIncoming: "
					+ reportEntry.getValue().getUsdIncoming());
			System.out.println("UsdOutgoing: "
					+ reportEntry.getValue().getUsdOutgoing());
			System.out.println("Ranking BuyEntities [High to Low]: ");
			reportEntry.getValue().getRankedBuyTrades().stream()
					.map(e -> e.getEntity().getValue())
					.forEach(System.out::println);
			System.out.println("Ranking SellEntities [High to Low]: ");
			reportEntry.getValue().getRankedSellTrades().stream()
					.map(e -> e.getEntity().getValue())
					.forEach(System.out::println);
			System.out
					.println("###################### END OF REPORT ###################### \n");
		}
	}

	private String millisToDate(long millis) {
		return DateFormat.getDateInstance(DateFormat.SHORT).format(millis);
	}

}
