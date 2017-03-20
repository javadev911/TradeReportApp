package stubs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import models.Trade;
import util.DateHelper;
import enums.CurrencyE;
import enums.EntityE;
import enums.TradeActionE;

public class TradesStub {
	
	@Inject
	private DateHelper dateHelper;

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
		trade2.setEntity(EntityE.BAR);
		trade2.setTradeAction(TradeActionE.SELL);
		trade2.setAgreedFx(0.22);
		trade2.setCurrency(CurrencyE.AED);
		trade2.setInstructionDate(dateHelper.createDate(4, 0, 2016));
		trade2.setSettlementDate(dateHelper.createDate(6, 0, 2016));
		trade2.setUnits(450);
		trade2.setPricePerUnit(150.5);

		Trade trade3 = new Trade();
		trade3.setEntity(EntityE.FOO);
		trade3.setTradeAction(TradeActionE.SELL);
		trade3.setAgreedFx(1.23);
		trade3.setCurrency(CurrencyE.GBP);
		trade3.setInstructionDate(dateHelper.createDate(8, 0, 2016));
		trade3.setSettlementDate(dateHelper.createDate(10, 0, 2016));
		trade3.setUnits(289);
		trade3.setPricePerUnit(89.9);

		Trade trade4 = new Trade();
		trade4.setEntity(EntityE.BAR);
		trade4.setTradeAction(TradeActionE.BUY);
		trade4.setAgreedFx(0.26);
		trade4.setCurrency(CurrencyE.SAR);
		trade4.setInstructionDate(dateHelper.createDate(7, 0, 2016));
		trade4.setSettlementDate(dateHelper.createDate(9, 0, 2016));
		trade4.setUnits(357);
		trade4.setPricePerUnit(621.58);
		
		Trade trade5 = new Trade();
		trade5.setEntity(EntityE.FOO);
		trade5.setTradeAction(TradeActionE.SELL);
		trade5.setAgreedFx(0.50);
		trade5.setCurrency(CurrencyE.SGP);
		trade5.setInstructionDate(dateHelper.createDate(0, 0, 2016));
		trade5.setSettlementDate(dateHelper.createDate(1, 0, 2016));
		trade5.setUnits(200);
		trade5.setPricePerUnit(100.25);
		
		
		Trade trade6 = new Trade();
		trade6.setEntity(EntityE.BAR);
		trade6.setTradeAction(TradeActionE.SELL);
		trade6.setAgreedFx(0.90);
		trade6.setCurrency(CurrencyE.SGP);
		trade6.setInstructionDate(dateHelper.createDate(0, 0, 2016));
		trade6.setSettlementDate(dateHelper.createDate(1, 0, 2016));
		trade6.setUnits(200);
		trade6.setPricePerUnit(190.85);


		trades.add(trade1);
		trades.add(trade2);
		trades.add(trade3);
		trades.add(trade4);
		trades.add(trade5);
		trades.add(trade6);

		return trades;
	}

}
