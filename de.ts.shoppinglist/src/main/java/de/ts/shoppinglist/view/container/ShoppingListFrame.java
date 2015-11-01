package de.ts.shoppinglist.view.container;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Extends {@link JFrame}. Adds some default configuration.
 * Built to display the ShoppingList application and all its components
 * 
 * @author Timo
 *
 */
public class ShoppingListFrame extends JFrame {

	private static final long serialVersionUID = 3779245123841078581L;

	/**
	 * Creates {@link ShoppingListFrame} with the default configuration: <br>
	 * {@link BorderLayout}, centered on the screen, {@link JFrame.DISPOSE_ON_CLOSE}.
	 */
	public ShoppingListFrame() {
	
		configureFrame();
		
	}
	
	private void configureFrame() {
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(Messages.getString("ShoppingListFrame.FrameTitle")); //$NON-NLS-1$
	}
	
	
}
