package models;

import java.util.List;

public class Report {

	private double usdIncoming = 0.0;
	private double usdOutgoing = 0.0;
	List<Trade> rankedBuyTrades;
	List<Trade> rankedSellTrades;

	public double getUsdIncoming() {
		return usdIncoming;
	}

	public void setUsdIncoming(double usdIncoming) {
		this.usdIncoming = usdIncoming;
	}

	public double getUsdOutgoing() {
		return usdOutgoing;
	}

	public void setUsdOutgoing(double usdOutgoing) {
		this.usdOutgoing = usdOutgoing;
	}

	public List<Trade> getRankedBuyTrades() {
		return rankedBuyTrades;
	}

	public void setRankedBuyTrades(List<Trade> rankedBuyTrades) {
		this.rankedBuyTrades = rankedBuyTrades;
	}

	public List<Trade> getRankedSellTrades() {
		return rankedSellTrades;
	}

	public void setRankedSellTrades(List<Trade> rankedSellTrades) {
		this.rankedSellTrades = rankedSellTrades;
	}


}
