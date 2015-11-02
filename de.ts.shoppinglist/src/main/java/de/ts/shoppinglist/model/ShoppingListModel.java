package de.ts.shoppinglist.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.SwingPropertyChangeSupport;

import de.ts.shoppinglist.controller.ShoppingListController;

/**
 * Extends {@link DefaultListModel}. Has {@link SwingPropertyChangeSupport} and
 * is configured to store {@link ShoppingListEntry}
 * 
 * 
 */
public class ShoppingListModel extends DefaultListModel<ShoppingListEntry> {

	private static Logger log = Logger.getLogger("ShoppingListModel");

	private static final long serialVersionUID = 1L;

	protected SwingPropertyChangeSupport propertyChangeSupport;

	public ShoppingListModel() {
		super();

		this.propertyChangeSupport = new SwingPropertyChangeSupport(this);

		addListDataListener(createListDataListener());
	}

	@Override
	public boolean removeElement(Object obj) {
		log.info(obj + " removed from model");
		return super.removeElement(obj);
	}

	@Override
	public ShoppingListEntry remove(int index) {
		log.info("index: " + index + " removed from model");
		return super.remove(index);

	}

	@Override
	public void addElement(ShoppingListEntry element) {
		super.addElement(element);
		log.info(element + " added to the end of the model");
	}

	@Override
	public void add(int index, ShoppingListEntry element) {
		super.add(index, element);
		log.info(element + " added to the model at " + index);
	}

	/**
	 * Creates a {@link ListDataListener} implementation which fires
	 * {@link PropertyChangeEvent} when the list changes
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

	/**
	 * Iterates over all {@link ShoppingListEntry} of this model and compares their id with the given.
	 * If a match was found, the index of that match will be stored and {@code remove(index)} will be called.
	 * 
	 * @param id Id of the {@link ShoppingListEntry} to delete
	 */
	public void removeElementById(Long id) {
		Enumeration<ShoppingListEntry> elements = elements();

		//index of the currently inspected element
		int index =0;
		
		while (elements.hasMoreElements()) {
			ShoppingListEntry nextElement = elements.nextElement();
			// if it is the element searched for
			if (nextElement.getId() == id) {
				 remove(index);
				 return;
			}
			index++;
		}

	}
}
