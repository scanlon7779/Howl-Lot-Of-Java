package edu.ncsu.csc216.hloj.model.manager;

import edu.ncsu.csc216.hloj.model.MenuItem;
import edu.ncsu.csc216.hloj.model.ModelException;
import edu.ncsu.csc216.hloj.model.Order;
import edu.ncsu.csc216.hloj.model.OrderItem;
import edu.ncsu.csc216.hloj.model.lists.SortedList;

/**
 * Holds and manages all the menu items 
 * @author colinscanlon
 *
 */
public class MenuManager {

	/** Instance of MenuManager */
	private static MenuManager instance;
	
	/** Menu as a list of MenuItems */
	private SortedList<MenuItem> menu;
	
	/**
	 * The private constructor for the menu manager
	 */
	private MenuManager() {
		menu = new SortedList<MenuItem>();
	}
	
	/**
	 * Returns the one instance of the menu manager
	 * @return the instance of project manager
	 */
	public static MenuManager getInstance() {
		if (instance == null) {
			instance = new MenuManager();
		}
		return instance;
	}
	
	/**
	 * Adds the given menu item to the list
	 * @param m the menu item to add
	 */
	public void addMenuItem(MenuItem m) {
		menu.add(m);
	}
	
	/**
	 * Returns an array of menu items
	 * @return The array of all menu items
	 */
	public MenuItem[] getMenuItems() {
		MenuItem[] ret = new MenuItem[menu.size()];
		for (int i = 0; i < menu.size(); i++) {
			ret[i] = menu.get(i);
		}
		return ret;
	}
	
	/**
	 * Gets a menu item at the specified id
	 * @param id The id of the menu item to get
	 * @return The menu item with the specified id
	 */
	public MenuItem getMenuItem(int id) {
		return menu.get(id);
	}
	
	/**
	 * Removes a menu item with the given id
	 * @param id the id of the menu item to remove
	 * @throws ModelException thrown if the menu item is still a part of an open order
	 */
	public void removeMenuItem(int id) throws ModelException {
		for (Order o : OrderManager.getInstance().getOrders()) {
			for (OrderItem oi : o.getItems()) {
				if (oi.getMenuItem().equals(menu.get(id))) {
					throw new ModelException("Cannot delete a menu item that is part of an open order");
				}
			}
		}
		menu.remove(id);
	}
	
	/**
	 * Edits the menu item with the given id
	 * @param id The id of the menu item to edit
	 * @param m the menu item to edit
	 */
	public void editMenuItem(int id, MenuItem m) {
		menu.remove(id);
		menu.add(m);
	}
	
	/**
	 * Removes all menu items from the list
	 */
	public void removeAllMenuItems() {
		menu = new SortedList<MenuItem>();
	}
}
