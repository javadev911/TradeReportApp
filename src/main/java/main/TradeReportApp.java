package main;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import controllers.TradeReportController;

public class TradeReportApp {

	public static void main(String[] args) {
		TradeReportController controller = (TradeReportController) getWeldContainerForDI().select(TradeReportController.class).get();
		controller.reportTrades();
	}
	
	private static WeldContainer getWeldContainerForDI(){
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		return container;
	}

}
