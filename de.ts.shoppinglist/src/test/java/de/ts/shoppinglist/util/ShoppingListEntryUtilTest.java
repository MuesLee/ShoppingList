package de.ts.shoppinglist.util;

import static org.junit.Assert.*;

import org.junit.Test;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingItemUnit;
import de.ts.shoppinglist.model.ShoppingListEntry;
import de.ts.shoppinglist.util.ShoppingListEntryUtil;

public class ShoppingListEntryUtilTest {

	@Test
	public void testparseStringToShoppingListEntry() throws Exception {

		String shoppingItemString = "Test";
		int shoppingItemQuantity = 1;
		ShoppingItemUnit itemUnit = ShoppingItemUnit.PIECE;
		
		String given = shoppingItemQuantity	+ itemUnit.toString() + " " + shoppingItemString;
		ShoppingListEntry actual = ShoppingListEntryUtil.parseStringToShoppingListEntry(given);

		ShoppingItem expectedShoppingItem = new ShoppingItem(shoppingItemString);
		ShoppingItemQuantity expectedShoppingItemQuantity = new ShoppingItemQuantity(shoppingItemQuantity, itemUnit);

		assertEquals(expectedShoppingItem, actual.getItem());
		assertEquals(expectedShoppingItemQuantity, actual.getQuantity());
	}

}
