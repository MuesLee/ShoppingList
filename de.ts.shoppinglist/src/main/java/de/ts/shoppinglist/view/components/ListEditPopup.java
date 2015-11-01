package de.ts.shoppinglist.view.components;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import de.ts.shoppinglist.model.ShoppingListEntry;
import de.ts.shoppinglist.util.ShoppingListEntryUtil;

public class ListEditPopup extends JPopupMenu {

	private static final long serialVersionUID = 1L;
	
	private JTextField editTextField;
	private JList<ShoppingListEntry> list;

	public ListEditPopup(final JList<ShoppingListEntry> list) {

		this.list = list;

		editTextField = new JTextField();
		Border border = UIManager.getBorder("List.focusCellHighlightBorder");
		editTextField.setBorder(border);

		// Add an Action to the text field to save the new value to the model

		editTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = editTextField.getText();
				DefaultListModel<ShoppingListEntry> model = (DefaultListModel<ShoppingListEntry>) list.getModel();
				int row = list.getSelectedIndex();
				applyValueToModel(value, model, row);
				setVisible(false);
			}

		});

		// Add the editor to the popup

		setBorder(new EmptyBorder(0, 0, 0, 0));
		add(editTextField);

		int row = list.getSelectedIndex();
		Rectangle r = list.getCellBounds(row, row);

		setPreferredSize(new Dimension(r.width, r.height));
		show(list, r.x, r.y);

		// Prepare the text field for editing

		editTextField.setText(list.getSelectedValue().toString());
		editTextField.selectAll();
		editTextField.setHorizontalAlignment(SwingConstants.CENTER);
		editTextField.requestFocusInWindow();

	}

	private void applyValueToModel(String value, DefaultListModel<ShoppingListEntry> model, int row) {

		ShoppingListEntry newEntry;
		try {
			newEntry = ShoppingListEntryUtil.parseStringToShoppingListEntry(value);
			model.set(row, newEntry);
		} catch (ParseException e) {
		}

	}

}
