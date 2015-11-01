package de.ts.shoppinglist.model;

import de.ts.shoppinglist.controller.ShoppingListController;

public class Entity {

	private long id;

	public Entity() {
		this.setId(ShoppingListController.getNextID());

	}

	public long getId() {
		return id;
	}

	void setId(long id) {
		this.id = id;
	}
}
