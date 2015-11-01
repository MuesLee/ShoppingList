package de.ts.shoppinglist.view.components;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.SwingConstants;

import de.ts.shoppinglist.model.ShoppingListEntry;

/**
 * Specialised Class of {@link ShoppingListEntryComponent}, to render a
 * {@link ShoppingListEntry} in {@link JList}
 *
 * @author Timo
 */
public class ShoppingListEntryRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -9212941310063227084L;

	public ShoppingListEntryRenderer() {

		setOpaque(true);

	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		Component listCellRendererComponent = super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		
		ShoppingListEntry listEntry = (ShoppingListEntry) value;
		String shoppingItemName = listEntry.getItem().getName();
		String quantityString = listEntry.getQuantity().getQuantity() + " x ";
		
		setText(quantityString + shoppingItemName);
		setHorizontalAlignment(SwingConstants.CENTER);

		return listCellRendererComponent;
	}

}
