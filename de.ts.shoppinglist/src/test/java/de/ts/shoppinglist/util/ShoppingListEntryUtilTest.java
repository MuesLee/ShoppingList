package de.ts.shoppinglist.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingItemUnit;
import de.ts.shoppinglist.model.ShoppingListEntry;

public class ShoppingListEntryUtilTest {


	@Test
	public void testparsePieceStringToShoppingListEntry() throws Exception {
		
		String shoppingItemString = "Test ";
		int shoppingItemQuantity = 1;
		ShoppingItemUnit itemUnit = ShoppingItemUnit.PIECE;
		
		String given = shoppingItemQuantity	+ itemUnit.toString() + " " + shoppingItemString;
		ShoppingListEntry actual = ShoppingListEntryUtil.parseStringToShoppingListEntry(given);
		
		ShoppingItem expectedShoppingItem = new ShoppingItem(shoppingItemString);
		ShoppingItemQuantity expectedShoppingItemQuantity = new ShoppingItemQuantity(shoppingItemQuantity, itemUnit);
		
		assertEquals(expectedShoppingItem, actual.getItem());
		assertEquals(expectedShoppingItemQuantity, actual.getQuantity());
	}
	@Test
	public void testparseGrammStringToShoppingListEntry() throws Exception {
		
		String shoppingItemString = " Test";
		int shoppingItemQuantity = 12313;
		ShoppingItemUnit itemUnit = ShoppingItemUnit.GRAMM;
		
		String given = shoppingItemQuantity	+ itemUnit.toString() + " " + shoppingItemString;
		ShoppingListEntry actual = ShoppingListEntryUtil.parseStringToShoppingListEntry(given);
		
		ShoppingItem expectedShoppingItem = new ShoppingItem(shoppingItemString);
		ShoppingItemQuantity expectedShoppingItemQuantity = new ShoppingItemQuantity(shoppingItemQuantity, itemUnit);
		
		assertEquals(expectedShoppingItem, actual.getItem());
		assertEquals(expectedShoppingItemQuantity, actual.getQuantity());
	}
	@Test
	public void testparseGrammStringToShoppingListEntries() throws Exception {
		
		String shoppingItemString = " Test";
		int shoppingItemQuantity = 12313;
		ShoppingItemUnit itemUnit = ShoppingItemUnit.GRAMM;
		
		ShoppingItem shoppingItem = new ShoppingItem(shoppingItemString);
		ShoppingItemQuantity expectedShoppingItemQuantity = new ShoppingItemQuantity(shoppingItemQuantity, itemUnit);
		ShoppingListEntry expectedShoppingListEntry = new ShoppingListEntry(shoppingItem, expectedShoppingItemQuantity);
		
		List<ShoppingListEntry> actualList = ShoppingListEntryUtil.parseStringToShoppingListEntries(expectedShoppingListEntry.toString());
		
		ShoppingListEntry actual = actualList.get(0);
		assertEquals(expectedShoppingItemQuantity, actual.getQuantity());
		assertEquals(shoppingItem, actual.getItem());
	}
	@Test
	public void testparseGrammStringToShoppingListEntries2() throws Exception {
		
		String shoppingItemString = " Test";
		int shoppingItemQuantity = 12313;
		ShoppingItemUnit itemUnit = ShoppingItemUnit.GRAMM;
		
		ShoppingItem shoppingItem = new ShoppingItem(shoppingItemString);
		ShoppingItemQuantity expectedShoppingItemQuantity = new ShoppingItemQuantity(shoppingItemQuantity, itemUnit);
		ShoppingItem shoppingItem2 = new ShoppingItem(shoppingItemString+"2");
		ShoppingListEntry expectedShoppingListEntry = new ShoppingListEntry(shoppingItem, expectedShoppingItemQuantity);
		ShoppingListEntry expectedShoppingListEntry2 = new ShoppingListEntry(shoppingItem2, expectedShoppingItemQuantity);
		
		String givenData = expectedShoppingListEntry.toString() +"\n"+expectedShoppingListEntry2.toString();
		List<ShoppingListEntry> actualList = ShoppingListEntryUtil.parseStringToShoppingListEntries(givenData);
		
		ShoppingListEntry actual = actualList.get(0);
		ShoppingListEntry actual2 = actualList.get(1);
		assertEquals(expectedShoppingItemQuantity, actual.getQuantity());
		assertEquals(shoppingItem, actual.getItem());
		assertEquals(expectedShoppingItemQuantity, actual2.getQuantity());
		assertEquals(shoppingItem2, actual2.getItem());
		
	}
}
