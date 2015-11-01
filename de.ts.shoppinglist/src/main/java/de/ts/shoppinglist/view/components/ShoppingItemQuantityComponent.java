package de.ts.shoppinglist.view.components;

import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

public class ShoppingItemQuantityComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField textField;
	
	public ShoppingItemQuantityComponent() {
	
		configure();
		
		initComponents();
		
		addComponents();
	}


	private void addComponents() {
		add(textField);
	}


	private void configure() {
		setOpaque(true);
		setLayout(new FlowLayout());
	}


	private void initComponents() {
		textField = createNumbersOnlyComponent(4);
	}

	
	private JFormattedTextField createNumbersOnlyComponent(int length) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);

		JFormattedTextField component = new JFormattedTextField(decimalFormat);
		component.setColumns(length);
		component.setEditable(true);

		return component;
	}
	
	public void setItemQuantity(int value)
	{
		String valueString = String.valueOf(value);
		
		textField.setText(valueString);
	}
	
	public int getShoppingItemQuantityRepresentation()
	{
		String quantityTextFieldValue = textField.getText();
		int quantity = 0;
		if(!quantityTextFieldValue.isEmpty())
		quantity = Integer.valueOf(quantityTextFieldValue);
		
		return quantity;
		
	}
	
}
