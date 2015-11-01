package de.ts.shoppinglist.model;

public enum ShoppingItemUnit {

	PIECE(" x"), GRAMM("g"), KILOGRAMM("kg");
	
	private String unitText;

	private ShoppingItemUnit(String unitText) {
		this.unitText = unitText;
	}

	@Override
	public String toString() {
		return unitText;
	}
	
}
