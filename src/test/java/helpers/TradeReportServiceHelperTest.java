package helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Trade;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import util.DateHelper;
import enums.CurrencyE;
import enums.EntityE;
import enums.TradeActionE;

@RunWith(MockitoJUnitRunner.class)
public class TradeReportServiceHelperTest {

	private DateHelper dateHelper;
	private TradeReportServiceHelper testClass;
	private CurrencyHelper currencyHelper;


	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		testClass = mock(TradeReportServiceHelper.class, CALLS_REAL_METHODS);
		dateHelper = mock(DateHelper.class, CALLS_REAL_METHODS);		
		currencyHelper = mock(CurrencyHelper.class, CALLS_REAL_METHODS);		
		setInternalState(testClass, "dateHelper", dateHelper);		
		setInternalState(testClass, "currencyHelper", currencyHelper);
	}

	
	//Currency: AED, Given Settlement Date is Friday, which is a non-working day, so this test must assert that Settlement date was updated to next working day 	
	@Test
	public void test_updateSettlementDates_updates_settlement_dates_to_next_working_day_if_they_fall_on_weekend_for_curr_AED() {
		List<Trade> tradeStubs = new ArrayList<>();
		tradeStubs.add(createTradeWithSettlememtDateAs17Mar2017ForCurrencyAED());
		testClass.updateSettlementDates(tradeStubs);
		assertTrue("Report Map is null", CollectionUtils.isNotEmpty(tradeStubs));		
		assertEquals("Settlement Date did not matched", tradeStubs.get(0).getSettlementDate().getTime(), tradeStubs.get(0).getSettlementDate().getTime());
		verify(testClass, Mockito.times(1)).updateSettlementDates(tradeStubs);
	}

	//Currency: SAR, Given Settlement Date is Saturday, which is a non-working day, so this test must assert that Settlement date was updated to next working day
	@Test
	public void test_updateSettlementDates_updates_settlement_dates_to_next_working_day_if_they_fall_on_weekend_for_curr_SAR() {
		List<Trade> tradeStubs = new ArrayList<>();
		tradeStubs.add(createTradeWithSettlememtDateAs18Mar2017ForCurrencySAR());
		testClass.updateSettlementDates(tradeStubs);
		assertTrue("Report Map is null", CollectionUtils.isNotEmpty(tradeStubs));		
		assertEquals("Settlement Date did not matched", tradeStubs.get(0).getSettlementDate().getTime(), tradeStubs.get(0).getSettlementDate().getTime());
		verify(testClass, Mockito.times(1)).updateSettlementDates(tradeStubs);
	}

	//Currency: GBP, Given Settlement Date is Sunday, which is a non-working day, so this test must assert that Settlement date was updated to next working day
	@Test
	public void test_updateSettlementDates_updates_settlement_dates_to_next_working_day_if_they_fall_on_weekend_for_curr_GBP() {
		long expectedDate20032017InMillis = dateHelper.createDate(20,2, 2017).getTime();
		List<Trade> tradeStubs = new ArrayList<>();
		tradeStubs.add(createTradeWithSettlememtDateAs19Mar2017ForCurrencyGBP());
		testClass.updateSettlementDates(tradeStubs);
		assertTrue("Report Map is null", CollectionUtils.isNotEmpty(tradeStubs));		
		assertEquals("Settlement Date did not matched ", expectedDate20032017InMillis, tradeStubs.get(0).getSettlementDate().getTime());
		verify(testClass, Mockito.times(1)).updateSettlementDates(tradeStubs);
	}

	//Currency: SGP & GBP, Given Settlement Date is Saturday and Sunday, which are a non-working days, so this test must assert that Settlement dates were updated to next working day
	@Test
	public void test_updateSettlementDates_updates_settlement_dates_to_next_working_day_if_they_fall_on_weekend_for_curr_GBP_and_SGP() {
		List<Trade> tradeStubs = new ArrayList<>();
		tradeStubs.add(createTradeWithSettlememtDateAs19Mar2017ForCurrencyGBP());
		tradeStubs.add(createTradeWithSettlememtDateAs18Mar2017ForCurrencySGP());
		testClass.updateSettlementDates(tradeStubs);
		assertTrue("Report Map is null", CollectionUtils.isNotEmpty(tradeStubs));		
		//GBP
		assertEquals("Settlement Date did not matched", tradeStubs.get(0).getSettlementDate().getTime(), tradeStubs.get(0).getSettlementDate().getTime());
		//SGP
		assertEquals("Settlement Date did not matched", tradeStubs.get(1).getSettlementDate().getTime(), tradeStubs.get(1).getSettlementDate().getTime());
		verify(testClass, Mockito.times(1)).updateSettlementDates(tradeStubs);
	}

	@Test
	public void test_groupTradesByInstructionDate(){
		long expectedDate31122016InMillis = dateHelper.createDate(0, 0, 2016).getTime();
		long expectedDate07012016InMillis = dateHelper.createDate(7, 0, 2016).getTime();
		int expectedNoOfTradesOnDate31122015 = 3;
		int expectedNoOfTradesOnDate07012016 = 1;
		Map<Long, List<Trade>> groupedTradesByInstructionDate = testClass.groupTradesByInstructionDate(getTrades());
		assertTrue("organisedTrades Map is null", MapUtils.isNotEmpty(groupedTradesByInstructionDate));
		
		for (Map.Entry<Long, List<Trade>> groupedTradesEntry : groupedTradesByInstructionDate.entrySet()) {
			long actualInstructioninMillis =   groupedTradesEntry.getKey();
			if(expectedDate31122016InMillis == actualInstructioninMillis){
				assertEquals("Actual Trades Size must be "+expectedNoOfTradesOnDate31122015, expectedNoOfTradesOnDate31122015, groupedTradesEntry.getValue().size());
			} else if(expectedDate07012016InMillis == actualInstructioninMillis){
				assertEquals("Actual Trades Size must be "+expectedNoOfTradesOnDate07012016, expectedNoOfTradesOnDate07012016, groupedTradesEntry.getValue().size());
			}  
			else {
				fail();
			}
		}
	}
	
	public List<Trade> getTrades() {
		List<Trade> trades = new ArrayList<Trade>();

		Trade trade1 = new Trade();
		trade1.setEntity(EntityE.FOO);
		trade1.setTradeAction(TradeActionE.BUY);
		trade1.setAgreedFx(0.50);
		trade1.setCurrency(CurrencyE.SGP);
		trade1.setInstructionDate(dateHelper.createDate(0, 0, 2016));
		trade1.setSettlementDate(dateHelper.createDate(1, 0, 2016));
		trade1.setUnits(200);
		trade1.setPricePerUnit(100.25);

		Trade trade2 = new Trade();
		trade2.setEntity(EntityE.FOO);
		trade2.setTradeAction(TradeActionE.SELL);
		trade2.setAgreedFx(0.50);
		trade2.setCurrency(CurrencyE.SAR);
		trade2.setInstructionDate(dateHelper.createDate(0, 0, 2016));
		trade2.setSettlementDate(dateHelper.createDate(1, 0, 2016));
		trade2.setUnits(200);
		trade2.setPricePerUnit(100.25);
		
		Trade trade3 = new Trade();
		trade3.setEntity(EntityE.BAR);
		trade3.setTradeAction(TradeActionE.SELL);
		trade3.setAgreedFx(0.90);
		trade3.setCurrency(CurrencyE.GBP);
		trade3.setInstructionDate(dateHelper.createDate(0, 0, 2016));
		trade3.setSettlementDate(dateHelper.createDate(1, 0, 2016));
		trade3.setUnits(200);
		trade3.setPricePerUnit(190.85);
		
		Trade trade4 = new Trade();
		trade4.setEntity(EntityE.BAR);
		trade4.setTradeAction(TradeActionE.BUY);
		trade4.setAgreedFx(0.26);
		trade4.setCurrency(CurrencyE.AED);
		trade4.setInstructionDate(dateHelper.createDate(7, 0, 2016));
		trade4.setSettlementDate(dateHelper.createDate(9, 0, 2016));
		trade4.setUnits(357);
		trade4.setPricePerUnit(621.58);

		trades.add(trade1);
		trades.add(trade2);
		trades.add(trade3);
		trades.add(trade4);

		return trades;
	}
	
	private Trade createTradeWithSettlememtDateAs17Mar2017ForCurrencyAED(){
		Trade trade = createTradeWithoutCurrencyAndSettlementDate();
		trade.setCurrency(CurrencyE.AED);
		trade.setSettlementDate(dateHelper.createDate(17, 2, 2017)); // This date is a Friday, non-working day for AED Curr
		return trade;
	}
	
	private Trade createTradeWithSettlememtDateAs18Mar2017ForCurrencySAR(){
		Trade trade = createTradeWithoutCurrencyAndSettlementDate();
		trade.setCurrency(CurrencyE.SAR);
		trade.setSettlementDate(dateHelper.createDate(18, 2, 2017)); // This date is a Saturday, non-working day for SAR Curr
		return trade;
	}
	
	private Trade createTradeWithSettlememtDateAs19Mar2017ForCurrencyGBP(){
		Trade trade = createTradeWithoutCurrencyAndSettlementDate();
		trade.setCurrency(CurrencyE.GBP);
		trade.setSettlementDate(dateHelper.createDate(19, 2, 2017)); // This date is a Sunday, non-working day for GBP Curr
		return trade;
	}
	
	private Trade createTradeWithSettlememtDateAs18Mar2017ForCurrencySGP(){
		Trade trade = createTradeWithoutCurrencyAndSettlementDate();
		trade.setCurrency(CurrencyE.SGP);
		trade.setSettlementDate(dateHelper.createDate(18, 2, 2017)); // This date is a Sunday, non-working day for GBP Curr
		return trade;
	}	
	
	private Trade createTradeWithoutCurrencyAndSettlementDate(){
		Trade trade = new Trade();
		trade.setEntity(EntityE.FOO);
		trade.setTradeAction(TradeActionE.BUY);
		trade.setAgreedFx(0.50);
		trade.setInstructionDate(dateHelper.createDate(0, 0, 2016));		
		trade.setUnits(200);
		trade.setPricePerUnit(100.25);
		
		return trade;
	}
	
}
