package de.ts.shoppinglist.view.components;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import de.ts.shoppinglist.model.ShoppingListEntry;
import de.ts.shoppinglist.model.ShoppingListModel;
import de.ts.shoppinglist.util.ShoppingListEntryUtil;

public class ListTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
	private List<Long> shoppingListEntryIds = new ArrayList<>();

	/**
	 * Only supports importing {@link String}
	 */
	public boolean canImport(TransferHandler.TransferSupport info) {
		// Check for String flavor
		if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			return false;
		}

		info.setShowDropLocation(true);

		JList.DropLocation dropLocation = (JList.DropLocation) info.getDropLocation();
		int dropIndex = dropLocation.getIndex();

		// we do not support invalid columns
		if (dropIndex == -1) {
			return false;
		}

		return true;
	}

	/**
	 * Bundle up the selected items in a single list for export. Each line is
	 * separated by a newline.
	 */
	protected Transferable createTransferable(JComponent c) {
		@SuppressWarnings("unchecked")
		JList<ShoppingListEntry> list = (JList<ShoppingListEntry>) c;
		try {
			ArrayList<ShoppingListEntry> values = (ArrayList<ShoppingListEntry>) list.getSelectedValuesList();
			for (ShoppingListEntry shoppingListEntry : values) {
				shoppingListEntryIds.add(shoppingListEntry.getId());
			}

			StringBuffer buff = new StringBuffer();

			for (ShoppingListEntry shoppingListEntry : values) {

				buff.append(shoppingListEntry == null ? "" : shoppingListEntry.toString());
				buff.append("\n");
			}

			return new StringSelection(buff.toString());
		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * Support move actions.
	 */
	public int getSourceActions(JComponent c) {
		return TransferHandler.MOVE;
	}

	/**
	 * Perform the actual import. Only supports drag and drop.
	 * 
	 * @param info
	 *            {@link TransferSupport} info
	 */
	public boolean importData(TransferHandler.TransferSupport info) {
		if (!info.isDrop()) {
			return false;
		}

		@SuppressWarnings("unchecked")
		JList<ShoppingListEntry> list = (JList<ShoppingListEntry>) info.getComponent();
		ShoppingListModel listModel = (ShoppingListModel) list.getModel();
		JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
		int index = dl.getIndex();
		boolean insert = dl.isInsert();

		Transferable t = info.getTransferable();
		String data;
		try {
			data = (String) t.getTransferData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			return false;
		}

		// Split data by new Line
		String[] values = data.split("\n");

		// Perform the actual import.
		for (int i = 0; i < values.length; i++) {

			String value = values[i];
			ShoppingListEntry parsedShoppingListEntry;

			// Try to parse the String to a ShoppingListEntry
			try {
				parsedShoppingListEntry = ShoppingListEntryUtil.parseStringToShoppingListEntry(value);

			} catch (Exception e) {
				return false;
			}

			// Insert all Drops under the Drop Location
			index = index + i;

			if (insert) {

				try {
					listModel.add(index, parsedShoppingListEntry);

				} catch (ArrayIndexOutOfBoundsException e) {
					// If the items go beyond the end of the current
					// list, add them in.
					listModel.addElement(parsedShoppingListEntry);
				}
			}
		}
		return true;
	}

	/**
	 * Remove the items moved from the list.
	 */
	protected void exportDone(JComponent c, Transferable data, int action) {
		@SuppressWarnings("unchecked")
		JList<ShoppingListEntry> source = (JList<ShoppingListEntry>) c;
		ShoppingListModel listModel = (ShoppingListModel) source.getModel();

		try {
			if (action == TransferHandler.MOVE) {

				for (Long id : shoppingListEntryIds) {
					listModel.removeElementById(id);
				}

			}
		} catch (Exception e) {
		} finally {
			shoppingListEntryIds = new ArrayList<>();
		}
	}
}
