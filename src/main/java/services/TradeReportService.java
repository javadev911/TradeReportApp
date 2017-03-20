package services;

import helpers.ReportHelper;
import helpers.TradeReportServiceHelper;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import models.Report;
import models.Trade;

@Singleton
public class TradeReportService {

	@Inject
	private TradeReportServiceHelper tradeReportServiceHelper;
	@Inject
	private ReportHelper reportHelper;

	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * This method calls several methods on other helper classes to execute the
	 * request to generate report as given in the problem statement.
	 */
	public Map<Long, Report> reportTrades(List<Trade> trades) {
		tradeReportServiceHelper.updateSettlementDates(trades);
		return reportHelper.generateReport(tradeReportServiceHelper
				.groupTradesByInstructionDate(trades));
	}
}
