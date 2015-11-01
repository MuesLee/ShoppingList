package de.ts.shoppinglist.view.components;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

import de.ts.shoppinglist.model.ShoppingItem;

public class ShoppingItemComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	private JTextField itemName;

	public ShoppingItemComponent(ShoppingItem item) {
		super();
		configure();
		initFields(item);
		
		setVisible(true);
	}

	private void configure() {
		setOpaque(true);
		setLayout(new FlowLayout());
	}

	private void initFields(ShoppingItem item) {
		itemName = new JTextField(25);

		if (item != null) {
			itemName.setText(item.getName());
		}
		
		add(itemName);
	}
	
	public String getShoppingItemName()
	{
		return itemName.getText();
	}
}
