package com.karasuno.spring.view;

public enum ManageView {
    
    ACCOUNT("account"),
    PRODUCT("product"),
    WAREHOUSE("warehouse");

    public final String view;

    private ManageView(String view) {
        this.view = view;
    }

    public String getViewName() {
        return view;
    }
}
