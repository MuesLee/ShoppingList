package de.ts.shoppinglist.view.container;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import de.ts.shoppinglist.model.ShoppingListEntry;
import de.ts.shoppinglist.util.Messages;

public class ShoppingListPanel extends JPanel implements ShoppingListGUI {
	private static final long serialVersionUID = 1L;

	private JList<ShoppingListEntry> shoppingList;
	private JLabel shoppingListLabel;

	public ShoppingListPanel(JList<ShoppingListEntry> shoppingList) {
		this.shoppingList = shoppingList;

		initComponents();

		configure();
		add(shoppingListLabel, BorderLayout.BEFORE_FIRST_LINE);
		add(shoppingList, BorderLayout.CENTER);
	}

	private void initComponents() {
		shoppingListLabel = new JLabel(Messages.getString("ShoppingListPanel.MyShoppingListLabel")); //$NON-NLS-1$
		shoppingListLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void configure() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}

	public List<ShoppingListEntry> getSelectedShoppingListEntries() {
		return shoppingList.getSelectedValuesList();
	}

	@Override
	public JList<ShoppingListEntry> getJList() {
		return shoppingList;
	}

}