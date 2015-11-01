package de.ts.shoppinglist.model;

public class ShoppingItemQuantity{

	private int quantity;

	public ShoppingItemQuantity(int quantity) {
		super();
		this.quantity = quantity;
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
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return Integer.toString(quantity);
	}
	
	
}
