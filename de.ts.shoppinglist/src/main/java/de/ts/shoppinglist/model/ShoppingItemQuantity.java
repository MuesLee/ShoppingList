package de.ts.shoppinglist.model;

public class ShoppingItemQuantity{

	private int quantity;
	
	private ShoppingItemUnit itemUnit;

	public ShoppingItemQuantity(int quantity, ShoppingItemUnit itemUnit) {
		super();
		this.quantity = quantity;
		this.itemUnit = itemUnit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemUnit == null) ? 0 : itemUnit.hashCode());
		result = prime * result + quantity;
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
		ShoppingItemQuantity other = (ShoppingItemQuantity) obj;
		if (itemUnit != other.itemUnit)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Integer.toString(quantity) + itemUnit.toString();
	}

	public ShoppingItemUnit getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(ShoppingItemUnit itemUnit) {
		this.itemUnit = itemUnit;
	}

	public boolean isValid() {
		if(quantity <1)
		{
			return false;
		}
		if(itemUnit == null)
		{
			return false;
		}
		
		return true;
	}
	
	
}
