package controllers;

import java.util.Map;

import models.Report;

public interface TradeReportController {
	
	Map<Long, Report> reportTrades();

}
