package services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;
import helpers.CurrencyHelper;
import helpers.ReportHelper;
import helpers.TradeReportServiceHelper;

import java.util.List;
import java.util.Map;

import models.Report;
import models.Trade;

import org.apache.commons.collections.MapUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import stubs.TradesStub;
import util.DateHelper;
import enums.EntityE;

@RunWith(MockitoJUnitRunner.class)
public class TradeReportServiceTest {

	private TradeReportService testClass;
	private TradesStub tradesStub;
	private DateHelper dateHelper;
	private TradeReportServiceHelper tradeReportServiceHelper;
	private ReportHelper reportHelper;
	private CurrencyHelper currencyHelper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		tradesStub = mock(TradesStub.class, CALLS_REAL_METHODS);
		testClass = mock(TradeReportService.class, CALLS_REAL_METHODS);
		dateHelper = mock(DateHelper.class, CALLS_REAL_METHODS);
		tradeReportServiceHelper = mock(TradeReportServiceHelper.class, CALLS_REAL_METHODS);
		reportHelper = mock(ReportHelper.class, CALLS_REAL_METHODS);
		currencyHelper = mock(CurrencyHelper.class, CALLS_REAL_METHODS);

		setInternalState(testClass, "tradeReportServiceHelper", tradeReportServiceHelper);
		setInternalState(testClass, "reportHelper", reportHelper);
		setInternalState(tradeReportServiceHelper, "dateHelper", dateHelper);
		setInternalState(tradeReportServiceHelper, "currencyHelper", currencyHelper);
		setInternalState(tradesStub, "dateHelper", dateHelper);
	}

	@Test
	public void test_reportTrades_returns_ReportMap() {
		long expectedDate08012016InMillis = dateHelper.createDate(8, 0, 2016).getTime();
		long expectedDate07012016InMillis = dateHelper.createDate(7, 0, 2016).getTime();
		long expectedDate31122016InMillis = dateHelper.createDate(0, 0, 2016).getTime();
		long expectedDate04012016InMillis = dateHelper.createDate(4, 0, 2016).getTime();
		double DELTA = 0.0;
		List<Trade> trades = tradesStub.getTrades();
		Map<Long, Report> reportMap = testClass.reportTrades(trades);
		assertTrue("Report Map is null", MapUtils.isNotEmpty(reportMap));

		for(Map.Entry<Long, Report> reportMapEntry : reportMap.entrySet()){			
			long actualInstallationDate = reportMapEntry.getKey();			
			if(actualInstallationDate == expectedDate08012016InMillis){
				assertReportForInstructionDate08012016(DELTA, reportMapEntry.getValue());
			} else if(actualInstallationDate == expectedDate07012016InMillis){
				assertReportForInstructionDate07012016(DELTA, reportMapEntry.getValue());
			} else if(actualInstallationDate == expectedDate04012016InMillis){
				assertReportForInstructionDate04012016(DELTA, reportMapEntry.getValue());
			} else if(actualInstallationDate == expectedDate31122016InMillis){
				assertReportForInstructionDate31122016(DELTA, reportMapEntry.getValue());
			}
			else {
				fail();
			}
		}
		
		verify(testClass, Mockito.times(1)).reportTrades(trades);
	}

	private void assertReportForInstructionDate31122016(double DELTA, Report report) {
		assertEquals("Incoming USD value did not match", 44378.0, report.getUsdIncoming(), DELTA);
		assertEquals("Outgoing USD value did not match", 10025.0, report.getUsdOutgoing(), DELTA);
		assertEquals("RankedBuyTrades size did not match", 1, report.getRankedBuyTrades().size());
		assertEquals("RankedSellTrades size did not match", 2, report.getRankedSellTrades().size());
		assertTrue("Entities did not match", report.getRankedBuyTrades().get(0).getEntity() == EntityE.FOO);
		assertTrue("Entities did not match", report.getRankedSellTrades().get(0).getEntity() == EntityE.BAR);
		assertTrue("Entities did not match", report.getRankedSellTrades().get(1).getEntity() == EntityE.FOO);
	}


	private void assertReportForInstructionDate04012016(double DELTA, Report report) {
		assertEquals("Incoming USD value did not match", 14899.5, report.getUsdIncoming(), DELTA);
		assertEquals("Outgoing USD value did not match", 0.0, report.getUsdOutgoing(), DELTA);
		assertEquals("RankedBuyTrades size did not match", 0, report.getRankedBuyTrades().size());
		assertEquals("RankedSellTrades size did not match", 1, report.getRankedSellTrades().size());				
		assertTrue("Entities did not match", report.getRankedSellTrades().get(0).getEntity() == EntityE.BAR);
	}


	private void assertReportForInstructionDate07012016(double DELTA, Report report) {
		assertEquals("Incoming USD value did not match", 0.0, report.getUsdIncoming(), DELTA);
		assertEquals("Outgoing USD value did not match", 57695.05560000001, report.getUsdOutgoing(), DELTA);
		assertEquals("RankedBuyTrades size did not match", 1, report.getRankedBuyTrades().size());
		assertEquals("RankedSellTrades size did not match", 0, report.getRankedSellTrades().size());
		assertTrue("Entities did not match", report.getRankedBuyTrades().get(0).getEntity() == EntityE.BAR);
	}


	private void assertReportForInstructionDate08012016(double DELTA, Report report) {
		assertEquals("Incoming USD value did not match", 31956.753, report.getUsdIncoming(), DELTA);
		assertEquals("Outgoing USD value did not match", 0.0, report.getUsdOutgoing(), DELTA);
		assertEquals("RankedBuyTrades size did not match", 0, report.getRankedBuyTrades().size());
		assertEquals("RankedSellTrades size did not match", 1, report.getRankedSellTrades().size());
		assertTrue("Entities did not match", report.getRankedSellTrades().get(0).getEntity() == EntityE.FOO);
	}

}
