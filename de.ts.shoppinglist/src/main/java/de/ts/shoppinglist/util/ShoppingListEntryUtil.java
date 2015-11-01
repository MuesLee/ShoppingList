package de.ts.shoppinglist.util;

import java.text.ParseException;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingListEntry;

public class ShoppingListEntryUtil {

	
	public static ShoppingListEntry parseStringToShoppingListEntry(String value) throws ParseException
	{
		try {
			
		String quantityTimesShoppingEntryString = ShoppingListEntry.QUANTITY_TIMES_SHOPPING_ENTRY_STRING;
		
		int startOfQuantity = 0;
		int endOfQuantity = value.indexOf(quantityTimesShoppingEntryString);
				
		int startOfItem = endOfQuantity+quantityTimesShoppingEntryString.length();
		int endOfItem = value.length();
		
		String itemQuantity = value.substring(startOfQuantity, endOfQuantity);
		String item = value.substring(startOfItem, endOfItem);
		
		int quantity = Integer.valueOf(itemQuantity);
		
		ShoppingItem shoppingItem = new ShoppingItem(item);
		ShoppingItemQuantity shoppingItemQuantity = new ShoppingItemQuantity(quantity);
		
		
		return new ShoppingListEntry(shoppingItem, shoppingItemQuantity);
		} catch (Exception e) {
			throw new ParseException(value, 0);
		}
		
	}
}
