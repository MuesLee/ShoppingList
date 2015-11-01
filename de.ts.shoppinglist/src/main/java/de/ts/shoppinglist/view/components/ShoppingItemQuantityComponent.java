package de.ts.shoppinglist.view.components;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import de.ts.shoppinglist.model.ShoppingItemUnit;

public class ShoppingItemQuantityComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private JSpinner shoppingItemQuantityComponent;
	private JComboBox<ShoppingItemUnit> shoppingItemUnitComponent;
	
	public ShoppingItemQuantityComponent() {
	
		configure();
		
		initComponents();
		
		addComponents();
	}


	private void addComponents() {
		add(shoppingItemQuantityComponent);
		add(shoppingItemUnitComponent);
	}


	private void configure() {
		setOpaque(true);
		setLayout(new FlowLayout());
	}


	private void initComponents() {
		shoppingItemQuantityComponent = createNumbersOnlyComponent(4);
		shoppingItemUnitComponent = createShoppingItemUnitComponent();
	}

	
	private JComboBox<ShoppingItemUnit> createShoppingItemUnitComponent() {
		
		JComboBox<ShoppingItemUnit> shoppingItemUnitComponent = new JComboBox<>(ShoppingItemUnit.values());
		
		return shoppingItemUnitComponent;
	}


	private JSpinner createNumbersOnlyComponent(int length) {
		
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 999999, 1));

		return spinner;
	}
	
	public void setItemQuantity(int value)
	{
		shoppingItemQuantityComponent.setValue(value);
	}
	
	public int getShoppingItemQuantity()
	{
		return (int) shoppingItemQuantityComponent.getValue();
	}


	public ShoppingItemUnit getShoppingItemUnit() {
		
		return (ShoppingItemUnit) shoppingItemUnitComponent.getSelectedItem();
	}
	
}
