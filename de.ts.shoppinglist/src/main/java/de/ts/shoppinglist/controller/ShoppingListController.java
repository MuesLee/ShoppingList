package de.ts.shoppinglist.controller;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ts.shoppinglist.model.ShoppingItem;
import de.ts.shoppinglist.model.ShoppingItemQuantity;
import de.ts.shoppinglist.model.ShoppingItemUnit;
import de.ts.shoppinglist.model.ShoppingListEntry;
import de.ts.shoppinglist.model.ShoppingListModel;
import de.ts.shoppinglist.view.components.ListEditPopup;
import de.ts.shoppinglist.view.components.ListTransferHandler;
import de.ts.shoppinglist.view.components.ShoppingListEntryRenderer;
import de.ts.shoppinglist.view.container.ShoppingListFrame;
import de.ts.shoppinglist.view.container.ShoppingListGUI;
import de.ts.shoppinglist.view.container.ShoppingListPanel;
import de.ts.shoppinglist.view.container.UserInputPanel;

public class ShoppingListController implements PropertyChangeListener, UserInputHandler {

	private static final Logger log = LoggerFactory.getLogger(ShoppingListController.class);
	
	public static String _PROPERTY_LIST_INTERVAL_ADDED = "_PROPERTY_LIST_INTERVAL_ADDED";
	public static String _PROPERTY_LIST_INTERVAL_REMOVED = "_PROPERTY_LIST_INTERVAL_REMOVED";
	public static String _PROPERTY_LIST_DATA_CHANGED = "_PROPERTY_LIST_DATA_CHANGED";

	private static long lastUsedID = 0L;

	// View
	private ShoppingListFrame mainFrame;
	private ShoppingListGUI shoppingListGUI;
	// Model
	private ShoppingListModel shoppingListModel;

	public ShoppingListController() {
		mainFrame = new ShoppingListFrame();

		mainFrame.setJMenuBar(createJMenuBar());

		JPanel shoppingListPanel = createShoppingListPanel();
		shoppingListGUI = (ShoppingListGUI) shoppingListPanel;

		mainFrame.add(shoppingListPanel, BorderLayout.CENTER);
		mainFrame.add(createUserInputPanel(), BorderLayout.PAGE_END);

	}

	/**
	 * Sets the mainFrame visible and packs it
	 */
	public void showFrame() {
		mainFrame.setVisible(true);
		mainFrame.pack();
	}

	/**
	 * Listens to {@link PropertyChangeEvent}.
	 * 
	 * If the {@link ShoppingListModel} has changed, the mainFrame will be
	 * updated to display the new status
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if (evt.getSource() == shoppingListModel) {
			mainFrame.pack();
		}

	}

	/**
	 * Reads the Data from the UserInputPanel and tries to add it to the
	 * shoppingListModel
	 * 
	 * @param shoppingListEntry
	 * 
	 */
	public void addShoppingItemToShoppingList(ShoppingListEntry shoppingListEntry) {
		if (shoppingListEntry != null && shoppingListEntry.isValid()) {
			shoppingListModel.addElement(shoppingListEntry);
		}
	}

	/**
	 * Removes the currently selected entries of the shoppingList from the
	 * shoppingListModel
	 * 
	 */
	public void removeCurrentlySelectedEntriesFromShoppingList() {

		List<ShoppingListEntry> selectedShoppingListEntries = shoppingListGUI.getSelectedShoppingListEntries();

		for (ShoppingListEntry shoppingListEntry : selectedShoppingListEntries) {

			shoppingListModel.removeElement(shoppingListEntry);
		}

	}

	@Override
	public void moveCurrentlySelectedEntryDownwards() {
		List<ShoppingListEntry> selectedShoppingListEntries = shoppingListGUI.getSelectedShoppingListEntries();
		for (ShoppingListEntry shoppingListEntry : selectedShoppingListEntries) {

			int currentIndex = shoppingListModel.indexOf(shoppingListEntry);
			int desiredIndex =  Math.max(currentIndex-1, 0);
			
			shoppingListModel.set(desiredIndex, shoppingListEntry);
		}
	}

	@Override
	public void moveCurrentlySelectedEntryUpwards() {
		
	}

	@Override
	public void editCurrentlySelectedItem() {
		new ListEditPopup(shoppingListGUI.getJList());
	}

	public static long getNextID() {
		return ++lastUsedID;
	}

	/**
	 * Creates a {@linkShoppingList} and a {@link ShoppingListPanel} which
	 * contains it.
	 * 
	 * @return
	 */
	private JPanel createShoppingListPanel() {

		JList<ShoppingListEntry> shoppingList = createShoppingList();
		JPanel shoppingListPanel = new ShoppingListPanel(shoppingList);

		return shoppingListPanel;
	}

	/**
	 * 
	 * Creates a {@link ShoppingList} with a {@link ShoppingListModel} Also
	 * registers the {@link ShoppingListController} as a
	 * {@link PropertyChangeListener}
	 **/
	private JList<ShoppingListEntry> createShoppingList() {
		shoppingListModel = new ShoppingListModel();
		shoppingListModel.addPropertyChangeListener(this);

		JList<ShoppingListEntry> shoppingList = new JList<ShoppingListEntry>(shoppingListModel);
		shoppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		shoppingList.setLayoutOrientation(JList.VERTICAL);
		shoppingList.setCellRenderer(new ShoppingListEntryRenderer());
		shoppingList.setDragEnabled(true);
		shoppingList.setDropMode(DropMode.INSERT);
		shoppingList.setTransferHandler(new ListTransferHandler());
		shoppingList.addMouseListener(createMouseListener());
		
		// TEST ENTRIES
		ShoppingListEntry shoppingListEntry = new ShoppingListEntry(new ShoppingItem("Brötchen"),
				new ShoppingItemQuantity(2, ShoppingItemUnit.PIECE));
		shoppingListModel.addElement(shoppingListEntry);
		ShoppingListEntry shoppingListEntry2 = new ShoppingListEntry(new ShoppingItem("Salami"),
				new ShoppingItemQuantity(2, ShoppingItemUnit.GRAMM));
		shoppingListModel.addElement(shoppingListEntry2);
		ShoppingListEntry shoppingListEntry3 = new ShoppingListEntry(new ShoppingItem("Parmesan"),
				new ShoppingItemQuantity(2123, ShoppingItemUnit.GRAMM));
		shoppingListModel.addElement(shoppingListEntry3);

		return shoppingList;
	}

	private MouseListener createMouseListener() {
		MouseListener mouseListener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);

				if (e.getClickCount() == 2) {
					editCurrentlySelectedItem();
				}
			}
		};
		return mouseListener;
	}

	/**
	 * Creates a JPanel with JButtons and JComponents to handle user input
	 * 
	 * @return
	 */
	private JPanel createUserInputPanel() {
		JPanel userInputPanel = new UserInputPanel(this);

		return userInputPanel;
	}

	/**
	 * Creates a JMenuBar with JMenuItems: <br>
	 * Menu ->
	 * 
	 * @return
	 */

	private JMenuBar createJMenuBar() {

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menü");
		JMenuItem menuItemExit = new JMenuItem("Beenden");

		menuBar.add(menu);
		menu.add(menuItemExit);

		return menuBar;
	}

}
