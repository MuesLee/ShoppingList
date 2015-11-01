package de.ts.shoppinglist.view.dynamicgui;

import java.awt.FlowLayout;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * A {@link JComponent}, specialized to display with {@link View} annotated fields as Swing-Components.
 * 
 * Relies on {@link ViewUtil} to create the Swing components with the annotated values 
 * 
 * @author Timo
 *
 * @param <T> The type of the object to be displayed
 */
public class ViewComponent<T> extends JComponent {

	private static final long serialVersionUID = 1L;

	private T item;

	public ViewComponent(T item) {

		this.item = item;

		configure();
		
		initComponents();
	}

	private void configure() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
	}

	private void initComponents() {

		if(item == null)
			return;
		
		@SuppressWarnings("unchecked")
		Class<T> itemClass = (Class<T>) item.getClass();

		Field[] declaredFields = itemClass.getDeclaredFields();

		for (Field field : declaredFields) {
			View viewAnnotation = field.getAnnotation(View.class);
			if (viewAnnotation != null) {

				ContentType contentType = viewAnnotation.contentType();
				boolean editable = viewAnnotation.editable();
				int length = viewAnnotation.length();
				String labelName = viewAnnotation.labelName();
				
				JComponent componentForType = ViewUtil.getComponentForType(contentType, length, editable);
				JLabel jLabel = new JLabel(labelName);
				jLabel.setLabelFor(componentForType);
				jLabel.setText(labelName);
				add(jLabel);
				add(componentForType);
			} 
		}
	}

}
