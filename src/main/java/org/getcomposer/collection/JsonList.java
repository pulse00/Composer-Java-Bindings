package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class JsonList<V> extends JsonCollection<V> implements Iterable<V> {
	
	protected List<V> collection = new ArrayList<V>();

	protected JsonList(Type type) {
		super(type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.collection.JsonCollection#size()
	 */
	public int size() {
		return collection.size();
	}
	
	/**
	 * Returns the passed object
	 * 
	 * @param packageName
	 * @return the dependency
	 */
	public V get(int index) {
		return collection.get(index);
	}
	
	/**
	 * Adds an item to this collection
	 * 
	 * @param item the new item
	 */
	public void add(V item) {
		@SuppressWarnings("unchecked")
		List<V> oldCollection = (List<V>) ((ArrayList<V>)collection).clone();
		collection.add(item);
		firePropertyChange("collection", oldCollection, collection);
	}
	
	/**
	 * Removes an item from this collection
	 * 
	 * @param item the item to remove
	 */
	public void remove(V item) {
		@SuppressWarnings("unchecked")
		List<V> oldCollection = (List<V>) ((ArrayList<V>)collection).clone();
		collection.remove(item);
		firePropertyChange("dependencies", oldCollection, collection);
	}
	
	public Iterator<V> iterator() {
		return collection.iterator();
	}
}
