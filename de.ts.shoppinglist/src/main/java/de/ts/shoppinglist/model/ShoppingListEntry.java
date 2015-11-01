package de.ts.shoppinglist.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * This class represents an entry of a shoppinglist. Encapsulating a
 * {@link ShoppingItem} and a quantity
 * 
 * 
 **/
public class ShoppingListEntry extends Entity implements Transferable{

	private ShoppingItem item;

	private ShoppingItemQuantity quantity;

	/**
	 * Creates an instance of ShoppingListEntry with the given
	 * {@link ShoppingItem} and quantity
	 * 
	 * @param item
	 *            A ShoppingItem
	 * @param shoppingQuantity
	 *            quantity of the item
	 */
	public ShoppingListEntry(ShoppingItem item, ShoppingItemQuantity shoppingQuantity) {
		super();
		this.setQuantity(shoppingQuantity);
		this.setItem(item);
	}

	public ShoppingItem getItem() {
		return item;
	}

	public void setItem(ShoppingItem item) {
		this.item = item;
	}

	public ShoppingItemQuantity getQuantity() {
		return quantity;
	}

	public void setQuantity(ShoppingItemQuantity quantity) {
		this.quantity = quantity;
	}

	public boolean isValid() {
		if(item == null || quantity == null)
		{
			return false;
		}
		if(item.getName() == null || item.getName().isEmpty())
		{
			return false;
		}
		if(quantity.getQuantity() == 0)
		{
			return false;
		}
		return true;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {

		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingListEntry other = (ShoppingListEntry) obj;
		if(this.getId() != other.getId())
		{
			return false;
		}
		
		return true;
	}
	
	
}
