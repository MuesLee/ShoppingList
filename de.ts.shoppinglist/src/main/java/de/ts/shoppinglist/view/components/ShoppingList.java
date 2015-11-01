package de.ts.shoppinglist.view.components;

import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import de.ts.shoppinglist.model.ShoppingListEntry;

public class ShoppingList extends JList<ShoppingListEntry> {

	private static final long serialVersionUID = 1L;

	
	public ShoppingList(ListModel<ShoppingListEntry> model) {
	super(model);
	
	setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	setLayoutOrientation(JList.VERTICAL);
	setCellRenderer(new ShoppingListEntryRenderer());
	setDragEnabled(true);
	setDropMode(DropMode.INSERT);
	setTransferHandler(new ListTransferHandler());
	}
}
