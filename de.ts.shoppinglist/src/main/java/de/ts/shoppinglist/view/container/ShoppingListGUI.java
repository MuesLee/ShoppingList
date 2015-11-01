package de.ts.shoppinglist.view.container;

import java.util.List;

import javax.swing.JList;

import de.ts.shoppinglist.model.ShoppingListEntry;

public interface ShoppingListGUI {

	public List<ShoppingListEntry> getSelectedShoppingListEntries();

	public JList<ShoppingListEntry> getJList();
	
}
