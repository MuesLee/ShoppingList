package de.ts.shoppinglist.model;

/**
 * This class represents a ShoppingItem
 * 
 */
public class ShoppingItem{
	
	private String name;

	/**
	 * Creates a {@link ShoppingItem} out of the given String
	 * 
	 * @param value
	 *            the string representation of the {@link ShoppingItem}
	 */

	public ShoppingItem(String value) {
		super();
		this.setName(value);
	}

	/**
	 * Returns the name of the ShoppingItem
	 * 
	 * @return Name of the ShoppingItem
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the ShoppingItem
	 * 
	 * @param name
	 *            new Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ShoppingItem other = (ShoppingItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
	

}
