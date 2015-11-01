package de.ts.shoppinglist.view.components;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JTextField;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.util.Messages;

public class ShoppingItemComponent extends JComponent {

	private static final long serialVersionUID = 1L;

	private JTextField itemName;

	public ShoppingItemComponent(ShoppingItem item) {
		super();
		configure();
		initComponents(item);
		
		setVisible(true);
	}

	private void configure() {
		setOpaque(true);
		setLayout(new FlowLayout());
	}

	private void initComponents(ShoppingItem item) {
		itemName = new JTextField(25);
		itemName.setToolTipText(Messages.getString("ShoppingItemComponent.ToolTipItemName")); //$NON-NLS-1$

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
