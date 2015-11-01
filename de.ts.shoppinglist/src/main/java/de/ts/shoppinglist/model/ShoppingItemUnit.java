package de.ts.shoppinglist.model;

public enum ShoppingItemUnit {

	PIECE(" x"), GRAMM("g");
	
	private String unitText;

	private ShoppingItemUnit(String unitText) {
		this.unitText = unitText;
	}

	@Override
	public String toString() {
		return unitText;
	}
	
}
