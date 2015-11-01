package de.ts.shoppinglist.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.SwingPropertyChangeSupport;

import de.ts.shoppinglist.controller.ShoppingListController;

/**
 * Extends {@link DefaultListModel}. Has {@link SwingPropertyChangeSupport} and is configured to store {@link ShoppingListEntry} 
 *	
 *	
 */
public class ShoppingListModel extends DefaultListModel<ShoppingListEntry>{

	private static final long serialVersionUID = 1L;

	protected SwingPropertyChangeSupport propertyChangeSupport;

	public ShoppingListModel() {
		super();
		
		this.propertyChangeSupport = new SwingPropertyChangeSupport(this);
		
		addListDataListener(createListDataListener());
	}
	
	/**
	 * Creates a {@link ListDataListener} implementation which fires {@link PropertyChangeEvent} when the list changes
	 * 
	 * @return
	 */
	private ListDataListener createListDataListener() {
		
		return new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent e) {
				firePropertyChange(ShoppingListController._PROPERTY_LIST_INTERVAL_REMOVED, e.getIndex0(), null);
			}
			
			@Override
			public void intervalAdded(ListDataEvent e) {
				firePropertyChange(ShoppingListController._PROPERTY_LIST_INTERVAL_ADDED, null, e.getIndex0());
			}
			
			@Override
			public void contentsChanged(ListDataEvent e) {
				firePropertyChange(ShoppingListController._PROPERTY_LIST_DATA_CHANGED, null, null);
			}
		};
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	
	
}
