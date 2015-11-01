package de.ts.shoppinglist.view.container;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.ts.shoppinglist.controller.UserInputHandler;
import de.ts.shoppinglist.model.ShoppingListEntry;
import de.ts.shoppinglist.view.components.ShoppingListEntryComponent;

public class UserInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private UserInputHandler controller;

	private JPanel buttonPanel;
	private JButton addItemToListButton;
	private JButton removeItemFromListButton;

	private ShoppingListEntryComponent shoppingListEntryComponent;

	private JLabel userInputLabel;

	public UserInputPanel(UserInputHandler controller) {

		this.controller = controller;

		configure();

		initComponents();

		addComponents();

	}

	private void configure() {
		LayoutManager layoutManager = new BorderLayout();
		setLayout(layoutManager);

		addKeyBinding();

		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}

	private void initComponents() {
		this.buttonPanel = createButtonPanel();
		this.userInputLabel = new JLabel("Neuen Eintrag erfassen:");
		this.userInputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		shoppingListEntryComponent = new ShoppingListEntryComponent(null);
	}

	private void addComponents() {
		add(userInputLabel, BorderLayout.PAGE_START);
		add(buttonPanel, BorderLayout.PAGE_END);
		add(shoppingListEntryComponent, BorderLayout.CENTER);
	}

	private void addKeyBinding() {

		// Pressing Enter will add the ShoppingItem

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
				"Enter pressed");
		getActionMap().put("Enter pressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				addShoppingItemToShoppingList();
			}
		});
		// Pressing Delete will remove the ShoppingItem

		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false),
				"Delete pressed");
		getActionMap().put("Delete pressed", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent ae) {
				removeItemsFromShoppingList();
			}
		});

	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new FlowLayout());

		this.addItemToListButton = new JButton("hinzufügen");
		this.removeItemFromListButton = new JButton("entfernen");

		addItemToListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				addShoppingItemToShoppingList();
			}
		});

		removeItemFromListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeItemsFromShoppingList();
			}
		});

		buttonPanel.add(addItemToListButton);
		buttonPanel.add(removeItemFromListButton);

		return buttonPanel;
	}

	private ShoppingListEntry createShoppingItemFromUserInput() {
		ShoppingListEntry shoppingListEntry = shoppingListEntryComponent.getShoppingListEntryRepresentation();

		return shoppingListEntry;

	}

	private void addShoppingItemToShoppingList() {
		ShoppingListEntry shoppingListEntry = createShoppingItemFromUserInput();
		controller.addShoppingItemToShoppingList(shoppingListEntry);
	}

	private void removeItemsFromShoppingList() {
		controller.removeCurrentlySelectedEntriesFromShoppingList();
	}
}
