package de.ts.shoppinglist.view.components;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingListEntry;

/**
 * A specialized {@link JComponent} to display a {@link ShoppingListEntry}
 */
public class ShoppingListEntryComponent extends JComponent {
	protected static final int _DEFAULT_QUANTITY = 1;
	protected static final String _DEFAULT_ITEM_NAME = "Produktname";

	private static final long serialVersionUID = 1014430518778243865L;

	protected ShoppingItemComponent shoppingItem;
	protected ShoppingItemQuantityComponent shoppingItemQuantity;
	
	private JLabel quantityTimesItemLabel;

	/**
	 * Creates an instance with initialized components, but without
	 * {@link ShoppingItem} data
	 * 
	 **/
	public ShoppingListEntryComponent(ShoppingListEntry shoppingListEntry) {

		this.shoppingItem = createItemComponent(shoppingListEntry);
		this.shoppingItemQuantity = createItemQuantityComponent(shoppingListEntry);
		this.quantityTimesItemLabel = createQuantityTimesItemLabel();
		
		configure();

		this.add(shoppingItemQuantity);
		this.add(quantityTimesItemLabel);
		this.add(shoppingItem);

		setVisible(true);
	}

	private JLabel createQuantityTimesItemLabel() {
		JLabel label = new JLabel("x");
		return label;
	}

	private void configure() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	}

	/**
	 * Creates a {@link JComponent} to handle and display the quantity of a
	 * {@link ShoppingListEntry}
	 * 
	 * @param shoppingListEntry
	 * @return
	 */
	protected ShoppingItemQuantityComponent createItemQuantityComponent(ShoppingListEntry shoppingListEntry) {
		ShoppingItemQuantityComponent shoppingItemQuantityComponent = new ShoppingItemQuantityComponent();

		if (shoppingListEntry != null) {
			ShoppingItem item = shoppingListEntry.getItem();
			if (item != null)
				shoppingItemQuantityComponent.setItemQuantity(_DEFAULT_QUANTITY);
		}

		return shoppingItemQuantityComponent;
	}

	/**
	 * Creates a {@link JComponent} to handle and display a {@link ShoppingItem}
	 * 
	 * @param shoppingListEntry
	 * @return
	 */
	protected ShoppingItemComponent createItemComponent(ShoppingListEntry shoppingListEntry) {

		ShoppingItem item;
		if (shoppingListEntry == null) {
			item = null;
		} else {
			item = shoppingListEntry.getItem();
		}

		ShoppingItemComponent shoppingItem = new ShoppingItemComponent(item);

		return shoppingItem;
	}
	
	public ShoppingListEntry getShoppingListEntryRepresentation()
	{
		String name = shoppingItem.getShoppingItemName();
		int quantity = Integer.valueOf(shoppingItemQuantity.getShoppingItemQuantityRepresentation());
		
		ShoppingItem shoppingItem = new ShoppingItem(name);
		ShoppingItemQuantity shoppingQuantity = new ShoppingItemQuantity(quantity);
		ShoppingListEntry listEntry = new ShoppingListEntry(shoppingItem, shoppingQuantity);
		
		return listEntry;
	}

}
