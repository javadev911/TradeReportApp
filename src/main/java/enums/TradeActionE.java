package enums;

public enum TradeActionE {
	
    BUY("B"),
    SELL("S");

    private String value;

    TradeActionE(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }
	
    public static TradeActionE fromValue(String v) {
    	TradeActionE[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
        	TradeActionE c = arr$[i$];
            if(c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }

}
