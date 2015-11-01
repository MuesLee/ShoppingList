package de.ts.shoppinglist.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShoppingListEntryUtilTest {

	@Test
	public void testparseStringToShoppingListEntry() throws Exception {

		String shoppingItemString = "Test";
		int shoppingItemQuantity = 1;
		String given = shoppingItemQuantity + ShoppingListEntryUtil.QUANTITY_TIMES_SHOPPING_ENTRY_STRING
				+ shoppingItemString;
		ShoppingListEntry actual = ShoppingListEntryUtil.parseStringToShoppingListEntry(given);

		ShoppingItem expectedShoppingItem = new ShoppingItem(shoppingItemString);
		ShoppingItemQuantity expectedShoppingItemQuantity = new ShoppingItemQuantity(shoppingItemQuantity);

		assertEquals(expectedShoppingItem, actual.getItem());
		assertEquals(expectedShoppingItemQuantity, actual.getQuantity());
	}

}
