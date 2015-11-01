package de.ts.shoppinglist.view.dynamicgui;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 * Offers utility methods for the view
 * 
 * @author Timo
 *
 */
public class ViewUtil {

	/**
	 * Returns a configurated {@link JComponent} for the given purpose.   
	 * 
	 * @param contentType
	 *            Type of the content
	 * @param length character length of the input field
	 * @param editable  
	 * @return
	 */
	public static JComponent getComponentForType(ContentType contentType, int length, boolean editable) {

		if (length <= 0) {
			length = 1;
		}

		JComponent jComponent;

		switch (contentType) {
		case Integer:
			jComponent = createNumbersOnlyComponent(length, editable);
			break;
		case String:
		default:
			jComponent = createJTextField(length, editable);
			break;
		}

		return jComponent;

	}

	/**
	 * Returns a {@link JComponent} to handle decimal numbers. Uses the default {@link Locale}.
	 * 
	 * 
	 * @param length
	 * @param editable
	 * @return
	 */
	private static JComponent createNumbersOnlyComponent(int length, boolean editable) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
		DecimalFormat decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.setGroupingUsed(false);

		JFormattedTextField component = new JFormattedTextField(decimalFormat);
		component.setColumns(length);
		component.setEditable(editable);

		return component;
	}

	private static JComponent createJTextField(int length, boolean editable) {
		JTextField component = new JTextField(length);
		component.setEditable(editable);
		component.setColumns(length);

		return component;
	}

}
