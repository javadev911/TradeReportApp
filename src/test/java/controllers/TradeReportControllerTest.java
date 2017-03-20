package controllers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;
import helpers.CurrencyHelper;
import helpers.ReportHelper;
import helpers.TradeReportServiceHelper;

import java.util.Map;

import models.Report;

import org.apache.commons.collections.MapUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import services.TradeReportService;
import stubs.TradesStub;
import util.DateHelper;

@RunWith(MockitoJUnitRunner.class)
public class TradeReportControllerTest {

	private TradeReportController testClass;
	private TradesStub tradesStub;
	private TradeReportService tradeReportService;
	private DateHelper dateHelper;
	private TradeReportServiceHelper tradeReportServiceHelper;
	private ReportHelper reportHelper;
	private CurrencyHelper currencyHelper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		testClass = mock(TradeReportControllerImpl.class, CALLS_REAL_METHODS);
		tradesStub = mock(TradesStub.class, CALLS_REAL_METHODS);
		tradeReportService = mock(TradeReportService.class, CALLS_REAL_METHODS);
		dateHelper = mock(DateHelper.class, CALLS_REAL_METHODS);
		tradeReportServiceHelper = mock(TradeReportServiceHelper.class, CALLS_REAL_METHODS);
		reportHelper = mock(ReportHelper.class, CALLS_REAL_METHODS);
		currencyHelper = mock(CurrencyHelper.class, CALLS_REAL_METHODS);
		
		setInternalState(testClass, "tradesStub", tradesStub);
		setInternalState(testClass, "tradeReportService", tradeReportService);		
		setInternalState(tradeReportService, "tradeReportServiceHelper", tradeReportServiceHelper);
		setInternalState(tradeReportService, "reportHelper", reportHelper);		
		setInternalState(tradeReportServiceHelper, "dateHelper", dateHelper);
		setInternalState(tradeReportServiceHelper, "currencyHelper", currencyHelper);		
		setInternalState(tradesStub, "dateHelper", dateHelper);		
	}

	@Test
	public void test_reportTrades_returns_ReportMap() {		
		Map<Long, Report> reportMap = testClass.reportTrades();
		assertTrue("Report Map is null", MapUtils.isNotEmpty(reportMap));
		verify(testClass, Mockito.times(1)).reportTrades();
	}

}
