package de.ts.shoppinglist.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingItemUnit;
import de.ts.shoppinglist.model.ShoppingListEntry;

public class ShoppingListEntryUtil {

	private static Logger log = Logger.getLogger("ShoppingListEntryUtil");
	
	/**
	 * Returns {@link ShoppingListEntry} containing the information of the given
	 * String; <br>
	 * Format: [String representation of {@link ShoppingItemQuantity}
	 * ][SPACE][String representation of {@link ShoppingItem}]
	 * 
	 * Uses the first correctly matched String representation of {@link ShoppingItemUnit} to determine the end of the string representation of {@link ShoppingItemQuantity}
	 * 
	 * @param value
	 *            String to be parsed
	 * @return {@link ShoppingListEntry}
	 * @throws ParseException
	 *             if the given String is not valid.
	 */
	public static ShoppingListEntry parseStringToShoppingListEntry(String value) throws ParseException {
		if (value == null || value.isEmpty()) {
			log.warning("ShoppingListEntry parsing failed for: " + value);
			throw new ParseException(value, 0);
		}

		ShoppingItemUnit[] values = ShoppingItemUnit.values();

		for (ShoppingItemUnit shoppingItemUnit : values) {
			try {

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
			} catch (Exception e) {
			}
		}

		log.warning("ShoppingListEntry parsing failed for: " + value);
		throw new ParseException(value, 0);
	}
	
	/**
	 * Parsing the data to a {@link List} of {@link ShoppingListEntry}.
	 * Expecting one {@link ShoppingListEntry} per line.
	 * 
	 * @param data
	 * @return
	 * @throws ParseException if the data could not be parsed
	 */
	public static List<ShoppingListEntry> parseStringToShoppingListEntries(String data) throws ParseException
	{
		List<ShoppingListEntry> shoppingListEntries = new ArrayList<>(); 
		
		String[] split = data.split("\n");
		for (String string : split) {
			ShoppingListEntry parseStringToShoppingListEntry = parseStringToShoppingListEntry(string);
			shoppingListEntries.add(parseStringToShoppingListEntry);
		}
		return shoppingListEntries;
		
	}
}
