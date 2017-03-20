package enums;

public enum CurrencyE {

    SGP("SGP"),
    AED("AED"),
    SAR("SAR"),
    GBP("GBP");

    private String value;

    CurrencyE(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }
    
    public static CurrencyE fromValue(String v) {
    	CurrencyE[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
        	CurrencyE c = arr$[i$];
            if(c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }


}
