package de.ts.shoppinglist.util;

import java.text.ParseException;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingItemUnit;
import de.ts.shoppinglist.model.ShoppingListEntry;

public class ShoppingListEntryUtil {

	/**
	 * Returns {@link ShoppingListEntry} containing the information of the given
	 * String; <br>
	 * Format: [String representation of {@link ShoppingItemQuantity}
	 * ][SPACE][String representation of {@link ShoppingItem}]
	 * 
	 * Uses the first given String representation of {@link ShoppingItemUnit}
	 * 
	 * @param value
	 *            String to be parsed
	 * @return {@link ShoppingListEntry}
	 * @throws ParseException
	 *             if the given String is not valid.
	 */
	public static ShoppingListEntry parseStringToShoppingListEntry(String value) throws ParseException {
		if (value == null || value.isEmpty()) {
			throw new ParseException(value, 0);
		}

		try {
			ShoppingItemUnit[] values = ShoppingItemUnit.values();

			for (ShoppingItemUnit shoppingItemUnit : values) {

				String unitText = shoppingItemUnit.toString() + ShoppingListEntry.QUANTITY_TO_ITEM_DIVIDER;

				int startOfQuantity = 0;
				int endOfQuantity = value.indexOf(unitText);

				int startOfItem = endOfQuantity + unitText.length();
				int endOfItem = value.length();

				String itemQuantity = value.substring(startOfQuantity, endOfQuantity);
				String item = value.substring(startOfItem, endOfItem);

				int quantity = Integer.valueOf(itemQuantity);

				ShoppingItem shoppingItem = new ShoppingItem(item);
				ShoppingItemQuantity shoppingItemQuantity = new ShoppingItemQuantity(quantity, shoppingItemUnit);

				return new ShoppingListEntry(shoppingItem, shoppingItemQuantity);
			}

		} catch (Exception e) {
			throw new ParseException(value, 0);
		}
		
		throw new ParseException(value, 0);

	}
}
