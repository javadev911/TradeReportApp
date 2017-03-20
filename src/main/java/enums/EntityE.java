package enums;

public enum EntityE {

    FOO("FOO"),
    BAR("BAR");

    private String value;

    EntityE(String v) {
        this.value = v;
    }

    public String getValue() {
        return value;
    }
    
    public static EntityE fromValue(String v) {
    	EntityE[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
        	EntityE c = arr$[i$];
            if(c.value.equals(v)) {
                return c;
            }
        }

        throw new IllegalArgumentException(v);
    }


}
