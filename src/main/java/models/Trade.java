package models;

import java.util.Date;

import enums.CurrencyE;
import enums.EntityE;
import enums.TradeActionE;

public class Trade {

	private EntityE entity;
	private TradeActionE tradeAction;
	private Double agreedFx;
	private CurrencyE currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units;
	private Double pricePerUnit;

	public EntityE getEntity() {
		return entity;
	}

	public void setEntity(EntityE entity) {
		this.entity = entity;
	}

	public TradeActionE getTradeAction() {
		return tradeAction;
	}

	public void setTradeAction(TradeActionE tradeAction) {
		this.tradeAction = tradeAction;
	}

	public Double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public CurrencyE getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyE currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	public Double getUSDAmountOfTrade(){
		return pricePerUnit * units * agreedFx;
	}

}
