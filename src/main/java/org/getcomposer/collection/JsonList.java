package org.getcomposer.collection;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class JsonList<V> extends JsonCollection<V> implements Iterable<V> {
	
	protected List<V> values = new ArrayList<V>();

	protected JsonList(Type type) {
		super(type);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.getcomposer.values.JsonCollection#size()
	 */
	public int size() {
		return values.size();
	}
	
	/**
	 * Returns the passed object
	 * 
	 * @param packageName
	 * @return the dependency
	 */
	public V get(int index) {
		return values.get(index);
	}
	
	/**
	 * Adds an value to this values
	 * 
	 * @param value the new value
	 */
	@SuppressWarnings("unchecked")
	public <T> T add(V value) {
		List<V> oldValues = (List<V>) ((ArrayList<V>)values).clone();
		values.add(value);
		firePropertyChange("values", oldValues, values);
		return (T) this;
	}
	
	/**
	 * Removes an value from this values
	 * 
	 * @param value the value to remove
	 */
	@SuppressWarnings("unchecked")
	public <T> T remove(V value) {
		List<V> oldValues = (List<V>) ((ArrayList<V>)values).clone();
		values.remove(value);
		firePropertyChange("values", oldValues, values);
		return (T) this;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<V> iterator() {
		return values.iterator();
	}
}
