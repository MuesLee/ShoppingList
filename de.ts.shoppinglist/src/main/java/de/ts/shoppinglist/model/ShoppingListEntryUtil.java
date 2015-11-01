package de.ts.shoppinglist.model;

public class ShoppingListEntryUtil {

	
	static final String QUANTITY_TIMES_SHOPPING_ENTRY_STRING = " x ";

	public static ShoppingListEntry parseStringToShoppingListEntry(String value)
	{
		int startOfQuantity = 0;
		int endOfQuantity = value.indexOf(QUANTITY_TIMES_SHOPPING_ENTRY_STRING);
		
		
		int startOfItem = endOfQuantity+QUANTITY_TIMES_SHOPPING_ENTRY_STRING.length();
		int endOfItem = value.length();
		
		String itemQuantity = value.substring(startOfQuantity, endOfQuantity);
		String item = value.substring(startOfItem, endOfItem);
		
		int quantity = Integer.valueOf(itemQuantity);
		
		ShoppingItem shoppingItem = new ShoppingItem(item);
		ShoppingItemQuantity shoppingItemQuantity = new ShoppingItemQuantity(quantity);
		
		
		return new ShoppingListEntry(shoppingItem, shoppingItemQuantity);
		
	}
}
