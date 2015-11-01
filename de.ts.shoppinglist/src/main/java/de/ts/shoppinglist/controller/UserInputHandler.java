package de.ts.shoppinglist.controller;

import de.ts.shoppinglist.model.ShoppingListEntry;

public interface UserInputHandler {

	public void addShoppingItemToShoppingList(ShoppingListEntry shoppingListEntry);
	
	public void removeCurrentlySelectedEntriesFromShoppingList();
	
}
